package com.square.workforce;

import com.square.workforce.model.Employee;
import com.square.workforce.repository.EmployeesRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import lombok.NonNull;
import lombok.Setter;

/*
BusinessLogic.
 */
public class WorkForceViewModel extends ViewModel {
  private static final String SORT_BY_NAME = "name";
  MutableLiveData<List<Employee>> employeesLiveData;

  private EmployeesRepository employeesRepository;

  public WorkForceViewModel() {
    super();
    init();
  }

  public void init() {
    if (employeesRepository != null) {
      return;
    }
    employeesRepository = EmployeesRepository.getInstance();
  }

  //initialize MutableLiveData<List<Employee>> employees by lazy in the getter instead of in init()
  public LiveData<List<Employee>> getEmployees() {
    if (employeesLiveData == null) {
      employeesLiveData = employeesRepository.getEmployees();
    }
    return employeesLiveData;
  }

   public void sortEmployeesByParameter(@NonNull final String sortingParameter) {
     switch (sortingParameter) {
       case SORT_BY_NAME: sortEmployeesByName(); break;

       default: break;
     }
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
    employeesLiveData.postValue(employeesLiveData.getValue()); //post to update change in data
  }

}
