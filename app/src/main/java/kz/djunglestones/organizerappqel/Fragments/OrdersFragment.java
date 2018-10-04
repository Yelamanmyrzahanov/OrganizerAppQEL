package kz.djunglestones.organizerappqel.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kz.djunglestones.organizerappqel.Adapter.OrdersFragmentRecyclerAdapter;
import kz.djunglestones.organizerappqel.Models.Orders;
import kz.djunglestones.organizerappqel.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends Fragment {
    View v;
    private List<Orders> ordersList;

    public OrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_orders, container, false);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Заказы");
        initUI();

        addTickets();

        RecyclerView recyclerView  = v.findViewById(R.id.orders_fragment_recyclerview);
        OrdersFragmentRecyclerAdapter adapter = new OrdersFragmentRecyclerAdapter(getContext(),ordersList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return v;
    }

    private void addTickets() {
        ordersList = new ArrayList<>();
        ordersList.add(new Orders("Нариман Дуйсеков","nariman.duisekov@gmail.com","48612345","31 сен, 2018",2500));
        ordersList.add(new Orders("Еламан Мырзаханов","yelamanmyrzahanov@gmail.com","48712343","31 сен, 2018",5000));
        ordersList.add(new Orders("Жанибек Рахымбекулы","rzhanik@gmail.com","486123879","31 сен, 2018",7500));
    }

    private void initUI() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.orders_fragment_menu, menu);
        MenuItem searchMenu = menu.findItem(R.id.search);
        MenuItem qr_code_reader = menu.findItem(R.id.qr_code_menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
