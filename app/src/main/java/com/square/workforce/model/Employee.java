package com.square.workforce.model;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Employee {

  @SerializedName("full_name")
  private String fullName;

  @SerializedName("phone_number")
  private String phoneNumber;

  @SerializedName("email_address")
  private String emailAddress;

  private String biography;

  @SerializedName("photo_url_large")
  private String photoUrlLarge;

  @SerializedName("photo_url_small")
  private String photoUrlSmall;

  private String team;

  @SerializedName("employee_type")
  private String employeeType;

  public enum EmployeeTypes {
    FULL_TIME,
    PART_TIME,
    CONTRACTOR
  }

  public void populateEmployeeType(Employees employees) {
    for (Employee employee : employees.getEmployees()) {
      switch (Employee.EmployeeTypes.valueOf(employee.getEmployeeType())) {
        case FULL_TIME:
          employee.setEmployeeType("Full time");
          break;
        case PART_TIME:
          employee.setEmployeeType("Part time");
          break;
        case CONTRACTOR:
          employee.setEmployeeType("Contractor");
          break;
      }
    }
  }
}
