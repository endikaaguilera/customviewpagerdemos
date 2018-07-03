package com.thisobeystudio.customviewpagerdemos;

/*
 * Created by Endika Aguilera on 31/5/18.
 * Copyright: (c) 2018 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.thisobeystudio.customviewpagerdemos.basicdemo.BasicActivity;
import com.thisobeystudio.customviewpagerdemos.basicdemo.IndicatorsActivity;
import com.thisobeystudio.customviewpagerdemos.complexdemo.ComplexActivity;
import com.thisobeystudio.customviewpagerdemos.scrollabledemo.ScrollableActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.basicBtn).setOnClickListener(this);
        findViewById(R.id.indicatorsBtn).setOnClickListener(this);
        findViewById(R.id.scrollableBtn).setOnClickListener(this);
        findViewById(R.id.complexBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        int id = v.getId();

        switch (id) {
            case R.id.basicBtn:
                intent = new Intent(MainActivity.this, BasicActivity.class);
                startActivity(intent);
                break;
            case R.id.indicatorsBtn:
                intent = new Intent(MainActivity.this, IndicatorsActivity.class);
                startActivity(intent);
                break;
            case R.id.scrollableBtn:
                intent = new Intent(MainActivity.this, ScrollableActivity.class);
                startActivity(intent);
                break;
            case R.id.complexBtn:
                intent = new Intent(MainActivity.this, ComplexActivity.class);
                startActivity(intent);
                break;
        }
    }

}