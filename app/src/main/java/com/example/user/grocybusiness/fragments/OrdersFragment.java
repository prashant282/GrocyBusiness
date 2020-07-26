package com.example.user.grocybusiness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.adapters.OrderViewPagerAdapter;
import com.example.user.grocybusiness.models.OrdersAllModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class OrdersFragment extends Fragment {
    public static ArrayList<OrdersAllModel> arrayList;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> orderTypeFragments = new ArrayList();
    private OrderAllFragment orderAllFragment;
    private OrderUnderPackagingFragment orderUnderPackagingFragment;
    private OrderReadyFragment orderReadyFragment;
    private OrderPickedFragment orderPickedFragment;
    private OrderDeliveredFragment orderDeliveredFragment;
    private ArrayList<String> titles = new ArrayList();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);

        orderAllFragment = new OrderAllFragment();
        orderUnderPackagingFragment = new OrderUnderPackagingFragment();
        orderReadyFragment = new OrderReadyFragment();
        orderPickedFragment = new OrderPickedFragment();
        orderDeliveredFragment = new OrderDeliveredFragment();
        orderTypeFragments.add(orderAllFragment);
        titles.add("All");
        orderTypeFragments.add(orderUnderPackagingFragment);
        titles.add("Under packaging");
        orderTypeFragments.add(orderReadyFragment);
        titles.add("Ready");
        orderTypeFragments.add(orderPickedFragment);
        titles.add("Picked");
        orderTypeFragments.add(orderDeliveredFragment);
        titles.add("Delivered");

        tabLayout.setupWithViewPager(viewPager, true);

        OrderViewPagerAdapter orderViewPagerAdapter = new OrderViewPagerAdapter(getChildFragmentManager(), 0);
        for (int i = 0; i < 5; i++) {
            orderViewPagerAdapter.addFragment(orderTypeFragments.get(i), titles.get(i));
        }
        viewPager.setAdapter(orderViewPagerAdapter);

        getData();

        return view;
    }

    private void getData() {

        arrayList = new ArrayList();
        OrdersAllModel ordersAllModel = new OrdersAllModel("92716388376", "8:46 PM", "Ready", "Prashant's 3rd Order", "18:27", "Order Details", "485.74", "Paid", "Image url", "Nikhil", " is arriving in ", "16 mins", "627848", "Mark order Picked");
        arrayList.add(ordersAllModel);
        ordersAllModel = new OrdersAllModel("72638827368", "4:23 PM", "Under Packaging", "Utkarsh's 1st Order", "26:64", "Order Details", "247.82", "Unpaid", "Image url", "Raghu", " is arriving in ", "6 mins", "277268", "Mark order Ready");
        arrayList.add(ordersAllModel);
        ordersAllModel = new OrdersAllModel("4787863872", "10:28 PM", "Delivered", "Tapan's 8th Order", "08:06", "Order Details", "72.85", "Paid", "Image url", "Shivam", " is arriving in ", "2 mins", "276372", "Already Delivered");
        arrayList.add(ordersAllModel);

    }


}