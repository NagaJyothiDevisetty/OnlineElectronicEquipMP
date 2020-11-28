package com.example.onlineelectronicequipment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Registration extends AppCompatActivity {
    private Button createAccountBtn;
    private EditText userName,phonenumber,loginemail,loginpassword;
    private ProgressDialog loadingBar;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadingBar = new ProgressDialog(Registration.this);
        db = FirebaseFirestore.getInstance();

        userName = (EditText) findViewById(R.id.userName);
        phonenumber = (EditText) findViewById(R.id.phnumber);
        loginemail = (EditText) findViewById(R.id.email);
        loginpassword= (EditText) findViewById(R.id.password);
        createAccountBtn = (Button) findViewById(R.id.createAccount);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();

            }
        });

    }

    private void CreateAccount() {
        String  username=userName.getText().toString();
        String phone=phonenumber.getText().toString();
        String  email=loginemail.getText().toString();
        String password=loginpassword.getText().toString();

        if (TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "Please Enter your Nmae", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please write your phone number", Toast.LENGTH_SHORT).show();
        }

       else if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please write your Email", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please Enter password", Toast.LENGTH_SHORT).show();
        }

        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidateEmail(username,phone,email,password);
        }
    }

    private void ValidateEmail(String username, String phone, String email, String password) {
        HashMap<String, Object> userdataMap = new HashMap<>();
        HashMap<String, Object> admindataMap = new HashMap<>();
        userdataMap.put("username",username);
        userdataMap.put("email", email);
        userdataMap.put("phone", phone);
        userdataMap.put("password", password);

        db.collection("Registration").document().collection("Admin")
                .add(userdataMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Registration.this,"Your account is created Successfully",Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                        Intent intent=new Intent(Registration.this,Login.class);
                    startActivity(intent);
                    finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registration.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
