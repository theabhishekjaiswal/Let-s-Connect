package com.theakj.letsconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.giphy.sdk.analytics.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class signUp extends AppCompatActivity {

    FirebaseAuth auth;
    TextView signIn;
    EditText emailBox,nameBox,passwordBox,cont;
    Button signupBtn;
    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);


        auth=FirebaseAuth.getInstance();
        database=FirebaseFirestore.getInstance();

        emailBox=findViewById(R.id.emailBox);
        nameBox=findViewById(R.id.nameBox);
        passwordBox=findViewById(R.id.passwordBox);
        signupBtn=findViewById(R.id.signupBtn);
        cont=findViewById(R.id.cont);

        signIn=findViewById(R.id.signin);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signUp.this,loginPage.class));
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,email,pass,contnum;
                name=nameBox.getText().toString();
                email=emailBox.getText().toString();
                pass=passwordBox.getText().toString();
                contnum=cont.getText().toString();
                UserProfile user=new UserProfile();
                user.setName(name);;
                user.setContact(contnum);
                user.setEmail(email);;

                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            database.collection("users")
                                    .document(email + "").set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(signUp.this, "Account Created Succesfully", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    });


                        }else{
                            Toast.makeText(signUp.this, Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}