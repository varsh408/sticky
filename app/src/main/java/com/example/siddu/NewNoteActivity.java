package com.example.siddu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class NewNoteActivity extends AppCompatActivity {
private Button saveButton;
private String content, title;
private EditText noteTitle, noteNote;
private ImageView backButton, deleteButton;
    private ProgressDialog loadingbar;

  private String  saveCurrentDate,saveCurrentTime;
private TextView noteText;
    private String productRandomKey,downloadImageUrl;

    private StorageReference ProductImageRef;
    private DatabaseReference ProductsRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        ProductImageRef= FirebaseStorage.getInstance().getReference().child("Notes notes");
        ProductsRef= FirebaseDatabase.getInstance().getReference().child("Notes");


        saveButton=(Button)findViewById(R.id.btnCreate);
        noteTitle=(EditText)findViewById(R.id.inputNoteTitle);
        noteNote=(EditText)findViewById(R.id.inputNote);
        backButton=(ImageView)findViewById(R.id.imageBACK);
        deleteButton=(ImageView)findViewById(R.id.ImageSave);
        noteText=(TextView)findViewById(R.id.inNote);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 title= noteTitle.getText().toString().trim();
 content =noteNote.getText().toString().trim();
if(!TextUtils.isEmpty(title)&& !TextUtils.isEmpty(content)){
    createNote(title,content);
}else{
    Toast.makeText(NewNoteActivity.this, "Write a Note with a title ",Toast.LENGTH_SHORT).show();

}

            }
        });
    }
    private void createNote(String title, String content){

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("MMM dd,yyy");
         saveCurrentDate = currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
         saveCurrentTime = currentTime.format(calendar.getTime());
        productRandomKey=saveCurrentDate+saveCurrentTime;
        final StorageReference filepath=ProductImageRef.child(productRandomKey);
saveProductInfoToDAtabase();
    }

    private void saveProductInfoToDAtabase() {
        HashMap<String,Object> productMap=new HashMap<>();
        productMap.put("pid",productRandomKey);
        productMap.put("date",saveCurrentDate);
        productMap.put("time",saveCurrentTime);
        productMap.put("notetitle",title);
        productMap.put("note",content);
        ProductsRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Intent intent=new Intent(NewNoteActivity.this,MainActivity.class);
                            startActivity(intent);

                            Toast.makeText(NewNoteActivity.this,"Note Added.....",Toast.LENGTH_SHORT).show();
                        }
                        else {

                            String message=task.getException().toString();
                            Toast.makeText(NewNoteActivity.this,"Error"+message,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
