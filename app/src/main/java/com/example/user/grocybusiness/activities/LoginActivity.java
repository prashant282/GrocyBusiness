package com.example.user.grocybusiness.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.grocybusiness.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button buttonSendOTP;
    EditText editTextNumber;
    FirebaseFirestore db;
    String userId;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonSendOTP = findViewById(R.id.btnSendOtp);
        editTextNumber = findViewById(R.id.reg_NumText);
        progressBar = findViewById(R.id.progressBar);
        db = FirebaseFirestore.getInstance();

        buttonSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                buttonSendOTP.setEnabled(false);
                String uphone = editTextNumber.getText().toString();
                final String phone_number = "+91" + uphone.substring(uphone.length() - 10);
                Query query = db.collection("Shopkeeper").whereEqualTo("pNumber", phone_number);

                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().getDocuments().size() > 0) {
                                Intent otp_intent = new Intent(LoginActivity.this, OtpActivity.class);

                                otp_intent.putExtra("phone_number", phone_number);
                                startActivity(otp_intent);
                                finish();
                                Toast.makeText(LoginActivity.this, "Otp sent! to number:- " + phone_number, Toast.LENGTH_LONG).show();
                            } else {
                                Intent signUp_intent = new Intent(LoginActivity.this, UploadDocActivity.class);
                                Toast.makeText(LoginActivity.this, "You are not Registered Yet!!", Toast.LENGTH_LONG).show();
                                startActivity(signUp_intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                            buttonSendOTP.setEnabled(true);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });

            }
        });
    }
//
//    private void showProgress() {
//        progressDialog = new ProgressDialog(LoginActivity.this);
//        progressDialog.show();
//        progressDialog.setContentView(R.layout.process_dialog);
//        progressDialog.getWindow().setBackgroundDrawableResource(
//                android.R.color.transparent
//        );
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}