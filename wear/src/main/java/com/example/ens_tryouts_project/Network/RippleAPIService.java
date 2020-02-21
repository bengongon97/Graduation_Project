package com.example.ens_tryouts_project.Network;

import com.example.ens_tryouts_project.MenuOfTheDay.MenuOfTheDayClass;
import com.example.ens_tryouts_project.Schedule.ScheduleClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RippleAPIService {

    @GET("meal.php")
    Call<List<MenuOfTheDayClass>> menuCall ();

    //@Headers("Content-Type: application/json")
    @GET("course_schedule.php?termcode=201901")
    Call<ScheduleClass> scheduleCall();


    //https://www.sabanciuniv.edu/apps/test/course_schedule.php?termcode=201901


    /*
    // For ledger
    @GET("/v2/{type}/{identifier}")
    Call<LedgerResponse> ledgerSummary(@Path("type") String searchType, @Path("identifier") String identifier,
                                       @Query("transactions") String transaction, @Query("binary") String binary,
                                       @Query("expand") String expand);
    //For transaction
    @GET("/v2/transactions/{identifier}")
    Call<TransactionResponse> transactionSummary(@Path("identifier") String identifier, @Query("type") String type);

    //For account
    @GET("/v2/accounts/{identifier}")
    Call<AccountResponse> accountSummary(@Path("identifier") String identifier);
    */
}
