package com.example.user.grocybusiness.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.models.NotificationModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationAdapter extends FirestoreRecyclerAdapter<NotificationModel, NotificationAdapter.MyViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public NotificationAdapter(@NonNull FirestoreRecyclerOptions<NotificationModel> options) {
        super(options);
    }

    @NonNull
    @Override
    public NotificationModel getItem(int position) {
        return super.getItem(position);
    }

    @Override
    protected void onBindViewHolder(@NonNull NotificationAdapter.MyViewHolder holder, int position, @NonNull NotificationModel model) {

        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
//        DocumentReference documentReference=firebaseFirestore.collection("ShopsMain").document(MainActivity.selectedShop);
        DocumentReference documentReference1=firebaseFirestore.collection("ShopKeeper").document(Objects.requireNonNull(firebaseAuth.getUid()));
        String currentTime = model.getDateTime().substring(11, 16);

        holder.name.setText(model.getUserName());
        holder.address.setText(model.getUserAddress());
        holder.time.setText(currentTime);

        Glide.with(holder.photo.getContext()).load(model.getUserImage()).into(holder.photo);

        holder.orderDetails.setOnClickListener(view -> {

        });

        holder.acceptButton.setOnClickListener(view -> {





            documentReference1.collection("Notifications").whereEqualTo("orderNumberId", model.getOrderNumberId())
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                    documentReference1.collection("Notifications").document(queryDocumentSnapshots.getDocuments().get(0).getId())
                            .update("freshNotification",false).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(holder.acceptButton.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    documentReference1.collection("Notifications").document(queryDocumentSnapshots.getDocuments().get(0).getId())
                            .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            HashMap<String, Object> hm = new HashMap();
                            hm = (HashMap<String, Object>) documentSnapshot.getData();
                            hm.put("orderStatus", "Under Packaging");
                            hm.put("orderId", documentSnapshot.getId());
                            documentReference1.collection("MyOrders").document(queryDocumentSnapshots.getDocuments().get(0).getId())
                                    .set(hm).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(holder.acceptButton.getContext(), "Order Accepted Successfully", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(holder.acceptButton.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(holder.acceptButton.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(holder.acceptButton.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });



        });

        holder.rejectButton.setOnClickListener(view ->
        {

            documentReference1.collection("Notifications").whereEqualTo("orderNumberId", model.getOrderNumberId())
                    .get().addOnSuccessListener(queryDocumentSnapshots -> {
                        documentReference1.collection("Notifications").document(queryDocumentSnapshots.getDocuments().get(0).getId())
                                .update("freshNotification",false).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(holder.rejectButton.getContext(), "Order Rejected Succesfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(holder.acceptButton.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(holder.acceptButton.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        });



    }

    @NonNull
    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_notification, parent, false);
        return new NotificationAdapter.MyViewHolder(view);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, address, time, orderDetails;
        ImageView photo;
        Button acceptButton, rejectButton;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.user_name);
            address = itemView.findViewById(R.id.user_address);
            time = itemView.findViewById(R.id.order_time);
            orderDetails=itemView.findViewById(R.id.order_details);
            photo = itemView.findViewById(R.id.user_img);
            acceptButton=itemView.findViewById(R.id.btn_accept_order);
            rejectButton=itemView.findViewById(R.id.btn_reject_order);
        }
    }
}

