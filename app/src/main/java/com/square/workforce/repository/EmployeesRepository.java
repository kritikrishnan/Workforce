package com.square.workforce.repository;

import android.util.Log;

import com.square.workforce.model.Employees;
import com.square.workforce.model.State;
import com.square.workforce.remotedatasource.EmployeeApi;
import com.square.workforce.remotedatasource.RetrofitService;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import lombok.Getter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 Uses LiveData to observe changes to data used in this project.
 This is a Singleton class - We only want a single instance of Employees repository floating around in the app.
 Multiple instances would mean the possibility of multiple sets of data
*/
public class EmployeesRepository {


  @Getter
  final MutableLiveData<Employees> employeesLiveData = new MutableLiveData<>();

  @Getter
  final MutableLiveData<State> state = new MutableLiveData<>();

  private static EmployeesRepository employeesRepository;
  private static final String TAG = "Employees Repository";

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

  public void populateEmployees(){
    state.setValue(State.LOADED);
    employeeApi.getEmployees().enqueue(new Callback<Employees>() {
      @Override
      public void onResponse(@NonNull Call<Employees> call, @NonNull Response<Employees> response) {
        if (response.isSuccessful()) {
          if (response.body() != null && response.body().getEmployees().isEmpty()) {
            Log.d(TAG, "response was empty");
            state.setValue(State.EMPTY);
          } else {
            Log.d(TAG, "Successfully received data");
            state.setValue(State.LOADED);
          }
          employeesLiveData.setValue(response.body());
          employeesLiveData.postValue(response.body());
        } else {
          state.setValue(State.ERROR);
          Log.d(TAG, "Something went wrong " + response.errorBody());
        }
      }

      @Override
      public void onFailure(@NonNull Call<Employees> call, @NonNull Throwable throwable) {
        state.setValue(State.ERROR);
        Log.d(TAG, "Exception" + throwable.getCause() + " " + Arrays.toString(throwable.getStackTrace()));
      }
    });
  }
}
