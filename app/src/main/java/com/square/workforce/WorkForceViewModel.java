package com.square.workforce;


import com.square.workforce.model.Employee;
import com.square.workforce.repository.EmployeesRepository;
import com.square.workforce.view.adapter.BindableAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

/*
BusinessLogic.
 */
public class WorkForceViewModel extends ViewModel {
  private static final String SORT_BY_NAME = "name";
  private MutableLiveData<List<Employee>> employeesLiveData;
  public List<Employee> employees;

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
    populateEmployees();
  }

  public void populateEmployees() {
    employeesLiveData = employeesRepository.getEmployees();
    sortEmployeesByParameter(SORT_BY_NAME);
    employees = employeesLiveData.getValue();
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

  @BindingAdapter("employees")
  public void bindEmployeeDataToRecyclerViewAdapter(@NonNull final RecyclerView recyclerView, final List<Employee> employees) {
    if (recyclerView.getAdapter() instanceof BindableAdapter) {
      ((BindableAdapter)recyclerView.getAdapter()).setData(employees);
    }
  }
}
