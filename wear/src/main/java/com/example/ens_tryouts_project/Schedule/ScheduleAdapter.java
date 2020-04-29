package com.example.ens_tryouts_project.Schedule;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.widget.drawer.WearableNavigationDrawerView;

import com.example.ens_tryouts_project.R;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleView> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private ScheduleAdapter.OnItemClickListener onItemClickListener;
    List<String> daysOfTheWeek;
    Context context;

    public ScheduleAdapter(Context context, List<String> daysOfTheWeek){
        this.context = context;
        this.daysOfTheWeek = daysOfTheWeek;
    }

    public void setOnItemClickListener(ScheduleAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ScheduleAdapter.ScheduleView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.classic_text_view_layout, parent, false);
        return new ScheduleView(layoutView);
    }

    class ScheduleView extends RecyclerView.ViewHolder {
        TextView  textTextView;

        public ScheduleView(View itemView) {

            super(itemView);

            textTextView = itemView.findViewById(R.id.textTextView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ScheduleAdapter.ScheduleView holder, int position) {

        holder.textTextView.setText(daysOfTheWeek.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return daysOfTheWeek.size();
    }

}
