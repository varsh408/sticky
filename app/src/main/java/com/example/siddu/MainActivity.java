package com.example.siddu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private TextView myNOtetext;
private ImageView addNewNoteMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myNOtetext=(TextView)findViewById(R.id.textMyNotes);
        addNewNoteMain=(ImageView)findViewById(R.id.imageAddNoteMain);
        addNewNoteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,NewNoteActivity.class);
                startActivity(intent);
            }
        });
    }
}
