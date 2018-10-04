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

import kz.djunglestones.organizerappqel.Adapter.DraftEventsFragmentAdapter;
import kz.djunglestones.organizerappqel.Models.DraftEventsTickets;
import kz.djunglestones.organizerappqel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DraftEventsFragment extends Fragment {
    View view;
    List<DraftEventsTickets> draftEventsTicketsList;

    public DraftEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_draft_events, container, false);

        addTickets();
        RecyclerView recyclerView = view.findViewById(R.id.draft_events_frag_recyclerview);
        DraftEventsFragmentAdapter draftEventsFragmentAdapter = new DraftEventsFragmentAdapter(getContext(),draftEventsTicketsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(draftEventsFragmentAdapter);
        return view;
    }

    private void addTickets() {
        draftEventsTicketsList = new ArrayList<>();
        draftEventsTicketsList.add(new DraftEventsTickets("Сольный концерт Кайрата Нуртаса","вс, мая 1 2018","19:00"));
        draftEventsTicketsList.add(new DraftEventsTickets("Сольный концерт Кайрата Нуртаса","вс, мая 1 2018","19:00"));
        draftEventsTicketsList.add(new DraftEventsTickets("Сольный концерт Кайрата Нуртаса","вс, мая 1 2018","19:00"));

    }

}
