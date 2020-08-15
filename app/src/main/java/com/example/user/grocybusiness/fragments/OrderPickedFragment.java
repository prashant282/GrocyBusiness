package com.example.user.grocybusiness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.adapters.OrdersPickedAdapter;
import com.example.user.grocybusiness.models.OrdersAllModel;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrderPickedFragment extends Fragment {

    RecyclerView ordersPickedRecycler;
    public static OrdersPickedAdapter ordersPickedAdapter;

    ArrayList<OrdersAllModel> arrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_picked, container, false);

        ordersPickedRecycler = view.findViewById(R.id.recyclerview_orders_picked);
        setAdapter(view);

        return view;
    }

    private void setAdapter(View view) {
        ordersPickedRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        ordersPickedRecycler.setHasFixedSize(false);

        ordersPickedAdapter = new OrdersPickedAdapter(view.getContext(), OrdersFragment.arrayList);

        ordersPickedRecycler.setAdapter(ordersPickedAdapter);

//        for (int i = 0; i < OrdersFragment.arrayList.size(); i++) {
//            OrdersAllModel ordersAllModel = OrdersFragment.arrayList.get(i);
//            if (ordersAllModel.getOrderStatus().equals("Picked")) {
//                arrayList.add(ordersAllModel);
//            }
//        }


        ordersPickedAdapter.notifyDataSetChanged();

    }

}