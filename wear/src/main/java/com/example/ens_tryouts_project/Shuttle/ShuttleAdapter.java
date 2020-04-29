package com.example.ens_tryouts_project.Shuttle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ens_tryouts_project.R;
import com.example.ens_tryouts_project.Schedule.ScheduleAdapter;

import java.util.List;


public class ShuttleAdapter extends RecyclerView.Adapter<ShuttleAdapter.ShuttleView> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private ShuttleAdapter.OnItemClickListener onItemClickListener;
    List<String> destinationList;

    public ShuttleAdapter(List<String> destinationList){
        this.destinationList = destinationList;
    }

    public void setOnItemClickListener(ShuttleAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ShuttleAdapter.ShuttleView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.classic_text_view_layout, parent, false);
        return new ShuttleAdapter.ShuttleView(layoutView);
    }

    class ShuttleView extends RecyclerView.ViewHolder {
        TextView  textTextView;

        public ShuttleView(View itemView) {

            super(itemView);

            textTextView = itemView.findViewById(R.id.textTextView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ShuttleAdapter.ShuttleView holder, int position) {
        holder.textTextView.setText(destinationList.get(position));

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
        return destinationList.size();
    }

}