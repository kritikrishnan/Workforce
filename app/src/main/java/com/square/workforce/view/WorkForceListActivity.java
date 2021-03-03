package com.square.workforce.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import com.square.workforce.R;

class WorkForceListActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_workforce_list);
    ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_workforce_list);
    binding.setLifecycleOwner(this);
  }
}