package kz.djunglestones.organizerappqel.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import kz.djunglestones.organizerappqel.Activities.CreateEventActivity;
import kz.djunglestones.organizerappqel.Adapter.MyEventsFragmentPagerAdapter;
import kz.djunglestones.organizerappqel.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyEventsFragment extends Fragment {
    View v;
    private FloatingActionButton floatingActionButton;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public MyEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        v =inflater.inflate(R.layout.fragment_my_events, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Мои мероприятия");
        initUI();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Create Event Activity has to be opened",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),CreateEventActivity.class);
                startActivity(intent);
            }
        });

        viewPager.setAdapter(new MyEventsFragmentPagerAdapter(getFragmentManager(),tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;
    }

    private void initUI() {
        floatingActionButton = v.findViewById(R.id.fab);
        tabLayout = v.findViewById(R.id.myEventsFragmentTabLayout);
        viewPager = v.findViewById(R.id.viewPager);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.my_events_fragment_menu, menu);
        MenuItem searchMenu = menu.findItem(R.id.search);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
