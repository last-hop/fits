package com.example.fits;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register_act extends AppCompatActivity {


      EditText ipemail,ippass,ipcpass;
      Button cbutton;

      String epattern="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
      FirebaseAuth mauth;
    FirebaseUser muser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ipemail=findViewById(R.id.remail);
        ippass=findViewById(R.id.rpass);
        ipcpass=findViewById(R.id.rcpass);
        cbutton=findViewById(R.id.rcreatebutton);

        mauth=FirebaseAuth.getInstance();
        muser=mauth.getCurrentUser();

        cbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfAuth();
            }
        });



    }

    private void perfAuth() {
        String email=ipemail.getText().toString();
        String pass=ippass.getText().toString();
        String cpass=ipcpass.getText().toString();

        if(email.matches(epattern)){
            ipemail.setError("Enter context email");
        }
        else if(pass.isEmpty()||pass.length()<6){
            ippass.setError("enter correct password");
        }
        else if(!pass.equals(cpass)){
            ipcpass.setError("Password not match");
        }
        else{
            mauth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        sendUsernotextActivity();
                        Toast.makeText(register_act.this, "Registeration Sucessfull", Toast.LENGTH_SHORT).show(); 
                    }
                    else{
                        Toast.makeText(register_act.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }

                private void sendUsernotextActivity() {
                    Intent intent=new Intent(register_act.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
        }


    }
}