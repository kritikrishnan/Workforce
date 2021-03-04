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
}
