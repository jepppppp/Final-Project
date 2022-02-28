package com.example.firstaidkit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    public static final String TAG1 = "TAG,";
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;



    //    FirebaseAuth fAuth;
    FirebaseAuth mAuth;
//    FirebaseFirestore fStore;
//    String userID;

    SharedPreferences sharedPreferences;
//    private static final String SHARED_PREF_NAME="mypref";
//    private static final String KEY_EMAIL ="email";
//    private static final String KEY_PASSWORD ="password";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.createText);

        mAuth = FirebaseAuth.getInstance();
//        fAuth = FirebaseAuth.getInstance();
//        fStore = FirebaseFirestore.getInstance();


//        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);



        // login//

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });


//        if (fAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//
//        }


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //when click a button put data on shared preference..
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString(KEY_EMAIL,mEmail.getText().toString());
//                editor.putString(KEY_PASSWORD,mPassword.getText().toString());
//                editor.apply();


                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String fullName = mFullName.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();

//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("EMAIL",email);
//                editor.putString("PASSWORD",password);
//                editor.putString("FULLNAME",fullName);
//                editor.putInt("PHONE", Integer.parseInt(phone));
//                editor.apply();


                if (TextUtils.isEmpty(fullName)) {
                    mFullName.setError("Full name is required");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required");
                    return;
                }

                if (password.length() < 8) {
                    mPassword.setError("Password must be at least 8 numbers");
                    return;
                }
                if (phone.length() != 11  )  {
                    mPhone.setError("Please Enter 11 digit Phone Number.");
                    return;
                }

//                Intent intent = new Intent(Register.this,AccountProfile.class);
//                startActivity(intent);
//                finish();


                //        register the user in firebase

//                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((task) -> {
//                            if (task.isSuccessful()) {
//
//                                User users = new User(fullName, email, password, phone);
//                                Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();
//                                userID = fAuth.getCurrentUser().getUid();
//                                DocumentReference documentReference = fStore.collection("users").document(userID);
//                                Map<String, Object> user = new HashMap<>();
//                                user.put("fName", fullName);
//                                user.put("email", email);
//                                user.put("phone", phone);
//                                documentReference.set(user).addOnSuccessListener((OnSuccessListener) (aVoid) -> {
//                                    Log.d(TAG, "onSuccess: user Profile is created for" + userID);
//                                }).addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Log.d(TAG, "onFailure: " + e.toString());
//                                    }
//
//                                });
//                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
//
//
//                            } else {
//                                Toast.makeText(Register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            }
//        });





                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    User user = new User(fullName, email, password, phone);


                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                Toast.makeText(Register.this, "User Created ", Toast.LENGTH_LONG).show();
                                                Intent i = new Intent(getApplicationContext(), Login.class);
                                                startActivity(i);
                                            } else {
                                                Toast.makeText(Register.this, "Registration Failed!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(Register.this, "Registration Failed!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });



    }
}