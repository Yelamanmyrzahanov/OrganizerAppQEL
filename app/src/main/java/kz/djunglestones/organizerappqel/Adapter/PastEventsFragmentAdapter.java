package kz.djunglestones.organizerappqel.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.budiyev.android.circularprogressbar.CircularProgressBar;

import java.util.List;

import kz.djunglestones.organizerappqel.Models.PastEventsTickets;
import kz.djunglestones.organizerappqel.R;

public class PastEventsFragmentAdapter extends RecyclerView.Adapter<PastEventsFragmentAdapter.MyViewHolder>{
    List<PastEventsTickets> pastEventsTicketsList;
    Context context;

    public PastEventsFragmentAdapter(Context context,List<PastEventsTickets> pastEventsTicketsList) {
        this.pastEventsTicketsList = pastEventsTicketsList;
        this.context = context;
    }



    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_past_event_tickets, parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int soldOut = pastEventsTicketsList.get(position).getTicketsSold();
        int total = pastEventsTicketsList.get(position).getTicketsTotal();
        holder.circularProgressBar.setProgress(percentageCount(soldOut,total));
        holder.percentage_tv_center_cirlce.setText(String.valueOf(percentageCount(soldOut,total))+"%");
        holder.money_tv.setText(String.valueOf(pastEventsTicketsList.get(position).getTicketCost())+" ₸");
        holder.event_name_past_events.setText(pastEventsTicketsList.get(position).getEventName());
        holder.event_date_past_events.setText(String.valueOf(pastEventsTicketsList.get(position).getDate())+", "+String.valueOf(pastEventsTicketsList.get(position).getTime()));
        holder.tickets_sold_past_events.setText(String.valueOf(pastEventsTicketsList.get(position).getTicketsSold())+" / "+String.valueOf(pastEventsTicketsList.get(position).getTicketsTotal()));
    }

    private int percentageCount(int soldOut, int total) {
        return Math.round((float) soldOut *100/ (float) total);
    }

    @Override
    public int getItemCount() {
        return pastEventsTicketsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView event_name_past_events,event_date_past_events,tickets_sold_past_events,money_tv,percentage_tv_center_cirlce;
        private CircularProgressBar circularProgressBar;
        MyViewHolder(View itemView) {
            super(itemView);
            circularProgressBar = itemView.findViewById(R.id.circle_progressBar_past_events);
            event_name_past_events = itemView.findViewById(R.id.event_name_past_events);
            event_date_past_events = itemView.findViewById(R.id.event_date_past_events);
            tickets_sold_past_events = itemView.findViewById(R.id.tickets_sold_past_events);
            money_tv = itemView.findViewById(R.id.money_tv);
            percentage_tv_center_cirlce = itemView.findViewById(R.id.percentage_tv_center_cirlce);
        }
    }
}
