package com.bmpl.gridview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;

    ArrayAdapter arrayAdapter;

    ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView)findViewById(R.id.gridView);

        arrayList = new ArrayList<>();

        arrayList.add(R.mipmap.ic_launcher);
        arrayList.add(R.mipmap.ic_launcher);
        arrayList.add(R.mipmap.ic_launcher);
        arrayList.add(R.mipmap.ic_launcher);

        ImageView imageView = new ImageView(this);

        //arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayList);

        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });

    }
}
