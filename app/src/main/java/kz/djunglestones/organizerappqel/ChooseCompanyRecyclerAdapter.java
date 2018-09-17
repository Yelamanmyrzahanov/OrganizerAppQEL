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

public class ChooseCompanyRecyclerAdapter extends RecyclerView.Adapter<ChooseCompanyRecyclerAdapter.MyViewHolder> {

    Context mContext;
    List<Company> companyList;
    private String company_name;

    private int selectedItemPosition = -1;

    public ChooseCompanyRecyclerAdapter(Context mContext, List<Company> companyList) {
        this.mContext = mContext;
        this.companyList = companyList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_view_choose_company, parent, false);
        Intent intent = ((ChooseCompanyActivity) mContext).getIntent();
        company_name = intent.getStringExtra("company_name");
        MyViewHolder myViewHolder = new MyViewHolder(v);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final int itemPosition = holder.getAdapterPosition();

        final Company company = companyList.get(itemPosition);
        holder.company_name_tv.setText(company.getCompanyName());

        holder.checked.setVisibility(
                selectedItemPosition == itemPosition ? View.VISIBLE : View.INVISIBLE
        );

//        for (Company companyObject : companyList) {
//            if (company.getCompanyName().equals(company_name)) {
//                company.isChecked = true;
//                holder.checked.setVisibility(View.VISIBLE);
//            }
//        }

        String company_name_first_letter = Character.toString(company.getCompanyName().charAt(0));
        TextDrawable textDrawable = TextDrawable.builder().buildRound(company_name_first_letter, Color.parseColor("#eeeeee"));

        holder.company_image.setImageDrawable(textDrawable);

        holder.main_constraint_itemview_choose_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedItemPosition = itemPosition;
                Toast.makeText(mContext, company.getCompanyName(), Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();

//                View parentRow = (View) v.getParent();
//                String current_company = company.getCompanyName();
//                Toast.makeText(mContext, current_company, Toast.LENGTH_SHORT).show();
//                if (company.isChecked) {
//                    company.setChecked(false);
//                    holder.checked.setVisibility(View.INVISIBLE);
//                } else {
//                    company.setChecked(true);
//                    holder.checked.setVisibility(View.VISIBLE);
//                }
            }
        });
    }

    public int getSelectedItemPosition() {
        return selectedItemPosition;
    }

    public void setSelectedItemPosition(int position) {
        selectedItemPosition = position;
    }


    @Override
    public int getItemCount() {
        return companyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView company_name_tv;
        private ImageView company_image, more_options_company_cardview, checked;
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
