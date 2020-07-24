package com.example.user.grocybusiness.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.user.grocybusiness.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class UploadDocActivity extends AppCompatActivity {

    Bundle bundle;
    Button btnReg;
    ImageView imgGst, imgPan, imgAadhaarFront, getImgAadhaarBack;
    Uri filePathGst, filePathPan, filePathAadhaarFront, filePathAadhaarBack;
    long c=0, noOfShops;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    CheckBox checkBox;
    String sName, sAddress, sState, sGst, sCategory, sCity, sPinCode;
    DocumentReference documentReference;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_doc);

        bundle=getIntent().getExtras();
        btnReg=findViewById(R.id.btn_reg);
        imgAadhaarFront=findViewById(R.id.img_aadhaar_front);
        getImgAadhaarBack=findViewById(R.id.img_aadhaar_back);
        imgGst=findViewById(R.id.img_gst);
        imgPan=findViewById(R.id.img_pan);
        checkBox=findViewById(R.id.check_box);

        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

        sName=bundle.getString("sName");
        sAddress=bundle.getString("sAddress");
        sState=bundle.getString("sState");
        sGst=bundle.getString("sGst");
        sCategory=bundle.getString("sCategory");
        sCity=bundle.getString("sCity");
        sPinCode=bundle.getString("sPinCode");

        final Map<String,Object> shop=new HashMap<>();
        shop.put("shopName", sName);
        shop.put("shopAddress",sAddress );
        shop.put("shopCategory", sCategory);
        shop.put("shopCity",sCity);
        shop.put("shopState", sState);
        shop.put("shopPinCode", sPinCode);
        shop.put("shopGst", sGst);


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

        imgAadhaarFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAadhaarFront();
            }
        });

        getImgAadhaarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAadhaarBack();
            }
        });


