package com.example.user.grocybusiness.adapters;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.models.OrderItemModel;
import com.example.user.grocybusiness.models.OrdersAllModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrdersAllAdapter extends FirestoreRecyclerAdapter<OrdersAllModel, OrdersAllAdapter.OrdersAllViewHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OrdersAllAdapter(@NonNull FirestoreRecyclerOptions<OrdersAllModel> options) {
        super(options);
    }


    @NonNull
    @Override
    public OrdersAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_detail, parent, false);
        return new OrdersAllViewHolder(view);
    }
//
//    @Override
//    public void onBindViewHolder(@NonNull OrdersAllViewHolder holder, int position) {
//
//        OrdersAllModel ordersAllModel = orders_list.get(position);
//        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//
//        holder.orderId.setText(ordersAllModel.getOrderId());
//        String[] orderDateTime = ordersAllModel.getOrderTime().split(" ");
//        String orderDate = orderDateTime[0];
//        String orderTime = orderDateTime[1].substring(0, 5) + " " + orderDateTime[2];
//        holder.orderTime.setText(orderTime);
//        holder.orderStatus.setText(ordersAllModel.getOrderStatus());
//        holder.userDetails.setText(ordersAllModel.getUserDetails().split(" ")[0] + "'s Order");
////        holder.deliveryRemainingTime.setText(ordersAllModel.getDeliveryRemainingTime());
////        holder.orderDetails.setText(ordersAllModel.getOrderId());
//        holder.orderPrice.setText(ordersAllModel.getOrderPrice());
////        holder.orderPaymentStatus.setText(ordersAllModel.getOrderPaymentStatus());
//        holder.deliveryImage.setImageResource(R.drawable.user_profile);
////        holder.deliveryName.setText(ordersAllModel.getDeliveryName());
////        holder.deliveryMsg.setText(ordersAllModel.getDeliveryMsg());
////        holder.deliveryBoyRemainingTime.setText(ordersAllModel.getDeliveryBoyRemainingTime());
////        holder.deliveryOTP.setText(ordersAllModel.getDeliveryOTP());
//        String order_status = ordersAllModel.getOrderStatus();
//        if (order_status.equals("Delivered")) {
//            holder.changeOrderState.setEnabled(false);
//            holder.changeOrderState.setText("Already Delivered");
//        } else if (order_status.equals("Picked")) {
//            holder.changeOrderState.setEnabled(false);
//            holder.changeOrderState.setText("Order will delivered Soon");
//        } else if (order_status.equals("Ready")) {
//            holder.changeOrderState.setEnabled(false);
//            holder.changeOrderState.setText("Waiting for Pickup");
//        } else if (order_status.equals("Under Packaging")) {
//            holder.changeOrderState.setText("Mark Order Ready");
//        }
//
//        holder.changeOrderState.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (order_status.equals("Under Packaging")) {
//                    holder.changeOrderState.setEnabled(false);
//                    holder.changeOrderState.setText("Waiting for Pickup");
//                    firebaseFirestore.collection("ShopKeeper").document(firebaseAuth.getUid()).collection("MyOrders")
//                            .document(ordersAllModel.getOrderDocumentId()).update("orderStatus", "Ready").addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            ordersAllModel.setOrderStatus("Ready");
//                            notifyItemChanged(position);
////                            OrderDeliveredFragment.ordersDeliveredAdapter.notifyDataSetChanged();
////                            OrderPickedFragment.ordersPickedAdapter.notifyDataSetChanged();
////                            OrderReadyFragment.ordersReadyAdapter.notifyDataSetChanged();
////                            OrderUnderPackagingFragment.ordersUnderPackagingAdapter.notifyDataSetChanged();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//                            holder.changeOrderState.setEnabled(true);
//                            holder.changeOrderState.setText("Mark Order Ready");
//                        }
//                    });
//                }
//            }
//        });
//
//
//    }

    @Override
    protected void onBindViewHolder(@NonNull OrdersAllViewHolder holder, int position, @NonNull OrdersAllModel model) {

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        holder.orderId.setText(model.getOrderNumberId());
        String[] orderDateTime = model.getDateTime().split(" ");
        String orderDate = orderDateTime[0];
        String orderTime = orderDateTime[1].substring(0, 5) + " " + orderDateTime[2];
        holder.orderTime.setText(orderTime);
        holder.orderStatus.setText(model.getOrderStatus());
        holder.userDetails.setText(model.getUserName().split(" ")[0] + "'s Order");
//        holder.deliveryRemainingTime.setText(ordersAllModel.getDeliveryRemainingTime());
//        holder.orderDetails.setText(ordersAllModel.getOrderId());
        holder.orderPrice.setText("" + model.getOrderAmount());
//        holder.orderPaymentStatus.setText(ordersAllModel.getOrderPaymentStatus());
        holder.deliveryImage.setImageResource(R.drawable.user_profile);
//        holder.deliveryName.setText(ordersAllModel.getDeliveryName());
//        holder.deliveryMsg.setText(ordersAllModel.getDeliveryMsg());
//        holder.deliveryBoyRemainingTime.setText(ordersAllModel.getDeliveryBoyRemainingTime());
//        holder.deliveryOTP.setText(ordersAllModel.getDeliveryOTP());
        String order_status = model.getOrderStatus();
        if (order_status.equals("Delivered")) {
            holder.changeOrderState.setEnabled(false);
            holder.changeOrderState.setText("Already Delivered");
        } else if (order_status.equals("Picked")) {
            holder.changeOrderState.setEnabled(false);
            holder.changeOrderState.setText("Order will delivered Soon");
        } else if (order_status.equals("Ready")) {
            holder.changeOrderState.setEnabled(false);
            holder.changeOrderState.setText("Waiting for Pickup");
        } else if (order_status.equals("Under Packaging")) {
            holder.changeOrderState.setEnabled(true);
            holder.changeOrderState.setText("Mark Order Ready");
        }

        holder.changeOrderState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (order_status.equals("Under Packaging")) {
                    holder.changeOrderState.setEnabled(false);
                    holder.changeOrderState.setText("Waiting for Pickup");
                    firebaseFirestore.collection("ShopKeeper").document(firebaseAuth.getUid()).collection("MyOrders")
                            .document(model.getOrderId()).update("orderStatus", "Ready").addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            model.setOrderStatus("Ready");
                            notifyItemChanged(position);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(holder.changeOrderState.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            holder.changeOrderState.setEnabled(true);
                            holder.changeOrderState.setText("Mark Order Ready");
                        }
                    });
                }
            }
        });

        holder.orderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View orderDetails = LayoutInflater.from(holder.orderDetails.getContext()).inflate(R.layout.layout_order_details_dialog, holder.viewGroup, false);

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.orderDetails.getContext());

                builder.setView(orderDetails);
                AlertDialog alertDialog = builder.create();
                TextView order_id = orderDetails.findViewById(R.id.order_id);
                order_id.setText("Order Id: " + model.getOrderNumberId());
                ImageView close_dialog = orderDetails.findViewById(R.id.close_dialog);
                close_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                RecyclerView orderItemsRecycler = orderDetails.findViewById(R.id.order_items_recycler);

                ArrayList<HashMap<String, Object>> order_items = model.getItems();
                ArrayList<OrderItemModel> arrayList = new ArrayList();
                for (int i = 0; i < order_items.size(); i++) {
                    OrderItemModel orderItemModel = new OrderItemModel();
                    HashMap<String, Object> hm = order_items.get(i);
                    orderItemModel.setItemCount((Long) hm.get("itemCount"));
                    orderItemModel.setItemID((String) hm.get("itemID"));
                    orderItemModel.setItemsName((String) hm.get("itemsName"));
                    orderItemModel.setItemsImage((String) hm.get("itemsImage"));
                    orderItemModel.setItemsPrice((String) hm.get("itemsPrice"));
                    orderItemModel.setItemsQuantity((String) hm.get("itemsQuantity"));
                    orderItemModel.setShopId((String) hm.get("shopId"));
                    orderItemModel.setVariantId((String) hm.get("variantID"));
                    arrayList.add(orderItemModel);
                }
                OrderItemsAdapter orderItemsAdapter = new OrderItemsAdapter(holder.orderDetails.getContext(), arrayList);
                orderItemsRecycler.setLayoutManager(new LinearLayoutManager(holder.orderDetails.getContext(), LinearLayoutManager.VERTICAL, false));
                orderItemsRecycler.setHasFixedSize(false);
                orderItemsRecycler.setAdapter(orderItemsAdapter);
                orderItemsAdapter.notifyDataSetChanged();

                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();

            }
        });


    }

    @NonNull
    @Override
    public OrdersAllModel getItem(int position) {
        return super.getItem(position);
    }

    public class OrdersAllViewHolder extends RecyclerView.ViewHolder {
        TextView orderId;
        TextView orderTime;
        TextView orderStatus;
        TextView userDetails;
        TextView deliveryRemainingTime;
        TextView orderDetails;
        TextView orderPrice;
        TextView orderPaymentStatus;
        ImageView deliveryImage;
        TextView deliveryName;
        TextView deliveryMsg;
        TextView deliveryBoyRemainingTime;
        TextView deliveryOTP;
        Button changeOrderState;
        ViewGroup viewGroup;

        public OrdersAllViewHolder(@NonNull View itemView) {
            super(itemView);

            orderId = itemView.findViewById(R.id.order_id);
            orderTime = itemView.findViewById(R.id.order_time);
            orderStatus = itemView.findViewById(R.id.order_status);
            userDetails = itemView.findViewById(R.id.user_details);
            deliveryRemainingTime = itemView.findViewById(R.id.delivery_remaining_time);
            orderDetails = itemView.findViewById(R.id.order_details);
            orderPrice = itemView.findViewById(R.id.order_price);
            orderPaymentStatus = itemView.findViewById(R.id.order_payment_status);
            deliveryImage = itemView.findViewById(R.id.delivery_image);
            deliveryName = itemView.findViewById(R.id.delivery_name);
            deliveryMsg = itemView.findViewById(R.id.tv_deliverymsg);
            deliveryBoyRemainingTime = itemView.findViewById(R.id.tv_delivery_boy_time);
            deliveryOTP = itemView.findViewById(R.id.delivery_otp);
            changeOrderState = itemView.findViewById(R.id.change_order_state);

            viewGroup = itemView.findViewById(android.R.id.content);

        }
    }
}
