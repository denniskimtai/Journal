package com.codegreed_devs.journalalc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail, editTextPassword;
    private Button btnRegister;
    private TextView textViewSignIn;

    ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            finish();
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
        }

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword =  findViewById(R.id.editTextPassword);
        progressDialog = new ProgressDialog(this);

        btnRegister =  findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        textViewSignIn = findViewById(R.id.textViewSignIn);
        textViewSignIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnRegister){
            registerUser();
        }
        if (v == textViewSignIn){
            finish();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //Email is empty
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
            //Stop execution
            return;
        }
        //Password is empty
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter your Password", Toast.LENGTH_SHORT).show();
            //stop execution
            return;
        }

        //show progressbar while registering user
        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            finish();
                            Toast.makeText(MainActivity.this, "Registration successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(MainActivity.this, "Registration failed. Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
