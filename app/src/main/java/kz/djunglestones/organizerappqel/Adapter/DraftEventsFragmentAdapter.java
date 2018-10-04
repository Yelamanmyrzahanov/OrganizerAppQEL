package kz.djunglestones.organizerappqel.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kz.djunglestones.organizerappqel.Models.DraftEventsTickets;
import kz.djunglestones.organizerappqel.R;

public class DraftEventsFragmentAdapter extends RecyclerView.Adapter<DraftEventsFragmentAdapter.MyViewHolder>{
    List<DraftEventsTickets> draftEventsTicketsList;
    Context context;

    public DraftEventsFragmentAdapter(Context context,List<DraftEventsTickets> draftEventsTicketsList) {
        this.draftEventsTicketsList = draftEventsTicketsList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_draft_event_tickets, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.eventDate.setText(draftEventsTicketsList.get(position).getEventDate()+", "+draftEventsTicketsList.get(position).getEventTime());
        holder.eventName.setText(draftEventsTicketsList.get(position).getEventTime());
    }

    @Override
    public int getItemCount() {
        return draftEventsTicketsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView eventName,eventDate;
        public MyViewHolder(View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.event_name_draft_events);
            eventDate = itemView.findViewById(R.id.event_date_draft_events);
        }
    }
}
