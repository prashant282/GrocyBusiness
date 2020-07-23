package com.example.user.grocybusiness.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.user.grocybusiness.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseUser currentUser;
    LinearLayout mainLayout;
    ShimmerFrameLayout shimmerAnimation;
    private FirebaseAuth mAuth;
    RelativeLayout noInternetLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = mAuth.getCurrentUser();

        mainLayout = findViewById(R.id.main_linear_layout);
        shimmerAnimation = findViewById(R.id.shimmerAnimation);
        noInternetLayout = findViewById(R.id.no_internet_layout);

        if (!isNetworkConnected()) {
            shimmerAnimation.stopShimmer();
            shimmerAnimation.setVisibility(View.GONE);
            noInternetLayout.setVisibility(View.VISIBLE);
            return;
        }

        Query query = db.collection("ShopKeeper").whereEqualTo("pNumber", currentUser.getPhoneNumber());
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                HashMap<String, Object> document = (HashMap<String, Object>) queryDocumentSnapshots.getDocuments().get(0).getData();
                if (Integer.parseInt("" + document.get("no_of_shops")) == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("oNumber", (String) document.get("pNumber"));
                    bundle.putString("oCity", (String) document.get("ownerCity"));
                    bundle.putString("oName", (String) document.get("ownerName"));
                    bundle.putString("oEmail", (String) document.get("ownerEmail"));
                    Toast.makeText(MainActivity.this, "Please Register atleast one shop to continue", Toast.LENGTH_LONG).show();
                    Intent add_shop_intent = new Intent(MainActivity.this, AddShopActivity.class);
                    startActivity(add_shop_intent);
                    finish();
                } else {
                    shimmerAnimation.stopShimmer();
                    shimmerAnimation.setVisibility(View.GONE);
                    mainLayout.setVisibility(View.VISIBLE);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                shimmerAnimation.stopShimmer();
                shimmerAnimation.setVisibility(View.GONE);
                mainLayout.setVisibility(View.VISIBLE);
            }
        });

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}