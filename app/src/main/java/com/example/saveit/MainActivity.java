package com.example.saveit;

import android.content.IntentSender;
import android.os.Bundle;

import com.List.View.Data.ViewData;
import com.camera.module.CameraInstance;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;
import android.content.Intent.*;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.autofill.AutofillValue;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CameraInstance camInstance = CameraInstance.getInstance();
    ArrayList imageDB = null;
    ViewData mView = ViewData.getInstance();

    ArrayAdapter < String > adapter = null;
    ListView listview = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageDB = new ArrayList<String>();
        FloatingActionButton fab = findViewById(R.id.fab);

        // Deep Copy to avoid IllegalException
        List< String > ListElementsArrayList = mView.getListData();

        adapter = new ArrayAdapter < String >
                (MainActivity.this, android.R.layout.simple_list_item_1,
                        ListElementsArrayList);


        listview = findViewById(R.id.listImages);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mView.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Call","ItemClickListener"+position);
                android.content.Intent intent = new android.content.Intent(MainActivity.this, ImageViewer.class);
                intent.putExtra("fileName",mView.getImageName(position));
                startActivity(intent);
            }
        });

    }
    public void sendMessage(View view) {

        Log.e("Call SendMessgae","Send");
        android.content.Intent intent = new android.content.Intent(MainActivity.this, ImageCaptureActivity.class);
        startActivity(intent);
        adapter.notifyDataSetChanged();
    }
}