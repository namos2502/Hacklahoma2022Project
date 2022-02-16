package com.example.chickenclinic.data.model;
import android.app.Application;

import com.example.chickenclinic.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;

import android.content.Intent;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chickenclinic.R;


public class FireApp extends Application {

    Button mLogin;
    EditText memailfield;
    EditText mpasswordfield;

    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();




    @Override
    public void onCreate() {
        super.onCreate();


        EditText mEmail = (EditText) memailfield.findViewById(R.id.emailusername);
        EditText mPass = (EditText) mpasswordfield.findViewById(R.id.password);
        Button button = (Button) mLogin.findViewById(R.id.temp);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(FireApp.this, LoginActivity.class));
                }
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSignIn();
                // Do something in response to button click
            }
        });

    }



            private void startSignIn() {
                String email = memailfield.getText().toString() ;
                String password = mpasswordfield.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(FireApp.this, "Can't leave blank", Toast.LENGTH_LONG).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(FireApp.this, "Sign In Problem", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }


    }




