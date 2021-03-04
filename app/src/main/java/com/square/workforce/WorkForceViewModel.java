package com.square.workforce;


import com.square.workforce.model.Employee;
import com.square.workforce.model.Employees;
import com.square.workforce.repository.EmployeesRepository;

import java.util.Collections;
import java.util.Comparator;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import lombok.Getter;

/*
BusinessLogic.
 */
public class WorkForceViewModel extends ViewModel {
  private static final String TAG = "WorkForceViewModel";
  public static final String SORT_BY_NAME = "name";

  @Getter
  private MediatorLiveData<Employees> employeesLiveData = new MediatorLiveData<>();


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
    employeesRepository.populateEmployees();
    employeesLiveData.addSource(employeesRepository.getEmployeesLiveData(), employees -> employeesLiveData.setValue(employees));
    if (employeesLiveData.getValue() == null) {
      employeesLiveData.setValue(new Employees());
    }
  }

   public Employees sortEmployeesByParameter(@NonNull final String sortingParameter, @NonNull final Employees employees) {
     switch (sortingParameter) {
       case SORT_BY_NAME: return sortEmployeesByName(employees);
     }
     return employees;
   }

  private Employees sortEmployeesByName(@NonNull final Employees employees) {
    final Comparator<Employee> comparator = (employee1, employee2) -> employee1.getFullName().compareTo(employee2.getFullName());
    Collections.sort(employees.getEmployees(), comparator);
    return employees;
  }
}
