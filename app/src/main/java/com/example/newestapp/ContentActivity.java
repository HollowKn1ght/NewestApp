package com.example.newestapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContentActivity extends AppCompatActivity {

    private TextView txt;
    private int content;
    private String[] contentText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        txt = findViewById(R.id.text_content);
        contentText = getResources().getStringArray(R.array.content_in_producers);
        coolgnome();
    }

    private void coolgnome(){
        Intent i = getIntent();
        if(i != null){
            content = i.getIntExtra("content", 0);
        }

        txt.setText(contentText[content]);
    }
}
