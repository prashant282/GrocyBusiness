package com.example.user.grocybusiness.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.fragments.AccountFragment;
import com.example.user.grocybusiness.fragments.ItemsFragment;
import com.example.user.grocybusiness.fragments.NotificationFragment;
import com.example.user.grocybusiness.fragments.OrdersFragment;
import com.example.user.grocybusiness.fragments.SettingFragment;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseFirestore db;
    FirebaseUser currentUser;
    ShimmerFrameLayout shimmerAnimation;
    RelativeLayout noInternetLayout;
    static final float END_SCALE = 0.7f;
    CoordinatorLayout coordinatorLayout;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ItemsFragment itemsFragment;
    private NotificationFragment notificationFragment;
    private OrdersFragment ordersFragment;
    private SettingFragment settingFragment;
    private AccountFragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = mAuth.getCurrentUser();

        shimmerAnimation = findViewById(R.id.shimmerAnimation);
        noInternetLayout = findViewById(R.id.no_internet_layout);

        bottomNavigationView = findViewById(R.id.main_bottom_nav);
        navigationView=findViewById(R.id.nav_view);
        frameLayout = findViewById(R.id.main_frame_layout);
        coordinatorLayout = findViewById(R.id.coordinator);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.main_toolbar);

        itemsFragment = new ItemsFragment();
        notificationFragment = new NotificationFragment();
        ordersFragment = new OrdersFragment();
        settingFragment = new SettingFragment();
        accountFragment = new AccountFragment();


        View view = navigationView.getHeaderView(0);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openNavDrawer, R.string.closeNavDrawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        navigationView.setItemIconTintList(null);
        navigationView.getMenu().getItem(0).setChecked(true);
        toolbar.setNavigationIcon(R.drawable.icon_menu);
        animateNavigationDrawer();


        shimmerAnimation.setVisibility(View.VISIBLE);
        coordinatorLayout.setVisibility(View.GONE);

        if (!isNetworkConnected()) {
            shimmerAnimation.stopShimmer();
            shimmerAnimation.setVisibility(View.GONE);
            coordinatorLayout.setVisibility(View.GONE);
            noInternetLayout.setVisibility(View.VISIBLE);
            return;
        }

        Query query = db.collection("ShopKeeper").whereEqualTo("pNumber", currentUser.getPhoneNumber());
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                HashMap<String, Object> document = (HashMap<String, Object>) queryDocumentSnapshots.getDocuments().get(0).getData();
                assert document != null;
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
                    coordinatorLayout.setVisibility(View.VISIBLE);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                shimmerAnimation.stopShimmer();
                shimmerAnimation.setVisibility(View.GONE);
                coordinatorLayout.setVisibility(View.VISIBLE);
            }
        });

        setFragment(ordersFragment);
        bottomNavigationView.setSelectedItemId(R.id.nav_order);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_item:
                        setFragment(itemsFragment);
                        return true;

                    case R.id.nav_noti:
                        setFragment(notificationFragment);
                        return true;

                    case R.id.nav_order:
                        setFragment(ordersFragment);
                        return true;

                    case R.id.nav_setting:
                        setFragment(settingFragment);
                        return true;

                    case R.id.nav_acc:
                        setFragment(accountFragment);
                        return true;

                    default:
                        return false;
                }

            }
        });

    }


    private void animateNavigationDrawer() {

        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                coordinatorLayout.setScaleX(offsetScale);
                coordinatorLayout.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = coordinatorLayout.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                coordinatorLayout.setTranslationX(xTranslation);
            }
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(Gravity.LEFT);
                return true;

            case R.id.notification:
                Intent intentNoti = new Intent(this, NotificationActivity.class);
                startActivity(intentNoti);
                return true;

            case R.id.feedback:
//                showFeedbackDialog();
                return true;
            case R.id.about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.setting:
                Toast.makeText(this, "Settings for this app shown here!", Toast.LENGTH_SHORT).show();
                Intent intentSetting = new Intent(this, SettingsActivity.class);
                startActivity(intentSetting);
                return true;

            case R.id.rate_us:
//                showRatingDialog();
                return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

        }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


//    private void showFeedbackDialog() {
//
//        ViewGroup viewGroup = findViewById(android.R.id.content);
//
//        View dialogView = LayoutInflater.from(this).inflate(R.layout.feedback_dialog, viewGroup, false);
//
//
//        //Now we need an AlertDialog.Builder object
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        //setting the view of the builder to our custom view that we already inflated
//        builder.setView(dialogView);
//
//        //finally creating the alert dialog and displaying it
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        Button buttonLove = alertDialog.findViewById(R.id.btnLove);
//        Button buttonImprove = alertDialog.findViewById(R.id.btnImprove);
//
//        assert buttonImprove != null;
//        buttonImprove.setOnClickListener(v -> {
//            alertDialog.dismiss();
//            showExpDialog();
//        });
//
//        assert buttonLove != null;
//        buttonLove.setOnClickListener(v -> {
//            alertDialog.dismiss();
//            showRatingDialog();
//        });
//    }
//
//    private void showRatingDialog() {
//        ViewGroup viewGroup = findViewById(android.R.id.content);
//
//        View dialogView = LayoutInflater.from(this).inflate(R.layout.rating_dialog, viewGroup, false);
//
//
//        //Now we need an AlertDialog.Builder object
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        //setting the view of the builder to our custom view that we already inflated
//        builder.setView(dialogView);
//
//        //finally creating the alert dialog and displaying it
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        Button buttonRate = alertDialog.findViewById(R.id.btnRatingPlay);
//        TextView textViewNo = alertDialog.findViewById(R.id.textNoThanks);
//        assert buttonRate != null;
//        buttonRate.setOnClickListener(v -> {
//            Toast.makeText(this, "Opening Play store!", Toast.LENGTH_SHORT).show();
//            alertDialog.dismiss();
//        });
//
//        assert textViewNo != null;
//        textViewNo.setOnClickListener(v -> {
//            alertDialog.dismiss();
//        });
//    }
//
//    private void showExpDialog() {
//        ViewGroup viewGroup = findViewById(android.R.id.content);
//
//        View dialogView = LayoutInflater.from(this).inflate(R.layout.experience_dialog, viewGroup, false);
//
//
//        //Now we need an AlertDialog.Builder object
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        //setting the view of the builder to our custom view that we already inflated
//        builder.setView(dialogView);
//
//        //finally creating the alert dialog and displaying it
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        alertDialog.setCanceledOnTouchOutside(false);
//
//        Button buttonFeedback = alertDialog.findViewById(R.id.btnFeed);
//        ImageView imageViewClose = alertDialog.findViewById(R.id.imageClose);
//        assert buttonFeedback != null;
//        buttonFeedback.setOnClickListener(v -> {
//            Toast.makeText(this, "Feedback sent!!", Toast.LENGTH_SHORT).show();
//            alertDialog.dismiss();
//        });
//
//        assert imageViewClose != null;
//        imageViewClose.setOnClickListener(v -> {
//            alertDialog.dismiss();
//        });
//    }


}
