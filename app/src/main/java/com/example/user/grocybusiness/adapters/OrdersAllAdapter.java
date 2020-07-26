package com.example.user.grocybusiness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.models.OrdersAllModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrdersAllAdapter extends RecyclerView.Adapter<OrdersAllAdapter.OrdersAllViewHolder> {

    Context context;
    ArrayList<OrdersAllModel> orders_list;

    public OrdersAllAdapter(Context context, ArrayList<OrdersAllModel> orders_list) {
        this.context = context;
        this.orders_list = orders_list;
    }

    @NonNull
    @Override
    public OrdersAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_detail, parent, false);
        return new OrdersAllViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAllViewHolder holder, int position) {

        OrdersAllModel ordersAllModel = orders_list.get(position);

        holder.orderId.setText(ordersAllModel.getOrderId());
        holder.orderTime.setText(ordersAllModel.getOrderTime());
        holder.orderStatus.setText(ordersAllModel.getOrderStatus());
        holder.userDetails.setText(ordersAllModel.getUserDetails());
        holder.deliveryRemainingTime.setText(ordersAllModel.getDeliveryRemainingTime());
//        holder.orderDetails.setText(ordersAllModel.getOrderId());
        holder.orderPrice.setText(ordersAllModel.getOrderPrice());
        holder.orderPaymentStatus.setText(ordersAllModel.getOrderPaymentStatus());
//        holder.deliveryImage.setText(ordersAllModel.getOrderId());
        holder.deliveryImage.setImageResource(R.drawable.user_profile);
        holder.deliveryName.setText(ordersAllModel.getDeliveryName());
        holder.deliveryMsg.setText(ordersAllModel.getDeliveryMsg());
        holder.deliveryBoyRemainingTime.setText(ordersAllModel.getDeliveryBoyRemainingTime());
        holder.deliveryOTP.setText(ordersAllModel.getDeliveryOTP());
        String order_status = ordersAllModel.getOrderStatus();
        if (order_status.equals("Delivered")) {
            holder.changeOrderState.setEnabled(false);
            holder.changeOrderState.setText(ordersAllModel.getChangeOrderState());
        } else {
            holder.changeOrderState.setText(ordersAllModel.getChangeOrderState());
        }


    }

    @Override
    public int getItemCount() {
        return orders_list.size();
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
        }
    }
}
