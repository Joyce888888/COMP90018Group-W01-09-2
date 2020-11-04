package com.example.comp90018.Activity.Shake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.comp90018.Activity.Home.HomePageAdapter;
import com.example.comp90018.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class CommActivity extends AppCompatActivity {
    private String commentText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        TextView commentTitle = (TextView) findViewById(R.id.commentTextView);
        EditText comments = (EditText) findViewById(R.id.commentEditText);
        commentText = comments.getText().toString();
        Button cancel = (Button) findViewById(R.id.cancelButton);

        //cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                onBackPressed();
            }
        });

        //implement the comment requesting
        Button comment = (Button) findViewById(R.id.commentButton);
        comment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                createIntent(commentText);
            }
        });
    }
    private void createIntent(String string){
        //TODO: 把String传输给服务器端
        //....

        Intent intent = new Intent(CommActivity.this, UserViewActivity.class);
        startActivity(intent);
    }
}