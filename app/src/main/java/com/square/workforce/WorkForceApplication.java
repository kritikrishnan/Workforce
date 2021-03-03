package com.square.workforce;

import android.app.Application;

//Singleton class. Only one instance for the entire application.
public class WorkForceApplication extends Application {
  public static WorkForceApplication application;

  @Override
  public void onCreate() {
    super.onCreate();
    application = this;
  }
}
