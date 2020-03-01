package com.example.loginactivity.ui.login;

import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.loginactivity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    FirebaseAuth mAuth;
// ...
// Initialize Firebase Auth


    @Override
    protected void onStart() {
        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);


        loginButton.setOnClickListener((View.OnClickListener) this);


    }

    @Override
    public void onClick(View v) {
        mAuth = FirebaseAuth.getInstance();
        final EditText email = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        switch (v.getId()) {
            case R.id.login:
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                   // Log.d(Tag, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                   // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }


                            }
                        });


        }

    }
}