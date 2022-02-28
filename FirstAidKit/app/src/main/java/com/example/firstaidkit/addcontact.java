package com.example.firstaidkit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class addcontact extends AppCompatActivity {

    private EditText phone;
    private ImageButton call;


    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;


    DatabaseReference AddContactDbRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);

        phone = findViewById(R.id.addnumber);
        call  = findViewById(R.id.call);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        AddContactDbRef = FirebaseDatabase.getInstance().getReference().child("Add Contact");






        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addNumber = phone.getText().toString();

                users user = new users(addNumber);

                AddContactDbRef.push().setValue(user);
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+addNumber));
                startActivity(intent);

//                users user = new users(addNumber);
//
//                FirebaseDatabase.getInstance().getReference("Add Contact Number")
//
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//
//                                if (task.isSuccessful()) {
//
//                                    Intent intent = new Intent(Intent.ACTION_CALL);
//                                    intent.setData(Uri.parse("tel:"+addNumber));
//                                    startActivity(intent);
//                                } else {
//                                    Toast.makeText(getApplicationContext(),"Contact Number is Incorrect",Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });





////                Intent intent = new Intent(Intent.ACTION_CALL);
////                intent.setData(Uri.parse("tel:"+addNumber));
////                startActivity(intent);
            }
        });
    }
}