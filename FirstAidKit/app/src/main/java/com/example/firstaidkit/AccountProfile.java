package com.example.firstaidkit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountProfile extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference dbReference;

    String userID;
    TextView txtDisplay;

//    TextView mfullnameTv,mEmailTv,mPassowrdTv,mPhoneTv;
//    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_profile);

//        mfullnameTv = findViewById(R.id.fullName);
//        mEmailTv = findViewById(R.id.Email);
//        mPassowrdTv = findViewById(R.id.password);
//        mPhoneTv = findViewById(R.id.phone);
//
//        preferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
//
//        String email = preferences.getString("EMAIL","");
//        mEmailTv.setText(email);
//
//        String fullname = preferences.getString("FULLNAME", "");
//        mfullnameTv.setText(fullname);
//
//        String password = preferences.getString("PASSWORD", "");
//        mPassowrdTv.setText(password);
//
//        int phone = preferences.getInt("PHONE", 0);
//        mPhoneTv.setText(""+phone);


        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        dbReference= FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();

        txtDisplay=findViewById(R.id.txtDisplay);

        dbReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user= snapshot.getValue(User.class);

                if (user!= null){
                    String fullName,email,password,phone;
                    fullName = user.fullName;
                    email = user.email;
                    password = user.password;
                    phone = user.phone;

                    txtDisplay.setText("Full name: "+fullName+"\n Email: "+email+"\nPassword: "+password+"\nPhone: "+phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AccountProfile.this, "Error", Toast.LENGTH_LONG).show();
            }
        });

    }
}