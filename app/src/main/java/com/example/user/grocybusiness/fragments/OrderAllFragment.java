package com.example.user.grocybusiness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.adapters.OrdersAllAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrderAllFragment extends Fragment {

    RecyclerView ordersAllRecycler;
    OrdersAllAdapter ordersAllAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order_all, container, false);
        ordersAllRecycler = view.findViewById(R.id.recyclerview_orders_all);


        setAdapter(view);

        return view;


    }

    private void setAdapter(View view) {
        ordersAllRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        ordersAllRecycler.setHasFixedSize(false);

        ordersAllAdapter = new OrdersAllAdapter(view.getContext(), OrdersFragment.arrayList);

        ordersAllRecycler.setAdapter(ordersAllAdapter);


        ordersAllAdapter.notifyDataSetChanged();

    }
}