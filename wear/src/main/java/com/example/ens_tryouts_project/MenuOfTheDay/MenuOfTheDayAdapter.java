package com.example.ens_tryouts_project.MenuOfTheDay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ens_tryouts_project.R;

import java.util.List;

public class MenuOfTheDayAdapter extends RecyclerView.Adapter<MenuOfTheDayAdapter.MenuOfTheDayView> {

    Context context;
    List<MenuOfTheDayClass> menuArray;

    public MenuOfTheDayAdapter(Context context, List<MenuOfTheDayClass> menuArray) {
        this.context = context; //  RIGHT NOW, I DO NOT NEED THE CONTEXT BUT I AM JUST TAKING IT TO BE SAFE.
        this.menuArray = menuArray;
    }

    class MenuOfTheDayView extends RecyclerView.ViewHolder {
        TextView  mealNameTextView;
        TextView  tabldotTextView;
        TextView  calorieInfoTextView;

        public MenuOfTheDayView(View itemView) {

            super(itemView);

            mealNameTextView = itemView.findViewById(R.id.mealNameTextView);
            tabldotTextView = itemView.findViewById(R.id.tabldotTextView);
            calorieInfoTextView = itemView.findViewById(R.id.calorieInfoTextView);
        }
    }

    @NonNull
    @Override
    public MenuOfTheDayView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_of_the_day_detailed, parent, false);
        return new MenuOfTheDayView(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuOfTheDayView holder, int position) {

        MenuOfTheDayClass myMenuItem = menuArray.get(holder.getAdapterPosition());

        if(myMenuItem.getMeal() != null)
            holder.mealNameTextView.setText(myMenuItem.getMeal_en());

        //no nullity condition for these, if needed, add one.
        if(myMenuItem.isDinner() && myMenuItem.isLunch())
            holder.tabldotTextView.setText("Lunch, Dinner"); //to be generalized later
        else if(!myMenuItem.isDinner() && myMenuItem.isLunch())
            holder.tabldotTextView.setText("Lunch");
        else if(!myMenuItem.isDinner() && !myMenuItem.isLunch())
            holder.tabldotTextView.setText("Not Included in Fixed Menu");
        else if(myMenuItem.isDinner() && !myMenuItem.isLunch())
            holder.tabldotTextView.setText("Dinner");

        if(myMenuItem.getCalorie() != null)
            holder.calorieInfoTextView.setText(myMenuItem.getCalorie());
    }

    @Override
    public int getItemCount() {
        return menuArray.size();
    }
}
