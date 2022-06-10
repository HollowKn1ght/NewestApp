package com.example.newestapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class fragment_ab extends Fragment implements RecyclerViewInterface{

    private ArrayList<FullItem> fullItems = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fullItems.add(new FullItem(R.drawable.hdd, "HDD или же винчестер", "04.03.19", "3", "0"));
        fullItems.add(new FullItem(R.drawable.ssd, "Твердотельный накопитель SSD", "18.08.18", "4", "0"));
        fullItems.add(new FullItem(R.drawable.m2, "SSD с протоколом NVMe", "22.02.22", "5", "0"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ab, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerB);
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