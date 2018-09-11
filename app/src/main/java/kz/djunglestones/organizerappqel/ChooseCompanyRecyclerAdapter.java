package kz.djunglestones.organizerappqel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.List;

public class ChooseCompanyRecyclerAdapter extends RecyclerView.Adapter<ChooseCompanyRecyclerAdapter.MyViewHolder>{

    Context mContext;
    List<Company> companyList;
    private String company_name;

    public ChooseCompanyRecyclerAdapter(Context mContext, List<Company> companyList) {
        this.mContext = mContext;
        this.companyList = companyList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_view_choose_company,parent,false);
        Intent intent = ((ChooseCompanyActivity)mContext).getIntent();
        company_name = intent.getStringExtra("company_name");
        MyViewHolder myViewHolder = new MyViewHolder(v);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Company company = companyList.get(holder.getAdapterPosition());
        holder.company_name_tv.setText(companyList.get(position).getCompanyName());
//        if (companyList.get(position).isChecked()){
//            holder.checked.setVisibility(View.VISIBLE);
//        }else{
//            holder.checked.setVisibility(View.INVISIBLE);
//        }

        for(Company companyObject:companyList){
            if (companyList.get(position).getCompanyName().equals(company_name)){
                companyList.get(position).isChecked = true;
                holder.checked.setVisibility(View.VISIBLE);
            }
        }
        String company_name_first_letter = Character.toString(companyList.get(position).getCompanyName().charAt(0));
        TextDrawable textDrawable = TextDrawable.builder().buildRound(company_name_first_letter, Color.parseColor("#eeeeee"));

        holder.company_image.setImageDrawable(textDrawable);

        holder.main_constraint_itemview_choose_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String current_company = companyList.get(position).getCompanyName();
                Toast.makeText(mContext,current_company,Toast.LENGTH_SHORT).show();
                for(Company companyObject:companyList){
                    if (companyObject.getCompanyName().equals(current_company)){
                        Toast.makeText(mContext,current_company+" EQUALS"+companyObject.getCompanyName(),Toast.LENGTH_SHORT).show();
                        companyObject.isChecked = true;
                        holder.checked.setVisibility(View.VISIBLE);
                    }
                    else{
                        Toast.makeText(mContext,current_company+" NOT EQUALS"+companyObject.getCompanyName(),Toast.LENGTH_SHORT).show();
                        companyObject.isChecked = false;
                        holder.checked.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });







;

    }


    @Override
    public int getItemCount() {
        return companyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView company_name_tv;
        private ImageView company_image,more_options_company_cardview,checked;
        ConstraintLayout main_constraint_itemview_choose_company;

        public MyViewHolder(View itemView) {
            super(itemView);

            more_options_company_cardview = itemView.findViewById(R.id.more_options_company_cardview);
            company_image = itemView.findViewById(R.id.company_image);
            checked = itemView.findViewById(R.id.checked);
            company_name_tv = itemView.findViewById(R.id.company_name_tv);

            main_constraint_itemview_choose_company = itemView.findViewById(R.id.main_constraint_itemview_choose_company);
        }
    }
}
