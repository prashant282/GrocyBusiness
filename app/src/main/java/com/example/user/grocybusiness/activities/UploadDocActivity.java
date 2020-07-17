package com.example.user.grocybusiness.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.grocybusiness.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class UploadDocActivity extends AppCompatActivity {

    Bundle bundle;
    Button btnGst, btnPan, btnAadhaar, btnNext;
    ImageView imgGst, imgPan, imgAadhaar;
    Uri filePathGst, filePathPan, filePathAadhaar;
    long c=0;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_doc);

        bundle=getIntent().getExtras();
        btnGst=findViewById(R.id.btn_upload_gst);
        btnPan=findViewById(R.id.btn_upload_pan);
        btnAadhaar=findViewById(R.id.btn_upload_aadhaar);
        btnNext=findViewById(R.id.btn_next);
        imgAadhaar=findViewById(R.id.img_aadhaar);
        imgGst=findViewById(R.id.img_gst);
        imgPan=findViewById(R.id.img_pan);

        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();

        imgGst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chooseGst();

            }
        });

        imgPan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePan();
            }
        });

        imgAadhaar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAadhaar();
            }
        });


        btnGst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadGst();

            }
        });

        btnPan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadPan();
            }
        });
        btnAadhaar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadAadhaar();
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (c==3){

                    Intent intent=new Intent(UploadDocActivity.this,LoginActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(UploadDocActivity.this, "Upload all the documents", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private void chooseGst() {

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select GST Certificate"),1);
    }


    private void choosePan() {

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Pan Card"),2);
    }

    private void chooseAadhaar() {

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Aadhaar Card"),3);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {

        if (requestCode==1 && resultCode== RESULT_OK && data!=null && data.getData()!=null){
            filePathGst = data.getData();
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePathGst);

            imgGst.setImageBitmap(bitmap);
        }
        if (requestCode==2 && resultCode== RESULT_OK && data!=null && data.getData()!=null){
            filePathPan = data.getData();
            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filePathPan);
            imgPan.setImageBitmap(bitmap);
        }
        if (requestCode==3 && resultCode== RESULT_OK && data!=null && data.getData()!=null){
            filePathAadhaar = data.getData();
            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filePathAadhaar);
            imgAadhaar.setImageBitmap(bitmap);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadGst() {

        if (filePathGst!=null){
            progressDialog= new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.setContentView(R.layout.process_dialog);
            Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(
                    android.R.color.transparent);
            progressDialog.show();

            StorageReference reference=storageReference.child("GST Certificate/"+ UUID.randomUUID().toString());
            reference.putFile(filePathGst)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            c++;
                            progressDialog.dismiss();
                            Toast.makeText(UploadDocActivity.this, "GST Certificate Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress= (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded "+ (int)progress+"%");

                }
            });
        }
    }

    private void uploadPan() {

        if (filePathPan!=null){
            progressDialog= new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.setContentView(R.layout.process_dialog);
            Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(
                    android.R.color.background_light);
            progressDialog.show();

            StorageReference reference=storageReference.child("Pan Card/"+ UUID.randomUUID().toString());
            reference.putFile(filePathPan)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            c++;
                            progressDialog.dismiss();
                            Toast.makeText(UploadDocActivity.this, "Pan Card Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress= (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded "+ (int)progress+"%");

                }
            });
        }
    }

    private void uploadAadhaar() {

        if (filePathAadhaar!=null){
            progressDialog= new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.setContentView(R.layout.process_dialog);
            Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(
                    android.R.color.background_light);
            progressDialog.show();

            StorageReference reference=storageReference.child("Aadhaar Card/"+ UUID.randomUUID().toString());
            reference.putFile(filePathAadhaar)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            c++;
                            progressDialog.dismiss();
                            Toast.makeText(UploadDocActivity.this, "Aadhaar Card Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress= (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded "+ (int)progress+"%");

                }
            });
        }
    }

}