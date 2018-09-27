package kz.djunglestones.organizerappqel.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.budiyev.android.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;

import kz.djunglestones.organizerappqel.Adapter.SoldOutCircleTicketsRecyclerAdapter;
import kz.djunglestones.organizerappqel.Class.SoldOutCircleTickets;
import kz.djunglestones.organizerappqel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment {

    View v;
    private CircularProgressBar sold_out_progressBar;
    private TextView sales_price_tv,sold_out_tickets_amount;
    private RecyclerView recyclerView,check_in_tickets_recyclerView;
    private List<SoldOutCircleTickets> soldOutCircleTicketsList;
    private List<SoldOutCircleTickets> checkInList;

    public StatisticsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_statistics, container, false);
        initUI();
        addStatisticsData();

        SoldOutCircleTicketsRecyclerAdapter adapter = new SoldOutCircleTicketsRecyclerAdapter(getContext(),soldOutCircleTicketsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        check_in_tickets_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        check_in_tickets_recyclerView.setAdapter(adapter);



        return v;
    }

    private void addStatisticsData() {
        soldOutCircleTicketsList = new ArrayList<>();
        soldOutCircleTicketsList.add(new SoldOutCircleTickets("Стандарт",5,25));
        soldOutCircleTicketsList.add(new SoldOutCircleTickets("Лакшири",4,10));
        soldOutCircleTicketsList.add(new SoldOutCircleTickets("Голд",5,30));

        checkInList = new ArrayList<>();
        checkInList.add(new SoldOutCircleTickets("Стандарт",5,25));
        checkInList.add(new SoldOutCircleTickets("Лакшири",4,10));
        checkInList.add(new SoldOutCircleTickets("Голд",5,30));
    }

    private void initUI() {
        sold_out_progressBar= v.findViewById(R.id.sold_out_progressBar);
        sales_price_tv = v.findViewById(R.id.sales_price_tv);
        sold_out_tickets_amount = v.findViewById(R.id.sold_out_tickets_amount);
        recyclerView = v.findViewById(R.id.sold_out_tickets_recyclerView);
        check_in_tickets_recyclerView = v.findViewById(R.id.check_in_tickets_recyclerView);

    }

}
