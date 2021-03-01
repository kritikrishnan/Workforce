package com.square.workforce;

import android.app.Application;

class WorkForceApplication extends Application {
  static WorkForceApplication application;

  @Override
  public void onCreate() {
    super.onCreate();
    application = this;
  }
}
