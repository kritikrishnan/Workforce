package com.square.workforce.remotedatasource;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

  private static final String BASE_URL ="https://s3.amazonaws.com/sq-mobile-interview/";
  private static final String EMPLOYEE_DETAILS_PERMALINK = "employees.json";

  private static Retrofit employees = new Retrofit.Builder()
    .baseUrl(constructUrl(EMPLOYEE_DETAILS_PERMALINK))
    .addConverterFactory(GsonConverterFactory.create())
    .build();

  private static String constructUrl(final String permalink) {
    final StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(BASE_URL);
    stringBuilder.append(permalink);
    return stringBuilder.toString();
  }

  public static <S> S employeesService(Class<S> serviceClass) {
    return employees.create(serviceClass);
  }

}
