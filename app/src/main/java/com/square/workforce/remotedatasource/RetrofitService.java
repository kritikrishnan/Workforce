package com.square.workforce.remotedatasource;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

  private static final String BASE_URL ="https://s3.amazonaws.com/sq-mobile-interview/";

  private static Retrofit employees = new Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build();

  public static <S> S employeesService(Class<S> serviceClass) {
    return employees.create(serviceClass);
  }

}
