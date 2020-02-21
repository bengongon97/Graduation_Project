package com.example.ens_tryouts_project.Schedule;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleView> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private ScheduleAdapter.OnItemClickListener onItemClickListener;
    List<String> daysOfTheWeek;

    public ScheduleAdapter(List<String> daysOfTheWeek){
        this.daysOfTheWeek = daysOfTheWeek;
    }

    public void setOnItemClickListener(ScheduleAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ScheduleAdapter.ScheduleView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = new TextView(parent.getContext());
        return new ScheduleView(view);
    }

    class ScheduleView extends RecyclerView.ViewHolder {
        TextView textView;

        public ScheduleView(View itemView) {

            super(itemView);
            textView = (TextView) itemView;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ScheduleAdapter.ScheduleView holder, int position) {
        holder.textView.setText(daysOfTheWeek.get(position));

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
