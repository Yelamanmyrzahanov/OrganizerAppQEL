package kz.djunglestones.organizerappqel.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kz.djunglestones.organizerappqel.Models.PromoCode;
import kz.djunglestones.organizerappqel.R;

public class PromoCodeRecyclerAdapter extends RecyclerView.Adapter<PromoCodeRecyclerAdapter.MyViewHolder> {
    private List<PromoCode> promoCodeList;
    private Context context;

    public PromoCodeRecyclerAdapter(List<PromoCode> promoCodeList, Context context) {
        this.promoCodeList = promoCodeList;
        this.context = context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_promocode, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.promocode_name.setText(promoCodeList.get(position).getTicket_name());
        holder.ticket_name.setText(promoCodeList.get(position).getPromo_code_name());
    }

    @Override
    public int getItemCount() {
        return promoCodeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView ticket_name;
        private TextView promocode_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            ticket_name = itemView.findViewById(R.id.promocode_name_tv);
            promocode_name = itemView.findViewById(R.id.promocode);

        }
    }
}
