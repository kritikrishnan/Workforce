package com.square.workforce;

public class TestWorkForceApplication extends WorkForceApplication {

  @Override
  public void onCreate() {
    super.onCreate();
    setTheme(R.style.AppTheme); //or just R.style.Theme_AppCompat
  }
}