package com.example.chatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button button;
    EditText email,password;
    TextView signup,ForgetPass;
    FirebaseAuth auth;
    String emailPattern ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    android.app.ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.setCancelable(false);

        auth =FirebaseAuth.getInstance();
        button=findViewById(R.id.Logbutton);
        email=findViewById(R.id.editTextLogEmail);
        password=findViewById(R.id.editTextLogPassword);
        signup=findViewById(R.id.textView11);
        ForgetPass=findViewById(R.id.textView77);

        ForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ResetPassword.class));
                finish();
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(getApplicationContext(), Registration.class));

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                if ((TextUtils.isEmpty(Email))){
                    progressDialog.dismiss();
                    Toast.makeText(Login.this, "Enter The Email", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(Password)){
                    progressDialog.dismiss();
                    Toast.makeText(Login.this, "Enter The Password", Toast.LENGTH_SHORT).show();
                } else if (!Email.matches(emailPattern)) {
                    progressDialog.dismiss();
                    email.setError("Give Proper Email Address");

                } else if (password.length()<6) {
                    progressDialog.dismiss();
                    password.setError("More Then Six Characters");
                    Toast.makeText(Login.this, "Passowrd Needs To Be Longer Then Six Characters", Toast.LENGTH_SHORT).show();

                }else {
                    auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.show();
                                try {
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } catch (Exception e) {
                                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });

    }
}