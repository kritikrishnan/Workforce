package com.square.workforce;

import android.app.Application;

import com.squareup.picasso.Picasso;

//Singleton class. Only one instance for the entire application.
public class WorkForceApplication extends Application {
  public static WorkForceApplication application;

  @Override
  public void onCreate() {
    super.onCreate();
    application = this;
    Picasso.Builder builder = new Picasso.Builder(this);
    Picasso built = builder.build();
    built.setIndicatorsEnabled(true);
    built.setLoggingEnabled(true);
  }
}
