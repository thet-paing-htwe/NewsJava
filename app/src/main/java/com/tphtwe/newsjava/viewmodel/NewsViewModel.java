package com.tphtwe.newsjava.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tphtwe.newsjava.api.ApiService;
import com.tphtwe.newsjava.model.News;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {
    MutableLiveData<News> resutlts= new MutableLiveData<>();
    MutableLiveData<Boolean> loadError=new MutableLiveData<>();
    MutableLiveData<Boolean> loading= new MutableLiveData<>();

    public MutableLiveData<News> getResutlts() {
        return resutlts;
    }

    public MutableLiveData<Boolean> getLoadError() {
        return loadError;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public void laodResults(){
        loading.setValue(true);

        String apiKey="28d11ca4baed4b118135c12f1f72a13d";

        Call<News> newsCall= ApiService.getNews("us","technology",apiKey);
        newsCall.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(response.isSuccessful() && response.body()!=null)
                {
                    loading.setValue(false);
                    resutlts.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                loadError.setValue(true);
                loading.setValue(false);

            }
        });
    }
}
