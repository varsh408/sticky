package com.example.siddu;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    View nview;
    TextView textTitle, textTime;
    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        nview=itemView;
textTime=nview.findViewById(R.id.note_title);
        textTitle=nview.findViewById(R.id.note_time);
    }
    public void setNoteTitle(String title){
        textTitle.setText(title);

    }
    public void setnoteTitle(String time){
        textTime.setText(time);

    }
}

