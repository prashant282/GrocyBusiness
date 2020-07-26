package com.example.user.grocybusiness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.adapters.OrdersAllAdapter;
import com.example.user.grocybusiness.models.OrdersAllModel;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class OrderUnderPackagingFragment extends Fragment {

    RecyclerView ordersUnderPackagingRecycler;
    OrdersAllAdapter ordersAllAdapter;

    ArrayList<OrdersAllModel> arrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order_under_packaging, container, false);

        ordersUnderPackagingRecycler = view.findViewById(R.id.recyclerview_orders_under_packaging);
        setAdapter(view);

        return view;
    }


    private void setAdapter(View view) {
        arrayList = new ArrayList();

        ordersUnderPackagingRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        ordersUnderPackagingRecycler.setHasFixedSize(false);

        ordersAllAdapter = new OrdersAllAdapter(view.getContext(), arrayList);

        ordersUnderPackagingRecycler.setAdapter(ordersAllAdapter);

        for (int i = 0; i < OrdersFragment.arrayList.size(); i++) {
            OrdersAllModel ordersAllModel = OrdersFragment.arrayList.get(i);
            if (ordersAllModel.getOrderStatus().equals("Under Packaging")) {
                arrayList.add(ordersAllModel);
            }
        }


        ordersAllAdapter.notifyDataSetChanged();

    }

}