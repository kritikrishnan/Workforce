package com.square.workforce.view.adapter;

import com.square.workforce.model.Employee;

import java.util.List;

public interface BindableAdapter {
  void setData(List<Employee> employeesList);
}
