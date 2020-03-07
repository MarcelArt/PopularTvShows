package com.example.populartvshows.api;

import com.example.populartvshows.models.Tv;
import com.example.populartvshows.models.Tvs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("tv/popular?api_key=54fb0736054c2be79f6f930f4cae9a70")
    Call<Tvs> listTvs();
}
