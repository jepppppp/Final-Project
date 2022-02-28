package com.example.firstaidkit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.api.Backend;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText mEmail, mPassword;
    private Button   mLoginBtn;
    private TextView mCreateBtn;

//    private FirebaseAuth fAuth;

    FirebaseAuth mAuth;



//    SharedPreferences sharedPreferences; old
//
//    public  static final String fileName="Login";
//    public  static final String Username="email"; old
//    public  static final String Password="password";

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_EMAIL ="email";
    private static final String KEY_PASSWORD ="password";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String email = sharedPreferences.getString(KEY_EMAIL,null);

        if (email != null){
            Intent intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);
        }


        /* session sharedreference */
//        User user = new User(12,"jjj");
//
//        //1. login and save session
//        SessionManagement sessionManagement = new SessionManagement(Login.this);
//        sessionManagement.saveSession(user);
//
//        //2. step
//        moveToMainActivity();
        /* session sharedreference */



//        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE);
//        if (sharedPreferences.contains(Username)){
//            Intent i = new Intent(Login.this,MainActivity.class);         OLD
//            startActivity(i);
//        }



        mEmail      = (EditText)    findViewById(R.id.Email);
        mPassword   = (EditText)    findViewById(R.id.password);

        mLoginBtn   = (Button)      findViewById(R.id.loginBtn);
        mCreateBtn  = (TextView)    findViewById(R.id.createText);

//        fAuth = FirebaseAuth.getInstance();

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //when click a button put data on shared preference..
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_EMAIL,mEmail.getText().toString());
                editor.putString(KEY_PASSWORD,mPassword.getText().toString());
                editor.apply();


                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                    return;
                }

                if(password.length()<8){
                    mPassword.setError("Password must be at least 8 numbers");
                    return;
                }
//                SharedPreferences.Editor editor =sharedPreferences.edit();
//                editor.putString(Username,email);
//                editor.commit(); old


                // authenticate the user

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"Logged in successfully", Toast.LENGTH_LONG).show();
//                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Login.this,"Error ! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            mEmail.setText("");
                            mEmail.requestFocus();
                            mPassword.setText("");
                        }

                    }
                });
            }
        });


        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });


    }


    /* session sharedreference */
//    private void moveToMainActivity() {
//        Intent intent = new Intent(Login.this,MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        checkSession();
//
//    }
//
//    private void checkSession() {
//        //check if user is logged in
//        //if user is logged in --> move to mainactivity
//
//        SessionManagement sessionManagement = new SessionManagement(Login.this);
//        int userID = sessionManagement.getSession();
//
//        if (userID != -1){
//            //user id logged in and move to mainactivity
//            moveToMainActivity();
//        }else{
//            //do nothing
//        }
//    } /* session sharedreference */

}