package com.example.ens_tryouts_project.Network_And_Settings;

import com.example.ens_tryouts_project.MenuOfTheDay.MenuOfTheDayClass;
import com.example.ens_tryouts_project.SUCard.SUCardClass;
import com.example.ens_tryouts_project.Shuttle.ShuttleClass;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RippleAPIService {
    @GET("meal.php?sdate=2020-01-09&edate=2020-01-09")
    Call<List<MenuOfTheDayClass>> menuCall();

    @GET("course_schedule.php?termcode=201901")
    Call<Map<String, Object>> scheduleCall();

    @GET("shuttle_json.php")
    Call<List<ShuttleClass>> shuttleCall();

    @GET("sucard.php")
    Call<List<SUCardClass>> suCardCall();
}