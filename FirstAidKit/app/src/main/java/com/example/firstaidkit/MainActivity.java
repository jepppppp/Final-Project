package com.example.firstaidkit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.MapsInitializer;
import com.google.android.material.navigation.NavigationView;
import com.google.common.collect.Maps;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

import pl.droidsonroids.gif.GifImageButton;

public class MainActivity extends AppCompatActivity {

    private GifImageButton mUsefulstuff;
    private GifImageButton mAnywhereEvents;
    private GifImageButton mOutdoorEvents;
    private GifImageButton mFirstAidMyths;
    private GifImageButton mFirstAidBasics;
    private GifImageButton mSeriousIncidents;
    private GifImageButton mPreventiveMeasures;
    private GifImageButton mCommonInHome;

    //    private ImageButton mProfile;
    private ImageButton mCall;
//    private ImageButton logout;

//    //content of icons
//
//    ListView listView;
//    private Object ListView;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private Button btnToggleDark;

//    SharedPreferences sharedPreferences;
//
//    public static final String fileName = "login";old
//    public static final String Username = "email";

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_EMAIL ="email";
    private static final String KEY_PASSWORD ="password";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        String password = sharedPreferences.getString(KEY_PASSWORD, null);

//        if (name != null || password != null){
//
//        }


//        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE); old


        setUpToolbar();
        /*---------------hooks-----------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        /*---------------toolbar-----------------*/
        setSupportActionBar(toolbar);

        /*---------------navigation drawer menu-----------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.nav_Map:

                        Intent intentt = new Intent(MainActivity.this,MapsActivity.class);
                        startActivity(intentt);
                        break;

                    case R.id.nav_emergencyhotlines:
                        Intent intent1 = new Intent(MainActivity.this,emergencyhotlines.class);
                        startActivity(intent1);
                        break;




//                    case R.id.nav_profile:
//                        Intent intent2 = new Intent(MainActivity.this, AccountProfile.class);
//                        startActivity(intent2);
//                        break;

                    case R.id.nav_BMI:
                        Intent intent3 = new Intent(MainActivity.this, BMIRecords.class);
                        startActivity(intent3);
                        break;



//                    case R.id.nav_Map:
//                        Intent intent5 = new Intent(MainActivity.this,MapsActivity.class);
//                        startActivity(intent5);
//                        break;



                    case R.id.nav_logout:
                        Intent intent4 = new Intent(MainActivity.this , Login.class);
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(),Login.class));
                        startActivity(intent4);
                        SharedPreferences.Editor editor =sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        Toast.makeText(MainActivity.this, "Log out succesfully", Toast.LENGTH_SHORT).show();
                        finish();
                        break;


                    /* session sharedreference */
//                        SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
//                        sessionManagement.removeSession(); session sharedreference
//
//                        moveToLogin();
                    /* session sharedreference */


                    //Paste your privacy policy link

//                    case  R.id.nav_Policy:{
//
//                        Intent browserIntent  = new Intent(Intent.ACTION_VIEW , Uri.parse(""));
//                        startActivity(browserIntent);
//
//                    }
                    //       break;

                }
                return false;
            }

            /* session sharedreference */
//            private void moveToLogin() {
//                Intent intent = new Intent(MainActivity.this,Login.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
            /* session sharedreference */
        });





        mUsefulstuff        = findViewById(R.id.usefulstuff);
        mAnywhereEvents     = findViewById(R.id.anywhereevents);
        mOutdoorEvents      = findViewById(R.id.outdoor);
        mFirstAidMyths      = findViewById(R.id.firstaidmyths);
        mFirstAidBasics     = findViewById(R.id.firstaidbasics);
        mSeriousIncidents   = findViewById(R.id.seriousincidents);
        mPreventiveMeasures = findViewById(R.id.preventivemeasures);
        mCommonInHome       = findViewById(R.id.commoninhome);

//        mProfile = findViewById(R.id.profile);
//        mCall = findViewById(R.id.emergencycall);
//        logout = findViewById(R.id.logoutbtn);

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(getApplicationContext(),Login.class));
//            }
//        });



//        //content of icons
//        ListView = findViewById(R.id.listview);
//        String[] values = new String[]{
//
//                "First Aid Basics"
//
//        };
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1,
//                android.R.id.text1, values);
//
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                //first item listview
//                if (position == 0) {
//                    Intent intent = new Intent(view.getContext(), FirstAidBasics.class);
//                    startActivity(intent);
//                }
//            };
//        });




        mUsefulstuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Usefulstuff.class));
            }
        });

        mAnywhereEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AnywhereEvents.class));
            }
        });

        mOutdoorEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),OutdoorEvents.class));
            }
        });

        mFirstAidMyths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FirstAidMyths.class));
            }
        });

        mFirstAidBasics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FirstAidBasics.class));
            }
        });

        mSeriousIncidents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SeriousIncidents.class));
            }
        });

        mPreventiveMeasures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PreventiveMeasures.class));
            }
        });

        mCommonInHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CommonInHome.class));
            }
        });

//        mProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),contentofprofile.class));
//            }
//        });

//        mCall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),emergencyhotlines.class));
//            }
//        });


    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }


    }
    public void darkbutton(View view) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

    }
    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.purple_200));
        actionBarDrawerToggle.syncState();
    }

}