package com.example.user.grocybusiness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.adapters.OrdersReadyAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class OrderReadyFragment extends Fragment {

    RecyclerView ordersReadyRecycler;
    public static OrdersReadyAdapter ordersReadyAdapter;

//    ArrayList<OrdersAllModel> arrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_ready, container, false);

        ordersReadyRecycler = view.findViewById(R.id.recyclerview_orders_ready);
        setAdapter(view);

        return view;
    }


    private void setAdapter(View view) {
        ordersReadyRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        ordersReadyRecycler.setHasFixedSize(false);

        ordersReadyAdapter = new OrdersReadyAdapter(view.getContext(), OrdersFragment.arrayList);

        ordersReadyRecycler.setAdapter(ordersReadyAdapter);

//        for (int i = 0; i < OrdersFragment.arrayList.size(); i++) {
//            OrdersAllModel ordersAllModel = OrdersFragment.arrayList.get(i);
//            if (ordersAllModel.getOrderStatus().equals("Ready")) {
//                arrayList.add(ordersAllModel);
//            }
//        }


        ordersReadyAdapter.notifyDataSetChanged();

    }

}