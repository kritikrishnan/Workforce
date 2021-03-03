package com.square.workforce.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.square.workforce.R;
import com.square.workforce.WorkForceViewModel;
import com.square.workforce.databinding.ActivityWorkforceListBinding;
import com.square.workforce.view.adapter.EmployeesListAdapter;

class WorkForceListActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_workforce_list);
    ActivityWorkforceListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_workforce_list);
    binding.setLifecycleOwner(this);
    WorkForceViewModel viewModel = new WorkForceViewModel();
    RecyclerView employeesListView = findViewById(R.id.employeesRecyclerView);
    //create adapter instance
    EmployeesListAdapter employeesListAdapter = new EmployeesListAdapter();
    employeesListView.setAdapter(employeesListAdapter);
    //binding adapter
    viewModel.bindEmployeeDataToRecyclerViewAdapter(employeesListView, viewModel.employees);
  }
}