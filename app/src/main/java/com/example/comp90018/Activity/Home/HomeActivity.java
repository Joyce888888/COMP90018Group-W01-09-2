package com.example.comp90018.Activity.Home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.comp90018.Activity.Map.MapActivity;
import com.example.comp90018.Activity.Shake.ShakeActivity;
import com.example.comp90018.Activity.fragments.ProfileFragment;
import com.example.comp90018.DataModel.UserModel;
import com.example.comp90018.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class HomeActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = "HomeActivity";
    private RelativeLayout main_body;
    private ImageButton button_home;
    private ImageButton button_search;
    private ImageButton button_map;
    private ImageButton button_shake;
    private ImageButton button_profile;
    private LinearLayout nav_bottons;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private UserModel user = new UserModel();

    public UserModel getUser(){
        return this.user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                if(getIntent().getExtras().getBoolean("backToProfile")) {
                    backToProfile();
                }
            } else{
                setMain();
            }
        }

    }

    private void initView(){
        main_body=findViewById(R.id.main_body);
        button_home=findViewById(R.id.button_home);
        button_search=findViewById(R.id.button_search);
        button_map=findViewById(R.id.button_map);
        button_shake=findViewById(R.id.button_shake);
        button_profile=findViewById(R.id.button_profile);
        nav_bottons=findViewById(R.id.nav_bottons);
        button_home.setOnClickListener(this);
        button_search.setOnClickListener(this);
        button_map.setOnClickListener(this);
        button_shake.setOnClickListener(this);
        button_profile.setOnClickListener(this);
    }

    private void setSelectStatus(int index) {
        switch (index){
            case 0:
                //setBackgroundColor
                button_home.setBackgroundColor(Color.parseColor("#fafaaf"));
                //set text color
                button_search.setBackgroundColor(Color.parseColor("#ffffff"));
                button_map.setBackgroundColor(Color.parseColor("#ffffff"));
                button_shake.setBackgroundColor(Color.parseColor("#ffffff"));
                button_profile.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
            case 1:
                button_search.setBackgroundColor(Color.parseColor("#fafaaf"));

                button_home.setBackgroundColor(Color.parseColor("#ffffff"));
                button_map.setBackgroundColor(Color.parseColor("#ffffff"));
                button_shake.setBackgroundColor(Color.parseColor("#ffffff"));
                button_profile.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
            case 2:
                button_map.setBackgroundColor(Color.parseColor("#fafaaf"));

                button_search.setBackgroundColor(Color.parseColor("#ffffff"));
                button_home.setBackgroundColor(Color.parseColor("#ffffff"));
                button_shake.setBackgroundColor(Color.parseColor("#ffffff"));
                button_profile.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
            case 3:
                button_shake.setBackgroundColor(Color.parseColor("#fafaaf"));

                button_search.setBackgroundColor(Color.parseColor("#ffffff"));
                button_home.setBackgroundColor(Color.parseColor("#ffffff"));
                button_map.setBackgroundColor(Color.parseColor("#ffffff"));
                button_profile.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
            case 4:
                button_profile.setBackgroundColor(Color.parseColor("#fafaaf"));

                button_search.setBackgroundColor(Color.parseColor("#ffffff"));
                button_home.setBackgroundColor(Color.parseColor("#ffffff"));
                button_shake.setBackgroundColor(Color.parseColor("#ffffff"));
                button_map.setBackgroundColor(Color.parseColor("#ffffff"));
                break;
        }
    }


    private void getUserData(){
        DocumentReference docRef = db.collection("users").document(mAuth.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        user = document.toObject(UserModel.class);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new HomePageFragment()).commit();
                setSelectStatus(0);
                break;
            case R.id.button_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new SearchFragment()).commit();
                setSelectStatus(1);
                break;
            case R.id.button_map:
                Intent intentmap = new Intent(HomeActivity.this, MapActivity.class);
                startActivity(intentmap);
                break;
            case R.id.button_shake:
                Intent intent = new Intent(HomeActivity.this, ShakeActivity.class);
                startActivity(intent);
                break;
            case R.id.button_profile:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment fragment = new ProfileFragment(mAuth.getUid());
                ft.replace(R.id.main_body, fragment);
                ft.commit();
                setSelectStatus(4);
                break;
        }
    }

    private void setMain() {
        this.getSupportFragmentManager().beginTransaction().add(R.id.main_body,new HomePageFragment()).commit();
        setSelectStatus(0);
    }

    public void backToProfile(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new ProfileFragment(mAuth.getUid());
        ft.replace(R.id.main_body, fragment);
        ft.commit();
        setSelectStatus(4);
    }
}