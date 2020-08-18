package com.example.user.grocybusiness.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.models.OrdersAllModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrdersReadyAdapter extends FirestoreRecyclerAdapter<OrdersAllModel, OrdersReadyAdapter.OrdersReadyViewHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OrdersReadyAdapter(@NonNull FirestoreRecyclerOptions<OrdersAllModel> options) {
        super(options);
    }

    @NonNull
    @Override
    public OrdersReadyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_detail, parent, false);
        return new OrdersReadyViewHolder(view);
    }

//    @Override
//    public void onBindViewHolder(@NonNull OrdersReadyViewHolder holder, int position) {
//
//        OrdersAllModel ordersAllModel = orders_list.get(position);
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
//        holder.changeOrderState.setEnabled(false);
//        holder.changeOrderState.setText("Waiting for Pickup");
//
//
//    }

    @Override
    protected void onBindViewHolder(@NonNull OrdersReadyViewHolder holder, int position, @NonNull OrdersAllModel model) {


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
        holder.changeOrderState.setEnabled(false);
        holder.changeOrderState.setText("Waiting for Pickup");

    }


    @NonNull
    @Override
    public OrdersAllModel getItem(int position) {
        return super.getItem(position);
    }

    public class OrdersReadyViewHolder extends RecyclerView.ViewHolder {
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

        public OrdersReadyViewHolder(@NonNull View itemView) {
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
