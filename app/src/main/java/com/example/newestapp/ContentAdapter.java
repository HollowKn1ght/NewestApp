package com.example.newestapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;

    private ArrayList<FullItem> items;
    private Context context;
    private FDatabase db;

    public ContentAdapter(ArrayList<FullItem> items, Context context,
                          RecyclerViewInterface recyclerViewInterface) {
        this.items = items;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,
                parent,false);
        db = new FDatabase(context);
        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            createTableOnFirstStart();
        }
        return new ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FullItem fullItem= items.get(position);

        readCursorData(fullItem, holder);
        holder.img.setImageResource(fullItem.getImageResourse());
        holder.txt.setText(fullItem.getTitle());
        holder.dat.setText(fullItem.getDate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView txt;
        TextView dat;
        Button btn;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            img = itemView.findViewById(R.id.image_view);
            txt = itemView.findViewById(R.id.titleOfStory_text_view);
            dat = itemView.findViewById(R.id.date);
            btn = itemView.findViewById(R.id.favBtn);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    FullItem fullItem = items.get(position);

                    if (fullItem.getFavStatus().equals("0")){
                        fullItem.setFavStatus("1");
                        db.insertIntoTheDatabase(fullItem.getTitle(), fullItem.getDate(), fullItem.getImageResourse(),
                                fullItem.getKey_id(), fullItem.getFavStatus());
                        btn.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                    }else{
                        fullItem.setFavStatus("0");
                        db.remove_fav(fullItem.getKey_id());
                        btn.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        int pos = Integer.parseInt(items.get(position).getKey_id());

                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

    private void createTableOnFirstStart() {
        db.insertEmpty();

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void readCursorData(FullItem fullItem, ViewHolder viewHolder) {
        Cursor cursor = db.read_all_data(fullItem.getKey_id());
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        try{
            while (cursor.moveToNext()){
                @SuppressLint("Range") String item_fav_status = cursor.getString(
                        cursor.getColumnIndex(FDatabase.FAVORITE_STATUS));
                fullItem.setFavStatus(item_fav_status);

                if (item_fav_status != null && item_fav_status.equals("1")){
                    viewHolder.btn.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                } else if (item_fav_status != null && item_fav_status.equals("0")){
                    viewHolder.btn.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            sqLiteDatabase.close();
        }
    }
}
