package com.example.firstaidkit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.firstaidkit.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {

    EditText editText_name,editText_gender,editText_category;
    ImageView imageView_save;
    Notes notes;
    boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        imageView_save = findViewById(R.id.imageView_save);
        editText_name = findViewById(R.id.editText_name);
        editText_gender= findViewById(R.id.editText_gender);
        editText_category = findViewById(R.id.editText_category);

        notes = new Notes();
        try {
            notes = (Notes) getIntent().getSerializableExtra("old_note");
            editText_name.setText(notes.getTitle());
            editText_gender.setText(notes.getNotes());
            editText_category.setText(notes.getResult());
            isOldNote = true;
        }catch (Exception e){
            e.printStackTrace();
        }


        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleName = editText_name.getText().toString();
                String descripGender =editText_gender.getText().toString();
                String category = editText_category.getText().toString();

                if (titleName.isEmpty()){
                    Toast.makeText(NotesTakerActivity.this, "Please enter name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (descripGender.isEmpty()){
                    Toast.makeText(NotesTakerActivity.this, "Please enter gender!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (category.isEmpty()){
                    Toast.makeText(NotesTakerActivity.this, "Please enter category!", Toast.LENGTH_SHORT).show();
                    return;
                }

                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");
                Date date = new Date();

                if (!isOldNote){
                    notes = new Notes();
                }


                notes.setTitle(titleName);
                notes.setNotes(descripGender);
                notes.setResult(category);
                notes.setDate(formatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("note", notes);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}