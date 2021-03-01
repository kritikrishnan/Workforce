package com.square.workforce.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import retrofit2.http.Url;

@Getter
@Setter
public class Employee {

  @SerializedName("full_name")
  private String fullName;

  @SerializedName("phone_number")
  private String phoneNumber;

  @SerializedName("email_address")
  private String emailAddress;

  private String biography;

  @SerializedName("image_url_large")
  private Url photoUrlLarge;

  @SerializedName("image_url_small")
  private Url photoUrlSmall;

  private String team;

  @SerializedName("employee_type")
  private String employeeType;

}
