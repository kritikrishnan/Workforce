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

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.square.workforce.WorkForceViewModel.SORT_BY_NAME;

public class WorkForceListActivity extends AppCompatActivity {
  private static final String TAG = "WorkForceListActivity";

  @VisibleForTesting
  public WorkForceViewModel viewModel;

  @VisibleForTesting
  public ViewFlipper viewFlipper;

  @VisibleForTesting
  public ProgressBar progressBar;

  @VisibleForTesting
  public RecyclerView employeesListView;

  public EmployeesListAdapter employeesListAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    viewModel = new WorkForceViewModel();
    ActivityWorkforceListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_workforce_list);
    binding.setLifecycleOwner(this);
    initializeViews();
    setUpEmployeesListView();
    observeStateAndPopulateUIState();
    observeMutableEmployeesList();
  }

  private void initializeViews() {
    viewFlipper = findViewById(R.id.viewFlipper);
    progressBar = findViewById(R.id.progress);
    employeesListView = findViewById(R.id.employees_recycler_view);
  }

  private void observeStateAndPopulateUIState() {
    viewModel.getStateLiveData().observe(this, state -> {
      switch (state) {
        case EMPTY:
          viewFlipper.setVisibility(ViewFlipper.VISIBLE);
          progressBar.setVisibility(View.GONE);
          viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.empty_layout_id)));
          break;

        case ERROR:
          viewFlipper.setVisibility(ViewFlipper.VISIBLE);
           progressBar.setVisibility(View.GONE);
          viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.error_layout_id)));
          break;

        case LOADED:
          progressBar.setVisibility(View.GONE);
          viewFlipper.setVisibility(ViewFlipper.GONE);
          employeesListView.setVisibility(View.VISIBLE);
          break;

        case LOADING:
          viewFlipper.setVisibility(ViewFlipper.VISIBLE);
          progressBar.setVisibility(View.VISIBLE);
          employeesListView.setVisibility(View.GONE);
          break;
      }
    });
  }

  private void setUpEmployeesListView() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(RecyclerView.VERTICAL);
    //create adapter instance
    employeesListAdapter = new EmployeesListAdapter(viewModel.getEmployeesLiveData().getValue());
    employeesListView.setAdapter(employeesListAdapter);
    employeesListView.setLayoutManager(layoutManager);
  }

  private void observeMutableEmployeesList() {
    viewModel.getEmployeesLiveData().observe(this, employees -> {
      employees = viewModel.sortEmployeesByParameter(SORT_BY_NAME, employees);
      employeesListAdapter.setEmployees(employees);
      employeesListAdapter.notifyDataSetChanged();
    });
  }
}