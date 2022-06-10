package com.example.newestapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class fragment_ac extends Fragment implements RecyclerViewInterface{

    private ArrayList<FullItem> fullItems = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fullItems.add(new FullItem(R.drawable.hamr, "Будущее Western Digital", "07.06.21", "6", "0"));
        fullItems.add(new FullItem(R.drawable.garbage, "Жесткие диски могут выйти из употребления в скором времени?", "30.04.21", "7", "0"));
        fullItems.add(new FullItem(R.drawable.diamond, "Какие накопители будут использовать в будущем?", "10.04.21", "8", "0"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ac, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerC);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new ContentAdapter(fullItems, getActivity(), this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), ContentActivity.class);
        intent.putExtra("content", position);
        startActivity(intent);
    }
}