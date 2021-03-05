package com.square.workforce;

import com.square.workforce.model.Employee;
import com.square.workforce.model.Employees;

import java.util.ArrayList;
import java.util.List;

public final class TestUtils {
  private static List<Employee> employeeDummyList = new ArrayList<>();

  public static Employees getNullData() {
    return null;
  }

  public static Employees getData() {
    Employees employees = new Employees();
    employees.setEmployees(createDummyEmployeeList());
    return employees;
  }

  public static Employees getMalformedData() {
    Employees employees = new Employees();
    employees.setEmployees(createDummyMalformedEmployeeList());
    return employees;
  }

  public static Employees getSortedByNameMalformedData() {
    Employees employees = new Employees();
    employees.setEmployees(createSortedByNameDummyMalformedList());
    return employees;
  }

  public static Employees getSortedByNameData() {
    Employees employees = new Employees();
    employees.setEmployees(createSortByNameDummyList());
    return employees;
  }

  public static Employees getEmptyData() {
    return new Employees();
  }

  private static final Employee malformedEmployee1 = Employee.builder()
    .fullName("Raj porwal")
    .phoneNumber("123445678")
    .emailAddress("rajpo@gmail.com")
    .team("E-Commerce")
    .employeeType("FULL_TIME")
    .photoUrlSmall("https://someImage.com/raj.jpg")
    .photoUrlLarge("https://someImage.com/raj_large.jpg")
    .build();

  private static final Employee malformedEmployee2 = Employee.builder()
    .phoneNumber("9876543")
    .emailAddress("kriti@gmail.com")
    .team("Payments")
    .biography("I'm awesome")
    .employeeType("FULL_TIME")
    .photoUrlSmall("https://someImage.com/kriti.jpg")
    .photoUrlLarge("https://someImage.com/kriti_large.jpg")
    .build();

  private static final Employee malformedEmployee3 = Employee.builder()
    .fullName("Dhanniya")
    .emailAddress("dumbo@gmail.com")
    .team("Product")
    .biography("I'm a Product developer and you will love my product")
    .employeeType("PART_TIME")
    .photoUrlSmall("https://someImage.com/dumbo.jpg")
    .photoUrlLarge("https://someImage.com/dumbo_large.jpg")
    .build();

  private static final Employee employee1 = Employee.builder()
    .fullName("Raj porwal")
    .phoneNumber("123445678")
    .emailAddress("rajpo@gmail.com")
    .team("E-Commerce")
    .biography("I'm a winner")
    .employeeType("FULL_TIME")
    .photoUrlSmall("https://someImage.com/raj.jpg")
    .photoUrlLarge("https://someImage.com/raj_large.jpg")
    .build();

  private static final Employee employee2 = Employee.builder()
    .fullName("Kriti Krishnan")
    .phoneNumber("9876543")
    .emailAddress("kriti@gmail.com")
    .team("Payments")
    .biography("I'm awesome")
    .employeeType("FULL_TIME")
    .photoUrlSmall("https://someImage.com/kriti.jpg")
    .photoUrlLarge("https://someImage.com/kriti_large.jpg")
    .build();

  private static final Employee employee3 = Employee.builder()
    .fullName("Dhanniya")
    .phoneNumber("75960574")
    .emailAddress("dumbo@gmail.com")
    .team("Product")
    .biography("I'm a Product developer and you will love my product")
    .employeeType("PART_TIME")
    .photoUrlSmall("https://someImage.com/dumbo.jpg")
    .photoUrlLarge("https://someImage.com/dumbo_large.jpg")
    .build();

  private static List<Employee> createDummyEmployeeList() {
    employeeDummyList.add(employee1);
    employeeDummyList.add(employee2);
    employeeDummyList.add(employee3);
    return employeeDummyList;
  }

  private static List<Employee> createDummyMalformedEmployeeList() {
    employeeDummyList.add(malformedEmployee1);
    employeeDummyList.add(malformedEmployee2);
    employeeDummyList.add(malformedEmployee3);
    return employeeDummyList;
  }

  private static List<Employee> createSortedByNameDummyMalformedList() {
    employeeDummyList.set(0, malformedEmployee3);
    employeeDummyList.set(1, malformedEmployee1);
    employeeDummyList.set(2, malformedEmployee2);
    return employeeDummyList;
  }

  private static List<Employee> createSortByNameDummyList() {
    employeeDummyList.set(0, employee3);
    employeeDummyList.set(1, employee2);
    employeeDummyList.set(2, employee1);
    return employeeDummyList;
  }
}
