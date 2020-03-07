package com.example.login.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button logBt = findViewById(R.id.login);
        final Button regBt = findViewById(R.id.regActivity);
        logBt.setOnClickListener(this);
        regBt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login:
                mAuth = FirebaseAuth.getInstance();
                TextView email = findViewById(R.id.username);
                TextView password = findViewById(R.id.password);
                mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Success", "signInWithEmail:success");
                                    //FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.d("Failed", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
                break;
            case R.id.regActivity:
                Intent I = new Intent(getApplicationContext(),RegisterActivity.class);
startActivity(I);

                break;
        }
    }
}