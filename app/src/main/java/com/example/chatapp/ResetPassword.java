package com.example.chatapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ResetPassword extends AppCompatActivity {
    EditText nemail;
    TextView logbtn;
    Button nresetbtn;
    String Email;
    FirebaseAuth Auth;
    ProgressDialog progressDialog;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Auth=FirebaseAuth.getInstance();
        nemail=findViewById(R.id.editTextforEmail);
        nresetbtn=findViewById(R.id.Fogbutton);
        logbtn =findViewById(R.id.loginbut);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saing...");
        progressDialog.setCancelable(false);

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPassword.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

        nresetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                validateData();
            }
        });
    }

    private void validateData() {
        Email = nemail.getText().toString();
        if (Email.isEmpty()){
            nemail.setError("Required");
        }else {
            Auth.sendPasswordResetEmail(Email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                progressDialog.dismiss();
                                Toast.makeText(ResetPassword.this, "check your Email", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ResetPassword.this, Login.class));
                                finish();
                            }else {
                                progressDialog.dismiss();
                                Toast.makeText(ResetPassword.this, "Error:" + Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
