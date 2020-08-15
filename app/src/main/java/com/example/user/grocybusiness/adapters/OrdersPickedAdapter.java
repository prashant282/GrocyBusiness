package com.example.user.grocybusiness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.models.OrdersAllModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrdersPickedAdapter extends RecyclerView.Adapter<OrdersPickedAdapter.OrdersPickedViewHolder> {

    Context context;
    ArrayList<OrdersAllModel> orders_list;

    public OrdersPickedAdapter(Context context, ArrayList<OrdersAllModel> orders_list) {
        this.context = context;
        this.orders_list = orders_list;
    }

    @NonNull
    @Override
    public OrdersPickedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_detail, parent, false);
        return new OrdersPickedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersPickedViewHolder holder, int position) {

        OrdersAllModel ordersAllModel = orders_list.get(position);

        if (ordersAllModel.getOrderStatus().equals("Picked")) {
            holder.orderId.setText(ordersAllModel.getOrderId());
            String[] orderDateTime = ordersAllModel.getOrderTime().split(" ");
            String orderDate = orderDateTime[0];
            String orderTime = orderDateTime[1].substring(0, 5) + " " + orderDateTime[2];
            holder.orderTime.setText(orderTime);
            holder.orderStatus.setText(ordersAllModel.getOrderStatus());
            holder.userDetails.setText(ordersAllModel.getUserDetails().split(" ")[0] + "'s Order");
//        holder.deliveryRemainingTime.setText(ordersAllModel.getDeliveryRemainingTime());
//        holder.orderDetails.setText(ordersAllModel.getOrderId());
            holder.orderPrice.setText(ordersAllModel.getOrderPrice());
//        holder.orderPaymentStatus.setText(ordersAllModel.getOrderPaymentStatus());
            holder.deliveryImage.setImageResource(R.drawable.user_profile);
//        holder.deliveryName.setText(ordersAllModel.getDeliveryName());
//        holder.deliveryMsg.setText(ordersAllModel.getDeliveryMsg());
//        holder.deliveryBoyRemainingTime.setText(ordersAllModel.getDeliveryBoyRemainingTime());
//        holder.deliveryOTP.setText(ordersAllModel.getDeliveryOTP());
            String order_status = ordersAllModel.getOrderStatus();
            holder.changeOrderState.setEnabled(false);
            holder.changeOrderState.setText("Order will delivered Soon");
        } else {
            holder.itemView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        }


    }

    @Override
    public int getItemCount() {
        return orders_list.size();
    }

    public class OrdersPickedViewHolder extends RecyclerView.ViewHolder {
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

        public OrdersPickedViewHolder(@NonNull View itemView) {
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
        }
    }
}
