package kz.djunglestones.organizerappqel.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kz.djunglestones.organizerappqel.Activities.ChooseCompanyActivity;
import kz.djunglestones.organizerappqel.Activities.ChoosePromocodeTicketActivity;
import kz.djunglestones.organizerappqel.Fragments.DiscountPromocodeDialog;
import kz.djunglestones.organizerappqel.Models.TicketsForChoosePromocode;
import kz.djunglestones.organizerappqel.R;

public class ChoosePromocodeRecyclerAdapter  extends RecyclerView.Adapter<ChoosePromocodeRecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<TicketsForChoosePromocode> ticketsForChoosePromocodeList;
    private List<TicketsForChoosePromocode> resultTicketsForChoosePromocodeList;

    public ChoosePromocodeRecyclerAdapter(Context context, List<TicketsForChoosePromocode> ticketsForChoosePromocodeList) {
        this.context = context;
        this.ticketsForChoosePromocodeList = ticketsForChoosePromocodeList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_choose_promocode, parent, false);
        resultTicketsForChoosePromocodeList = new ArrayList<>();

//        Log.d("ticketsFromIntent", "onCreateViewHolder: "+ Arrays.toString(ticketsFromIntent));
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TicketsForChoosePromocode ticket = ticketsForChoosePromocodeList.get(position);

        holder.checkBox.setVisibility(
                ticket.isFree() ? View.GONE : View.VISIBLE
        );
        holder.ticketName.setText(ticket.getTicketName());
        holder.checkBox.setChecked(ticket.isChecked());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ticket.setChecked(isChecked);
                    ((Activity)context).invalidateOptionsMenu();

            }
        });
    }

    @Override
    public int getItemCount() {
        return ticketsForChoosePromocodeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView ticketName;
        private CheckBox checkBox;
        public MyViewHolder(View itemView) {
            super(itemView);
            ticketName = itemView.findViewById(R.id.apply_to_all_tv);
            checkBox = itemView.findViewById(R.id.apply_to_all_checkbox);

        }
    }

    public List<TicketsForChoosePromocode> getResultTicketsForChoosePromocodeList() {
        List<TicketsForChoosePromocode> list = new ArrayList<>();
        for (TicketsForChoosePromocode tickets:ticketsForChoosePromocodeList){
            if (tickets.isChecked() && !tickets.isFree()){
                list.add(tickets);
            }
        }
//        for (TicketsForChoosePromocode ticket:resultTicketsForChoosePromocodeList){
//            if (!ticket.isFree() && ticket.isChecked()){
//                list.add(ticket);
//            }
//        }
        return list;
    }
}
