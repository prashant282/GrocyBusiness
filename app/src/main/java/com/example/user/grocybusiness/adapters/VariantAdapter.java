package com.example.user.grocybusiness.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.activities.EditVariantActivity;
import com.example.user.grocybusiness.activities.MainActivity;
import com.example.user.grocybusiness.models.ItemVariantsModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class VariantAdapter extends RecyclerView.Adapter<VariantAdapter.MyHolder>{

    Context context;
    ArrayList<ItemVariantsModel> itemVariantsModel;

    public VariantAdapter(Context context, ArrayList<ItemVariantsModel> itemVariantsModel) {
        this.context = context;
        this.itemVariantsModel = itemVariantsModel;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_variants,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.weight.setText(itemVariantsModel.get(position).getItemQuantity());
        holder.price.setText(itemVariantsModel.get(position).getItemPrice());
        Bundle bundle=new Bundle();
        bundle.putString("itemId",itemVariantsModel.get(position).getItemID());
        bundle.putString("variantId",itemVariantsModel.get(position).getVariant_id());
        bundle.putString("weight",itemVariantsModel.get(position).getItemQuantity());
        bundle.putString("price",itemVariantsModel.get(position).getItemPrice());
        holder.imgDltVariant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater=LayoutInflater.from(holder.imgDltVariant.getContext());
                View view1=layoutInflater.inflate(R.layout.layout_logout_alert_dialog,null);

                Button yesButton=view1.findViewById(R.id.btn_yes);
                Button cancelButton=view1.findViewById(R.id.btn_cancel);

                final androidx.appcompat.app.AlertDialog alertDialog=new androidx.appcompat.app.AlertDialog.Builder(holder.imgDltVariant.getContext())
                        .setView(view1)
                        .create();
                alertDialog.show();
                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
                        DocumentReference documentReference=firebaseFirestore.collection("ShopsMain").document(MainActivity.selectedShop);
                        DocumentReference documentReference1=documentReference.collection("Items").document(itemVariantsModel.get(position).getItemID());
                        documentReference1.collection("Variants").document(itemVariantsModel.get(position).getVariant_id())
                                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(context, "Variant Deleted!!", Toast.LENGTH_SHORT).show();

                                alertDialog.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                            }
                        });


//                        documentReference.collection("Items").document(itemModel.getItemId())
//                                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
//                                alertDialog.dismiss();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
//                                alertDialog.dismiss();
//                            }
//                        });

                    }
                });
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });



            }
        });

        holder.imgEditVariant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                holder.imgEditVariant.getContext().startActivity(new Intent(view.getContext(), EditVariantActivity.class));


                Intent intent=new Intent(view.getContext(),EditVariantActivity.class);
                intent.putExtras(bundle);
                holder.imgEditVariant.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return itemVariantsModel.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {

        TextView weight;
        TextView price;
        ImageView imgEditVariant;
        ImageView imgDltVariant;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            weight=itemView.findViewById(R.id.variant_weight);
            price=itemView.findViewById(R.id.variant_price);
            imgEditVariant=itemView.findViewById(R.id.img_edit_variant);
            imgDltVariant=itemView.findViewById(R.id.img_dlt_variant);
        }
    }
}

//public class VariantAdapter extends FirestoreRecyclerAdapter<ItemVariantsModel, VariantAdapter.MyViewHolder>  {
//
//    /**
//     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
//     * FirestoreRecyclerOptions} for configuration options.
//     *
//     * @param options
//     */
//    public VariantAdapter(@NonNull FirestoreRecyclerOptions<ItemVariantsModel> options) {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull VariantAdapter.MyViewHolder holder, int position, @NonNull ItemVariantsModel model) {
//
//        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
//        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
//        DocumentReference documentReference1=firebaseFirestore.collection("ShopKeeper").document(Objects.requireNonNull(firebaseAuth.getUid()));
//
//        holder.price.setText(model.getItemPrice());
//        holder.weight.setText(model.getItemQuantity());
//    }
//
//    @NonNull
//    @Override
//    public VariantAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_variants, parent, false);
//        return new VariantAdapter.MyViewHolder(view);
//
//    }
//
//    public class MyViewHolder  extends RecyclerView.ViewHolder{
//
//        TextView weight;
//        TextView price;
//        ImageView imgAddVariant;
//        ImageView imgDltVariant;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            weight=itemView.findViewById(R.id.variant_weight);
//            price=itemView.findViewById(R.id.variant_price);
//            imgAddVariant=itemView.findViewById(R.id.img_add_variant);
//            imgDltVariant=itemView.findViewById(R.id.img_dlt_variant);
//        }
//    }
//}