//        btnGst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                uploadGst();
//
//            }
//        });
//
//        btnPan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                uploadPan();
//            }
//        });
//        btnAadhaar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                uploadAadhaar();
//            }
//        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked()) {

                    btnReg.setEnabled(true);

                } else {

                    btnReg.setEnabled(false);

                }

            }
        });



        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (filePathGst!=null){

                        progressDialog= new ProgressDialog(UploadDocActivity.this);
                        progressDialog.setTitle("Uploading...");
                        progressDialog.setContentView(R.layout.process_dialog);
                        Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(
                                android.R.color.white);
                        progressDialog.show();

                        StorageReference reference=storageReference.child("GST Certificate/"+ UUID.randomUUID().toString());
                        reference.putFile(filePathGst)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        c++;

                                        Task<Uri> task = Objects.requireNonNull(Objects.requireNonNull(taskSnapshot.getMetadata()).getReference()).getDownloadUrl();
                                        task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                shop.put("imageGst",uri.toString());
                                            }
                                        });

                                        if(filePathPan!=null){

                                            StorageReference reference=storageReference.child("Pan Card/"+ UUID.randomUUID().toString());
                                            reference.putFile(filePathPan)
                                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                        @Override
                                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                            c++;

                                                            Task<Uri> task = Objects.requireNonNull(Objects.requireNonNull(taskSnapshot.getMetadata()).getReference()).getDownloadUrl();
                                                            task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                @Override
                                                                public void onSuccess(Uri uri) {
                                                                    shop.put("imagePan",uri.toString());
                                                                }
                                                            });

                                                            if(filePathAadhaarFront!=null){

                                                                StorageReference reference=storageReference.child("Aadhaar Card Front/"+ UUID.randomUUID().toString());
                                                                reference.putFile(filePathAadhaarFront)
                                                                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                                            @Override
                                                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                                                c++;

                                                                                Task<Uri> task = Objects.requireNonNull(Objects.requireNonNull(taskSnapshot.getMetadata()).getReference()).getDownloadUrl();
                                                                                task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                    @Override
                                                                                    public void onSuccess(Uri uri) {
                                                                                        shop.put("imageAadhaarFront",uri.toString());
                                                                                    }
                                                                                });

                                                                                if (filePathAadhaarBack!=null){

                                                                                    StorageReference reference=storageReference.child("Aadhaar Card Back/"+ UUID.randomUUID().toString());
                                                                                    reference.putFile(filePathAadhaarBack)
                                                                                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                                                                @Override
                                                                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                                                                    c++;
                                                                                                    Task<Uri> task = Objects.requireNonNull(Objects.requireNonNull(taskSnapshot.getMetadata()).getReference()).getDownloadUrl();
                                                                                                    task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                        @Override
                                                                                                        public void onSuccess(Uri uri) {
                                                                                                            shop.put("imageAadhaarBack",uri.toString());
                                                                                                        }
                                                                                                    });

                                                                                                    if (c==4){
                                                                                                        firebaseFirestore.collection("ShopKeeper").document(firebaseAuth.getUid())
                                                                                                                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                                            @Override
                                                                                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                                                if (task.isSuccessful()){

                                                                                                                    DocumentSnapshot documentSnapshot=task.getResult();
                                                                                                                    noOfShops=documentSnapshot.getLong("no_of_shops");
                                                                                                                    noOfShops++;
                                                                                                                    final Map<String, Object> count=new HashMap<>();
                                                                                                                    count.put("no_of_shops", noOfShops);

                                                                                                                    firebaseFirestore.collection("ShopKeeper").document(firebaseAuth.getUid())
                                                                                                                            .update(count).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                        @Override
                                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                                            if (task.isSuccessful()){



                                                                                                                                documentReference =firebaseFirestore.collection("ShopKeeper").document(Objects.requireNonNull(firebaseAuth.getUid()));
                                                                                                        documentReference.collection("MyShop")
                                                                                                                .add(shop).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                                                                            @Override
                                                                                                            public void onSuccess(DocumentReference documentReference) {
                                                                                                                Toast.makeText(UploadDocActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                                                                                                Intent intent=new Intent(UploadDocActivity.this,MainActivity.class);
                                                                                                                intent.putExtras(bundle);
                                                                                                                startActivity(intent);
                                                                                                                finish();

                                                                                                            }
                                                                                                        }).addOnFailureListener(new OnFailureListener() {
                                                                                                            @Override
                                                                                                            public void onFailure(@NonNull Exception e) {

                                                                                                            }
                                                                                                        });

                                                                                                                            }
                                                                                                                        }
                                                                                                                    });


                                                                                                                }
                                                                                                            }
                                                                                                        });



//                                                                                                        documentReference =firebaseFirestore.collection("ShopKeeper").document(Objects.requireNonNull(firebaseAuth.getUid()));
//                                                                                                        documentReference.collection("MyShop")
//                                                                                                                .add(shop).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                                                                                            @Override
//                                                                                                            public void onSuccess(DocumentReference documentReference) {
//                                                                                                                Toast.makeText(UploadDocActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
//                                                                                                                Intent intent=new Intent(UploadDocActivity.this,MainActivity.class);
//                                                                                                                intent.putExtras(bundle);
//                                                                                                                startActivity(intent);
//                                                                                                                finish();
//
//                                                                                                            }
//                                                                                                        }).addOnFailureListener(new OnFailureListener() {
//                                                                                                            @Override
//                                                                                                            public void onFailure(@NonNull Exception e) {
//
//                                                                                                            }
//                                                                                                        });
//


                                                                                                    }
                                                                                                    else{
                                                                                                        Toast.makeText(UploadDocActivity.this, "Select all the documents", Toast.LENGTH_SHORT).show();
                                                                                                        progressDialog.dismiss();
                                                                                                    }

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
                                                                        });

                                                            }

                                                        }
                                                    });

                                        }

