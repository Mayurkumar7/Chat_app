package com.example.chattingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chattingapp.R;
import com.example.chattingapp.databinding.ActivitySignInBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListener();
    }
    private void setListener(){
      binding.textCreateNewAccount.setOnClickListener(v->
              startActivity(new Intent(getApplicationContext() , SignInActivity.class)));
      binding.buttonSignIn.setOnClickListener(v-> addDataToFireStore());
    }

    private  void addDataToFireStore(){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String , Object>  data = new HashMap<>();
        data.put("first_name" , "Chirag");
        data.put("last_name" , "Kachhadiya");
        database.collection("users")
                .add(data)
                .addOnSuccessListener( documentReference -> {
                    Toast.makeText(getApplicationContext() , "Data Inserted" , Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(exception ->{
                    Toast.makeText(getApplicationContext(),exception.getMessage() , Toast.LENGTH_SHORT).show();
                });
    }
}

