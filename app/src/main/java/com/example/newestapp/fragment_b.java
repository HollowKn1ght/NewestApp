package com.example.newestapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.newestapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class fragment_b extends Fragment implements RecyclerViewInterface{

    private RecyclerView recyclerView;
    private FDatabase db;
    private List<FavItem> favItemList = new ArrayList<>();
    private FavAdapter favAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);

        db = new FDatabase(getActivity());
        recyclerView = view.findViewById(R.id.recyclerB);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadData();

        return view;
    }

    private void loadData() {
        if (favItemList != null){
            favItemList.clear();
        }
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = db.select_all_favorite_list();
        try{
            while (cursor.moveToNext()){
                @SuppressLint("Range") String title = cursor.getString(
                        cursor.getColumnIndex(FDatabase.ITEM_TITLE));
                @SuppressLint("Range") String date = cursor.getString(
                        cursor.getColumnIndex(FDatabase.ITEM_DATE));
                @SuppressLint("Range") String id = cursor.getString(
                        cursor.getColumnIndex(FDatabase.KEY_ID));
                @SuppressLint("Range") int image = Integer.parseInt(
                        cursor.getString(cursor.getColumnIndex(FDatabase.ITEM_IMAGE)));
                FavItem favItem = new FavItem(title, date, id, image);
                favItemList.add(favItem);
            }
        } finally {
            if (cursor != null)
                cursor.close();
            sqLiteDatabase.close();
        }

        favAdapter = new FavAdapter(getActivity(), favItemList, this);
        recyclerView.setAdapter(favAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), ContentActivity.class);
        intent.putExtra("content", position);
        startActivity(intent);
    }
}