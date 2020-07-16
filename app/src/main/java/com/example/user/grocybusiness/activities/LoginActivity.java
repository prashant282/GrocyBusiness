package com.example.user.grocybusiness.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.grocybusiness.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button buttonSendOTP;
    EditText editTextNumber;
    FirebaseFirestore fstore;
    String userId;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonSendOTP = findViewById(R.id.btnSendOtp);
        editTextNumber = findViewById(R.id.reg_NumText);


        buttonSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showProgress();
                String uphone = editTextNumber.getText().toString();
                String phone_number = "+91" + uphone.substring(uphone.length() - 10);

                Intent otp_intent = new Intent(LoginActivity.this, OtpActivity.class);

                otp_intent.putExtra("phone_number", phone_number);
                startActivity(otp_intent);
                progressDialog.dismiss();

                Toast.makeText(LoginActivity.this, "Otp sent! to number:- " + phone_number, Toast.LENGTH_LONG).show();

            }
        });
    }

    private void showProgress() {
        Context context;
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.process_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
    }
}