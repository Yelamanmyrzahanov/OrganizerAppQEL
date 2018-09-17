package kz.djunglestones.organizerappqel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PopUpThemeRecyclerAdapter extends RecyclerView.Adapter<PopUpThemeRecyclerAdapter.MyViewHolder>{
    Context context;
    List<PopUpThemeClass> popUpThemeClassList;

    public PopUpThemeRecyclerAdapter(Context context, List<PopUpThemeClass> popUpThemeClassList) {
        this.context = context;
        this.popUpThemeClassList = popUpThemeClassList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.pop_up_themes,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.theme_name_tv.setText(popUpThemeClassList.get(position).getThemeName());
        holder.theme_image.setImageResource(popUpThemeClassList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return popUpThemeClassList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView theme_name_tv;
        ImageView theme_image;
        public MyViewHolder(View itemView) {
            super(itemView);
            theme_name_tv = itemView.findViewById(R.id.theme_name_tv);
            theme_image = itemView.findViewById(R.id.theme_image);
        }
    }
}
