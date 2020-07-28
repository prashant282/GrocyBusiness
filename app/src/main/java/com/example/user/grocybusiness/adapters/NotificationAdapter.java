package com.example.user.grocybusiness.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.models.ItemModel;
import com.example.user.grocybusiness.models.NotificationModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

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

    @Override
    protected void onBindViewHolder(@NonNull NotificationAdapter.MyViewHolder holder, int position, @NonNull NotificationModel model) {

        //        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
//        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
//        DocumentReference documentReference=firebaseFirestore.collection("ShopsMain").document(
//                "qAeielILTRnO7hAIeiS7");
        String currentTime=model.getDateTime().substring(14,20);

        holder.name.setText(model.getUserName());
        holder.address.setText(model.getUserAddress());
        holder.time.setText(currentTime);

        Glide.with(holder.photo.getContext()).load(model.getUserImage()).into(holder.photo);

        holder.orderDetails.setOnClickListener(view -> {

        });

        holder.acceptButton.setOnClickListener(view -> {

        });

        holder.rejectButton.setOnClickListener(view -> {

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


















//





}

