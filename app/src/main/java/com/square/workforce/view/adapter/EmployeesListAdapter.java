package com.square.workforce.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.square.workforce.R;
import com.square.workforce.databinding.EmployeeCardBinding;
import com.square.workforce.model.Employee;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import static com.square.workforce.BR.model;

public class EmployeesListAdapter extends RecyclerView.Adapter<EmployeesListAdapter.ViewHolder> implements BindableAdapter {

  private List<Employee> employees;

  @Override
  public void setData(List<Employee> employees) {
    this.employees = employees;
  }

  @NonNull
  @Override
  public EmployeesListAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
    EmployeeCardBinding binding = DataBindingUtil.inflate(
      LayoutInflater.from(parent.getContext()),
      R.layout.employee_card, parent, false);

    return new ViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, final int position) {
    Employee employee = employees.get(position);
    holder.bind(employee);
  }


  @Override
  public int getItemCount() {
    return employees.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public EmployeeCardBinding dataBinding;

    public ViewHolder(final EmployeeCardBinding dataBinding) {
      super(dataBinding.getRoot());
      this.dataBinding = dataBinding;
    }

    public void bind(final Employee employee) {
      dataBinding.setVariable(model, employee);
      dataBinding.executePendingBindings();
    }
  }
}