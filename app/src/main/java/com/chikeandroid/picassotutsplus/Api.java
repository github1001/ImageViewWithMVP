package com.chikeandroid.picassotutsplus;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("MXkJvYja")
    Call<String> getString();
}
