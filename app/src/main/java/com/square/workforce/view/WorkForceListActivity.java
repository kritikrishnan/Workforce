package com.square.workforce.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.os.Bundle;

import com.square.workforce.R;
import com.square.workforce.WorkForceApplication;
import com.square.workforce.WorkForceViewModel;
import com.square.workforce.model.Employee;

import java.util.List;

class WorkForceListActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_workforce_list);
    ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_workforce_list);
    binding.setLifecycleOwner(this);
    WorkForceViewModel viewModel = new WorkForceViewModel();
    LiveData<List<Employee>> employees = viewModel.getEmployees();
  }
}