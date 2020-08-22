package com.example.user.grocybusiness.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.fragments.AddItemFragment;
import com.example.user.grocybusiness.fragments.ItemCustomImageFragment;

import java.io.IOException;

public class AddItemPhotoActivity extends AppCompatActivity {

    ImageButton imgItem;
    ImageView itemImageView;
    static Uri filePath;
    Button btnAddPhoto;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_photo);

        imgItem=findViewById(R.id.img_item);
        itemImageView=findViewById(R.id.img_item_preview);
        btnAddPhoto=findViewById(R.id.btn_add_image);

        bundle=getIntent().getExtras();


        imgItem.setOnClickListener(view1 -> {
            chooseItemImage();
        });

        btnAddPhoto.setOnClickListener(view12 -> {
//            startActivity(new Intent(AddItemPhotoActivity.this,EditItemActivity.class));
            Intent intent=new Intent(this,EditItemActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });


    }

    private void chooseItemImage() {

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent,"Select item image"),1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {

            if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
                filePath = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                itemImageView.setImageBitmap(bitmap);
                btnAddPhoto.setEnabled(true);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
