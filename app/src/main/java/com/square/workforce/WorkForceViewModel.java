package com.square.workforce;

import android.app.Application;

import com.square.workforce.model.Employee;
import com.square.workforce.repository.EmployeesRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import lombok.NonNull;

/*
BusinessLogic.
 */
public class WorkForceViewModel extends ViewModel {
  private static final String SORT_BY_NAME = "name";
  MutableLiveData<List<Employee>> employeesLiveData;
  Application appContext;

  private EmployeesRepository employeesRepository;

  public WorkForceViewModel(WorkForceApplication workforceApplicationContext) {
    super();
    appContext = workforceApplicationContext;
    init();
  }

  public void init() {
    if (employeesRepository != null) {
      return;
    }
    employeesRepository = EmployeesRepository.getInstance();
    getEmployees();
    sortEmployeesByParameter(SORT_BY_NAME);
  }

  public void getEmployees() {
    if (employeesLiveData == null) {
      employeesLiveData = employeesRepository.getEmployees();
    }
  }

   public void sortEmployeesByParameter(@NonNull final String sortingParameter) {
     switch (sortingParameter) {
       case SORT_BY_NAME: sortEmployeesByName(); break;

       default: break;
     }
     employeesLiveData.postValue(employeesLiveData.getValue()); //post to update change in data
   }

  private void sortEmployeesByName() {
    if (employeesLiveData.getValue() == null) {return;}
    final Comparator<Employee> comparator = new Comparator<Employee>() {
      @Override
      public int compare(Employee employee1, Employee employee2) {
        return employee1.getFullName().compareTo(employee2.getFullName());
      }
    };
    Collections.sort(employeesLiveData.getValue(), comparator);
  }
}
