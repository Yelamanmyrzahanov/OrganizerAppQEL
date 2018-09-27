package kz.djunglestones.organizerappqel.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.budiyev.android.circularprogressbar.CircularProgressBar;

import java.util.List;

import kz.djunglestones.organizerappqel.Class.SoldOutCircleTickets;
import kz.djunglestones.organizerappqel.R;

public class SoldOutCircleTicketsRecyclerAdapter extends RecyclerView.Adapter<SoldOutCircleTicketsRecyclerAdapter.MyViewHolder> {
    Context context;
    List<SoldOutCircleTickets> soldOutCircleTicketsList;

    public SoldOutCircleTicketsRecyclerAdapter(Context context, List<SoldOutCircleTickets> soldOutCircleTicketsList) {
        this.context = context;
        this.soldOutCircleTicketsList = soldOutCircleTicketsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_circle_progress_bars_tickets, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int soldOut = soldOutCircleTicketsList.get(position).getTicketsSold();
        int total = soldOutCircleTicketsList.get(position).getTicketTotal();
        holder.ticket_type_tv.setText(soldOutCircleTicketsList.get(position).getTicketType());
        holder.sold_out_tickets_amount.setText(soldOutCircleTicketsList.get(position).getTicketsSold()+"/"+soldOutCircleTicketsList.get(position).getTicketTotal());
        holder.circularProgressBar.setProgress(percentageCount(soldOut,total));
        holder.percentage_tv.setText(percentageCount(soldOut,total)+" %");
    }

    private float percentageCount(int soldOut, int total) {
        float percent = (soldOut*100)/total;
        return percent;
    }

    @Override
    public int getItemCount() {
        return soldOutCircleTicketsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CircularProgressBar circularProgressBar;
        private TextView ticket_type_tv,sold_out_tickets_amount,percentage_tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            circularProgressBar = itemView.findViewById(R.id.circle_progressBar);
            ticket_type_tv = itemView.findViewById(R.id.ticket_type_tv);
            sold_out_tickets_amount = itemView.findViewById(R.id.sold_out_tickets_amount);
            percentage_tv = itemView.findViewById(R.id.percentage_tv);
        }
    }
}
