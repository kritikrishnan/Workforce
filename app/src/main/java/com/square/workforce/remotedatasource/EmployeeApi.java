package com.square.workforce.remotedatasource;

import com.square.workforce.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeApi {

  @GET("Employee")
  Call<List<Employee>> getEmployees();

}
