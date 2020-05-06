package com.example.ens_tryouts_project.Schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ens_tryouts_project.R;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDetailedAdapter extends RecyclerView.Adapter<ScheduleDetailedAdapter.ScheduleDetailedView> {

    ScheduleDaysSubClass theClassOfToday;
    //private String theDay;

    public ScheduleDetailedAdapter(ScheduleDaysSubClass theClassOfToday) {
        this.theClassOfToday = theClassOfToday;
    }

    class ScheduleDetailedView extends RecyclerView.ViewHolder {
        TextView classNameTextView;
        TextView classHourTextView;
        TextView locationTextView;

        ScheduleDetailedView(View itemView) {
            super(itemView);

            locationTextView = itemView.findViewById(R.id.locationTextView);
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
        if(theClassOfToday == null || theClassOfToday.getClassCodeAndName().size() == 0){
            holder.classNameTextView.setText("There is no class today.");
            holder.classHourTextView.setText("No hour can be found.");
            holder.locationTextView.setText("");
        }
        else{

            List<String> begintime = theClassOfToday.getBegintime();
            List<String> endtime = theClassOfToday.getEndtime();
            List<String> uniqueall = theClassOfToday.getClassCodeAndName();
            List<String> roomcode = theClassOfToday.getRoomcode();
            List<String> buildingcode = theClassOfToday.getBuildingcode();

            List<String> alltimes = new ArrayList<>();
            List<String> alllocations = new ArrayList<>();

            for(int i = 0; i < begintime.size(); i++){
                alltimes.add(begintime.get(i) + " - " + endtime.get(i));
                alllocations.add(buildingcode.get(i) + " " + roomcode.get(i));
            }

            holder.classNameTextView.setText(uniqueall.get(holder.getAdapterPosition()));
            holder.classHourTextView.setText(alltimes.get(holder.getAdapterPosition()));
            holder.locationTextView.setText(alllocations.get(holder.getAdapterPosition()));
        }
    }

    @Override
    public int getItemCount() {
        if(theClassOfToday.getClassCodeAndName().size() != 0)
            return theClassOfToday.getClassCodeAndName().size();
        else
            return 1; //to show that there is no class
    }
}
