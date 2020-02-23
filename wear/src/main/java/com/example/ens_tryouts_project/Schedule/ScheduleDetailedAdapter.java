package com.example.ens_tryouts_project.Schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ens_tryouts_project.R;

import java.util.List;

public class ScheduleDetailedAdapter extends RecyclerView.Adapter<ScheduleDetailedAdapter.ScheduleDetailedView> {

    List<List<String>> classListOfTheList;
    private String theDay;

    public ScheduleDetailedAdapter(List<List<String>> classListOfTheList, String theDay) {
        this.classListOfTheList = classListOfTheList;
        this.theDay = theDay;
    }

    class ScheduleDetailedView extends RecyclerView.ViewHolder {
        TextView classNameTextView;
        TextView classHourTextView;

        ScheduleDetailedView(View itemView) {
            super(itemView);

            classNameTextView =  itemView.findViewById(R.id.classNameTextView);
            classHourTextView = itemView.findViewById(R.id.classHourTextView);
        }
    }

    @Override
    public ScheduleDetailedView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_detailed_program_layout, parent, false);
        return new ScheduleDetailedView(layoutView);
    }

    @Override
    public void onBindViewHolder(ScheduleDetailedView holder, final int position) {
        if(classListOfTheList.size() == 0 || classListOfTheList.get(0).size() == 0 || classListOfTheList.get(1).size() == 0){
            holder.classNameTextView.setText("There is no class today.");
            holder.classHourTextView.setText("No hour can be found.");
        }
        else{
            holder.classNameTextView.setText(classListOfTheList.get(0).get(holder.getAdapterPosition()));
            holder.classHourTextView.setText(classListOfTheList.get(1).get(holder.getAdapterPosition()));
        }
    }

    @Override
    public int getItemCount() {
        if(classListOfTheList.get(0).size() != 0)
            return classListOfTheList.get(0).size();
        if(classListOfTheList.get(1).size() != 0)
            return classListOfTheList.get(1).size();
        else
            return 1; //to show that there is no class
    }
}