//                            progressDialog.dismiss();
//                            Toast.makeText(UploadDocActivity.this, "GST Certificate Uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                });



                    }


//                    uploadGst();
//                    uploadPan();
//                    uploadAadhaarFront();
//                    uploadAadhaarBack();

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

    private void chooseAadhaarFront() {

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Aadhaar Card Front Photo"),3);
    }

    private void chooseAadhaarBack() {

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Aadhaar Card Back Photo"),4);
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
            filePathAadhaarFront = data.getData();
            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filePathAadhaarFront);
            imgAadhaarFront.setImageBitmap(bitmap);
        }
            if (requestCode==4 && resultCode== RESULT_OK && data!=null && data.getData()!=null){
                filePathAadhaarBack = data.getData();
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filePathAadhaarBack);
                getImgAadhaarBack.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void uploadGst() {
//
//        if (filePathGst!=null){
////            progressDialog= new ProgressDialog(this);
////            progressDialog.setTitle("Uploading...");
////            progressDialog.setContentView(R.layout.process_dialog);
////            Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(
////                    android.R.color.transparent);
////            progressDialog.show();
//
//            StorageReference reference=storageReference.child("GST Certificate/"+ UUID.randomUUID().toString());
//            reference.putFile(filePathGst)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            c++;
////                            progressDialog.dismiss();
////                            Toast.makeText(UploadDocActivity.this, "GST Certificate Uploaded", Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
////                    double progress= (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
////                    progressDialog.setMessage("Uploaded "+ (int)progress+"%");
//
//                }
//            });
//        }
//    }

//    private void uploadPan() {
//
//        if (filePathPan!=null){
////            progressDialog= new ProgressDialog(this);
////            progressDialog.setTitle("Uploading...");
////            progressDialog.setContentView(R.layout.process_dialog);
////            Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(
////                    android.R.color.background_light);
////            progressDialog.show();
//
//            StorageReference reference=storageReference.child("Pan Card/"+ UUID.randomUUID().toString());
//            reference.putFile(filePathPan)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            c++;
//                            progressDialog.dismiss();
////                            Toast.makeText(UploadDocActivity.this, "Pan Card Uploaded", Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
////                    double progress= (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
////                    progressDialog.setMessage("Uploaded "+ (int)progress+"%");
//
//                }
//            });
//        }
//    }

//    private void uploadAadhaarFront() {
//
//        if (filePathAadhaarFront!=null){
////            progressDialog= new ProgressDialog(this);
////            progressDialog.setTitle("Uploading...");
////            progressDialog.setContentView(R.layout.process_dialog);
////            Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(
////                    android.R.color.background_light);
////            progressDialog.show();
//
//            StorageReference reference=storageReference.child("Aadhaar Card Front/"+ UUID.randomUUID().toString());
//            reference.putFile(filePathAadhaarFront)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            c++;
//                            progressDialog.dismiss();
////                            Toast.makeText(UploadDocActivity.this, "Aadhaar Card Uploaded", Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
////                    double progress= (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
////                    progressDialog.setMessage("Uploaded "+ (int)progress+"%");
//
//                }
//            });
//        }
//    }

//    private void uploadAadhaarBack() {
//
//        if (filePathAadhaarBack!=null){
////            progressDialog= new ProgressDialog(this);
////            progressDialog.setTitle("Uploading...");
////            progressDialog.setContentView(R.layout.process_dialog);
////            Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(
////                    android.R.color.background_light);
////            progressDialog.show();
//
//            StorageReference reference=storageReference.child("Aadhaar Card Back/"+ UUID.randomUUID().toString());
//            reference.putFile(filePathAadhaarBack)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            c++;
//                            progressDialog.dismiss();
////                            Toast.makeText(UploadDocActivity.this, "Aadhaar Card Uploaded", Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
////                    double progress= (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
////                    progressDialog.setMessage("Uploaded "+ (int)progress+"%");
//
//                }
//            });
//        }
//    }

}