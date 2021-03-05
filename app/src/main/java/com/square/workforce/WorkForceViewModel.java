package com.square.workforce;


import com.square.workforce.model.Employee;
import com.square.workforce.model.Employees;
import com.square.workforce.model.State;
import com.square.workforce.repository.EmployeesRepository;

import java.util.Collections;
import java.util.Comparator;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import lombok.Getter;
import lombok.Setter;

/*
BusinessLogic.
 */
public class WorkForceViewModel extends ViewModel {
  private static final String TAG = "WorkForceViewModel";
  public static final String SORT_BY_NAME = "name";

  @Getter
  @Setter
  private MediatorLiveData<Employees> employeesLiveData = new MediatorLiveData<>();

  @Getter
  @Setter
  private MediatorLiveData<State> stateLiveData = new MediatorLiveData<>();

  private EmployeesRepository employeesRepository;

  public WorkForceViewModel() {
    super();
    init();
  }

  public void init() {
    if (employeesRepository != null) {
      return;
    }
    //set initial values to live data
    stateLiveData.setValue(State.LOADING);
    employeesLiveData.setValue(new Employees());

    employeesRepository = EmployeesRepository.getInstance();
    employeesRepository.populateEmployees();
    employeesLiveData.addSource(employeesRepository.getEmployeesLiveData(), employees -> employeesLiveData.setValue(employees));
    stateLiveData.addSource(employeesRepository.getState(), state -> stateLiveData.setValue(state));
  }

   public Employees sortEmployeesByParameter(@NonNull final String sortingParameter, @NonNull final Employees employees) {
     switch (sortingParameter) {
       case SORT_BY_NAME: return sortEmployeesByName(employees);
     }
     return employees;
   }

  private Employees sortEmployeesByName(@NonNull final Employees employees) {
    final Comparator<Employee> comparator = (employee1, employee2) -> {

      if (employee1.getFullName() == null && employee2.getFullName() == null) {
        return 0;
      }

      if (employee1.getFullName() == null || employee2.getFullName() == null) {
        return employee1.getFullName() == null ? -1 : 1;
      }

      return employee1.getFullName().compareTo(employee2.getFullName());
    };

    Collections.sort(employees.getEmployees(), comparator);
    return employees;
  }
}
