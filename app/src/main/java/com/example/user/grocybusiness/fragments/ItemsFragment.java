package com.example.user.grocybusiness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.adapters.ItemViewPagerAdapter;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private AllItemFragment allItemFragment;
    private OutOfStockItemFragment outOfStockItemFragment;

    public ItemsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemsFragment newInstance(String param1, String param2) {
        ItemsFragment fragment = new ItemsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view=inflater.inflate(R.layout.fragment_items, container, false);
        viewPager= view.findViewById(R.id.view_pager);
        tabLayout= view.findViewById(R.id.tab_layout);

        allItemFragment=new AllItemFragment();
        outOfStockItemFragment=new OutOfStockItemFragment();

        tabLayout.setupWithViewPager(viewPager);

        ItemViewPagerAdapter itemViewPagerAdapter=new ItemViewPagerAdapter(getChildFragmentManager(),0);
        itemViewPagerAdapter.addFragment(allItemFragment,"All Items");
        itemViewPagerAdapter.addFragment(outOfStockItemFragment,"Out Of Stock");
        viewPager.setAdapter(itemViewPagerAdapter);



        BadgeDrawable badgeDrawable1=tabLayout.getTabAt(0).getOrCreateBadge();
        badgeDrawable1.setVisible(true);
        badgeDrawable1.setNumber(21);

        BadgeDrawable badgeDrawable=tabLayout.getTabAt(1).getOrCreateBadge();
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(11);
        return view;
    }

}