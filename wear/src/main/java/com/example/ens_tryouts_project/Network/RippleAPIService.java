package com.example.ens_tryouts_project.Network;

import com.example.ens_tryouts_project.MenuOfTheDay.MenuOfTheDayClass;
import com.example.ens_tryouts_project.Schedule.ScheduleClass;
import com.example.ens_tryouts_project.Shuttle.ShuttleClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RippleAPIService {
    @GET("meal.php")
    Call<List<MenuOfTheDayClass>> menuCall ();

    @GET("course_schedule.php?termcode=201901")
    Call<ScheduleClass> scheduleCall();

    @GET("shuttle_json.php")
    Call<List<ShuttleClass>> shuttleCall ();
}