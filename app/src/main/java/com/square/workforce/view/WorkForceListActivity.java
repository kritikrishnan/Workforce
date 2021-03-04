package com.square.workforce.view;

import android.os.Bundle;
import android.util.Log;

import com.square.workforce.R;
import com.square.workforce.WorkForceViewModel;
import com.square.workforce.databinding.ActivityWorkforceListBinding;
import com.square.workforce.view.adapter.EmployeesListAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.square.workforce.WorkForceViewModel.SORT_BY_NAME;

public class WorkForceListActivity extends AppCompatActivity {
  private static final String TAG = "WorkForceListActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_workforce_list);
    final WorkForceViewModel viewModel = new WorkForceViewModel();
    ActivityWorkforceListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_workforce_list);
    binding.setLifecycleOwner(this);
    RecyclerView employeesListView = findViewById(R.id.employeesRecyclerView);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(RecyclerView.VERTICAL);
    //create adapter instance
    final EmployeesListAdapter employeesListAdapter = new EmployeesListAdapter(viewModel.getEmployeesLiveData().getValue());
    employeesListView.setAdapter(employeesListAdapter);
    employeesListView.setLayoutManager(layoutManager);
    viewModel.getEmployeesLiveData().observe(this, employees -> {
      Log.d(TAG, "kya hua? " + employees);
      employees = viewModel.sortEmployeesByParameter(SORT_BY_NAME, employees);
      employeesListAdapter.setEmployees(employees);
      employeesListAdapter.notifyDataSetChanged();
    });
  }
}