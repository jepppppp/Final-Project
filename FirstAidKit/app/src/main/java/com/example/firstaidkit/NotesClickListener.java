package com.example.firstaidkit;

import androidx.cardview.widget.CardView;

import com.example.firstaidkit.Models.Notes;

public interface NotesClickListener {
    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);
}
