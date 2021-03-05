package com.square.workforce.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

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
    final WorkForceViewModel viewModel = new WorkForceViewModel();
    ActivityWorkforceListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_workforce_list);
    binding.setLifecycleOwner(this);

    ViewFlipper viewFlipper = findViewById(R.id.viewFlipper);
    ProgressBar progressBar = findViewById(R.id.progress);
    viewModel.getStateLiveData().observe(this, state -> {
      switch (state) {
        case EMPTY:
          viewFlipper.setVisibility(ViewFlipper.VISIBLE);
          if (progressBar.isShown()) progressBar.setVisibility(View.GONE);
          viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.empty_layout_id)));
          break;

        case ERROR:
          viewFlipper.setVisibility(ViewFlipper.VISIBLE);
          if (progressBar.isShown()) progressBar.setVisibility(View.GONE);
          viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.error_layout_id)));
          break;

        case LOADED:
          if (progressBar.isShown()) progressBar.setVisibility(View.GONE);
          viewFlipper.setVisibility(ViewFlipper.GONE);
          break;

        case LOADING: viewFlipper.setVisibility(ViewFlipper.VISIBLE); if (!progressBar.isShown()) progressBar.setVisibility(View.VISIBLE); break;
      }
    });

    RecyclerView employeesListView = findViewById(R.id.employees_recycler_view);
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