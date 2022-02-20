package com.example.scolarite.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
 private static Retrofit retrofit;
 private static String BASE_URL = "http://192.168.0.191:8080/api/";
 public  static  Retrofit getRetrofitInstance(){
   if(retrofit==null){
     retrofit = new  Retrofit.Builder().baseUrl(BASE_URL)
       .addConverterFactory(GsonConverterFactory.create())
     .build();
   }
   return  retrofit;
 }
}
