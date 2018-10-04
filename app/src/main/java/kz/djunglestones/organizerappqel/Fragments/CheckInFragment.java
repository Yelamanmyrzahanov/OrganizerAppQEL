package kz.djunglestones.organizerappqel.Fragments;


import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kz.djunglestones.organizerappqel.Adapter.CheckInAdapter;
import kz.djunglestones.organizerappqel.Models.CheckIn;
import kz.djunglestones.organizerappqel.R;
import kz.djunglestones.organizerappqel.SwipeController;
import kz.djunglestones.organizerappqel.SwipeControllerActions;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckInFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private List<CheckIn> checkInList;


    public CheckInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_check_in, container, false);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Check-in");

        addCheckInTickets();
        recyclerView = view.findViewById(R.id.check_in_recyclerview);
        final CheckInAdapter adapter = new CheckInAdapter(getContext(),checkInList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        final SwipeController swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onLeftClicked(int position) {
                adapter.setChecInViewLineColor(position,true);
                adapter.notifyItemChanged(position);
                super.onLeftClicked(position);
            }

            @Override
            public void onRightClicked(int position) {
                adapter.setChecInViewLineColor(position,false);
                adapter.notifyItemChanged(position);
                super.onRightClicked(position);
            }
        });
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });

        return view;
    }

    private void addCheckInTickets() {
        checkInList = new ArrayList<>();
        checkInList.add(new CheckIn("Нариман Дуйсеков","Стандарт"));
        checkInList.add(new CheckIn("Мырзаханов Еламан","Стандарт"));
        checkInList.add(new CheckIn("Nurzhanova Akerke","Стандарт"));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.orders_fragment_menu, menu);
        MenuItem searchMenu = menu.findItem(R.id.search);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
