package com.square.workforce.repository;

import com.square.workforce.model.Employee;
import com.square.workforce.remotedatasource.EmployeeApi;
import com.square.workforce.remotedatasource.RetrofitService;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 Uses LiveData to observe changes to data used in this project
*/
public class EmployeesRepository {

  final MutableLiveData<List<Employee>> mutableEmployees = new MutableLiveData<>();
  private static EmployeesRepository employeesRepository;

  public static EmployeesRepository getInstance(){
    if (employeesRepository == null){
      employeesRepository = new EmployeesRepository();
    }
    return employeesRepository;
  }

  private EmployeeApi employeeApi;

  public EmployeesRepository(){
    employeeApi = RetrofitService.employeesService(EmployeeApi.class);
  }

  public MutableLiveData<List<Employee>> getEmployees(){
    employeeApi.getEmployees().enqueue(new Callback<List<Employee>>() {
      @Override
      public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
        if (response.isSuccessful()) {
          mutableEmployees.setValue(response.body());
        }
        //TODO: handle else
      }

      @Override
      public void onFailure(Call<List<Employee>> call, Throwable t) {
        //TODO: handle failure
      }
    });

    return mutableEmployees;
  }
}
