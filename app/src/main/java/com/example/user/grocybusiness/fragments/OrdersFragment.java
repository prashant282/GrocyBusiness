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
    public static ArrayList<OrdersAllModel> arrayList = new ArrayList();
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
        viewPager.setOffscreenPageLimit(5);

        return view;
    }



}