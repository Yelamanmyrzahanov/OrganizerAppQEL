package kz.djunglestones.organizerappqel.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
import kz.djunglestones.organizerappqel.Models.SoldOutCircleTickets;
import kz.djunglestones.organizerappqel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment {

    View v;
    private CircularProgressBar sold_out_progressBar,check_in_progressBar;
    private TextView sales_price_tv,sold_out_tickets_amount,sold_out_innerCircle_percentage_tv,check_in_tickets_amount,check_in_innerCircle_percentage_tv,total_check_in_price_tv;
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
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Статистика");
        initUI();
        addStatisticsData();
        countTotalSoldOutTickets();
        SoldOutCircleTicketsRecyclerAdapter adapter = new SoldOutCircleTicketsRecyclerAdapter(getContext(),soldOutCircleTicketsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        SoldOutCircleTicketsRecyclerAdapter checkInTicketsAdapter = new SoldOutCircleTicketsRecyclerAdapter(getContext(),checkInList);
        check_in_tickets_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        check_in_tickets_recyclerView.setAdapter(checkInTicketsAdapter);


        return v;
    }

    private void countTotalSoldOutTickets() {
        int totalSoldOutTickets = 0;
        int soldOutTickets = 0;
        for (SoldOutCircleTickets soldOutCircleTickets:soldOutCircleTicketsList){
            totalSoldOutTickets+=soldOutCircleTickets.getTicketTotal();
            soldOutTickets += soldOutCircleTickets.getTicketsSold();
        }

        sold_out_tickets_amount.setText(String.valueOf(soldOutTickets)+" / "+String.valueOf(totalSoldOutTickets));
        sold_out_progressBar.setProgress(percentageCount(soldOutTickets,totalSoldOutTickets));
        sold_out_innerCircle_percentage_tv.setText(percentageCount(soldOutTickets,totalSoldOutTickets)+"%");

        int totalCheckedInTickets = 0;
        int checkedInTickets = 0;
        for (SoldOutCircleTickets checkedInCircleTickets:checkInList){
            totalCheckedInTickets+=checkedInCircleTickets.getTicketTotal();
            checkedInTickets += checkedInCircleTickets.getTicketsSold();
        }
        check_in_tickets_amount.setText(String.valueOf(checkedInTickets)+" / "+String.valueOf(totalCheckedInTickets));
        check_in_progressBar.setProgress(percentageCount(checkedInTickets,totalCheckedInTickets));
        check_in_innerCircle_percentage_tv.setText(percentageCount(checkedInTickets,totalCheckedInTickets)+"%");
        total_check_in_price_tv.setText(String.valueOf(checkedInTickets));
    }

    private int percentageCount(int soldOut, int total) {
        return Math.round((float) soldOut *100/ (float) total);
    }

    private void addStatisticsData() {
        soldOutCircleTicketsList = new ArrayList<>();
        soldOutCircleTicketsList.add(new SoldOutCircleTickets("Стандарт",15,31));
        soldOutCircleTicketsList.add(new SoldOutCircleTickets("Лакшири",10,10));
        soldOutCircleTicketsList.add(new SoldOutCircleTickets("Голд",18,30));
        checkInList = new ArrayList<>();
        checkInList.add(new SoldOutCircleTickets("Стандарт",21,25));
        checkInList.add(new SoldOutCircleTickets("Лакшири",7,10));
        checkInList.add(new SoldOutCircleTickets("Голд",19,30));

    }

    private void initUI() {
        sold_out_progressBar= v.findViewById(R.id.sold_out_progressBar);
        sales_price_tv = v.findViewById(R.id.sales_price_tv);
        sold_out_tickets_amount = v.findViewById(R.id.sold_out_tickets_amount);
        recyclerView = v.findViewById(R.id.sold_out_tickets_recyclerView);
        check_in_tickets_recyclerView = v.findViewById(R.id.check_in_tickets_recyclerView);
        sold_out_innerCircle_percentage_tv = v.findViewById(R.id.sold_out_innerCircle_percentage_tv);
        check_in_tickets_amount = v.findViewById(R.id.check_in_tickets_amount);
        check_in_innerCircle_percentage_tv = v.findViewById(R.id.check_in_innerCircle_percentage_tv);
        check_in_progressBar = v.findViewById(R.id.check_in_progressBar);
        total_check_in_price_tv= v.findViewById(R.id.total_check_in_price_tv);

    }

}
