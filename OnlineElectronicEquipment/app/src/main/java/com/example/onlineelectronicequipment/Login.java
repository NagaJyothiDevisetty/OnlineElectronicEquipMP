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

import com.example.onlineelectronicequipment.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {
    private Button loginBtn,registerBtn;
    private EditText loginemail, loginpassword;
    ProgressDialog loadingBar;
    private String parentDbName="Registration";
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");

        loadingBar=new ProgressDialog(Login.this);
        db=FirebaseFirestore.getInstance();

        loginBtn = (Button) findViewById(R.id.signIn);
        registerBtn = (Button) findViewById(R.id.register);
        loginemail = (EditText) findViewById(R.id.email);
        loginpassword = (EditText) findViewById(R.id.password);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,Registration.class);
                startActivity(intent);
            }
        });

         loginBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 LoginUser();
             }
         });
    }

    private void LoginUser()
    {
        String email=loginemail.getText().toString();
        String password=loginpassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please write your emailID",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this,"Please write your Password",Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking your credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            AllowAccessToAccount(email,password);
        }
    }
    private void AllowAccessToAccount(final  String email,final String password)
    {
        db.collection(parentDbName)
                .get()
              .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
              {
                  @Override
                  public void onComplete(@NonNull Task<QuerySnapshot> task)
                  {
                      loadingBar.dismiss();
                      if(task.isSuccessful()){

                          for (QueryDocumentSnapshot document : task.getResult()) {
                              Users usersData = document.toObject(Users.class);
                              if(usersData.getEmail().equals(email)){
                                  if(usersData.getPassword().equals(password)){
                                      Toast.makeText(Login.this, "logged in Successfully", Toast.LENGTH_SHORT).show();
                                      loadingBar.dismiss();
                                      Intent intent = new Intent(Login.this, HomePage.class);
                                      startActivity(intent);
                                      finish();
                                  }
                              }

                          }
                      }
                        else {
                            Toast.makeText(Login.this, "Incorrect credentials,Please try again ", Toast.LENGTH_SHORT).show();

                        }
                      }
                  });
              }
    }
