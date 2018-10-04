package kz.djunglestones.organizerappqel.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kz.djunglestones.organizerappqel.Adapter.PastEventsFragmentAdapter;
import kz.djunglestones.organizerappqel.Models.PastEventsTickets;
import kz.djunglestones.organizerappqel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PastEventsFragment extends Fragment {
    View view;
    private List<PastEventsTickets> pastEventsTicketsList;

    public PastEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_past_events, container, false);

        addTickets();
        RecyclerView recyclerView = view.findViewById(R.id.past_events_frag_recyclerview);
        PastEventsFragmentAdapter adapter = new PastEventsFragmentAdapter(getContext(),pastEventsTicketsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void addTickets() {
        pastEventsTicketsList = new ArrayList<>();
        pastEventsTicketsList.add(new PastEventsTickets("Сольный концерт Кайрата Нуртаса","вс, мая 1 2018"," 19:00",12,60,500));
        pastEventsTicketsList.add(new PastEventsTickets("Сольный концерт Кайрата Нуртаса","вс, мая 1 2018"," 19:00",12,60,500));
        pastEventsTicketsList.add(new PastEventsTickets("Сольный концерт Кайрата Нуртаса","вс, мая 1 2018"," 19:00",12,60,500));
    }

}
