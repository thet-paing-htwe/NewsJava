package com.tphtwe.newsjava.api;

import com.tphtwe.newsjava.model.News;

import retrofit2.Call;

public class ApiService {
    public static ApiInterface apiInterface;

    public static ApiInterface getApiInterface(){
        apiInterface=ApiClient.getClient().create(ApiInterface.class);
        return apiInterface;
    }
    public static Call<News> getNews( String country,String category,String apiKey)
        {
            return ApiService.getApiInterface().getNews(
                    country,category,apiKey);
        }
    }


