package com.example.hp.madose;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by HP on 18/04/2018.
 */

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button connect= findViewById(R.id.button2);
        Button account= findViewById(R.id.button4);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this,Authentification.class));
                finish();
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Welcome.this,Utilisateur.class);
                intent.putExtra("status","new user request");
                startActivity(intent);
                MyApplication.setNewAccount(true);
                finish();
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();

        FirebaseUser currentUser = MyApplication.mAuth.getCurrentUser();
        if (MyApplication.getmAuth().getCurrentUser() !=null && ! MyApplication.getmAuth().getCurrentUser().getEmail().toString().equals("test@idconsulting.ie")){
            onAuthSuccess(MyApplication.getmAuth().getCurrentUser());
            Log.i("UNO",MyApplication.getmAuth().getCurrentUser().getEmail());
        }
    }

    private void onAuthSuccess(FirebaseUser user){
        String username = usernameFromEmail(user.getEmail());

        startActivity(new Intent(Welcome.this, Acceuil.class));


    }
    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }
}
