package com.square.workforce.remotedatasource;

import com.square.workforce.model.Employees;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeApi {

  @GET("employees.json")
  Call<Employees> getEmployees();

}
