package com.square.workforce.model;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employees {
  List<Employee> employees;

  public Employees() {
    employees = Collections.emptyList();
  }

  public boolean equals(Employees employees1) {
    return this.getEmployees().size() == employees1.getEmployees().size() && this.getEmployees().containsAll(employees1.getEmployees());
  }
}
