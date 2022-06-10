package com.example.newestapp;

import android.content.Intent;
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

import java.util.ArrayList;


public class fragment_ad extends Fragment implements RecyclerViewInterface{

    private ArrayList<FullItem> fullItems = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fullItems.add(new FullItem(R.drawable.mech, "Устройство жесткого диска", "20.05.21", "9", "0"));
        fullItems.add(new FullItem(R.drawable.magnet, "Магнитный принцип чтения и записи информации", "12.04.21", "10", "0"));
        fullItems.add(new FullItem(R.drawable.logic, "Логическое устройство винчестера", "20.10.20", "11", "0"));
        fullItems.add(new FullItem(R.drawable.param, "Характеристики винчестеров", "09.07.20", "12", "0"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ad, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerD);
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