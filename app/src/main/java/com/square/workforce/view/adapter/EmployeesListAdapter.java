package com.square.workforce.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.square.workforce.R;
import com.square.workforce.databinding.EmployeeCardBinding;
import com.square.workforce.model.Employee;
import com.square.workforce.model.Employees;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import lombok.Setter;

import static com.square.workforce.BR.model;

public class EmployeesListAdapter extends RecyclerView.Adapter<EmployeesListAdapter.ViewHolder> {

  private static final String TAG = "EmployeesListAdapter";
  @Setter
  private Employees employees;

  public EmployeesListAdapter(final Employees employees) {
    this.employees = employees != null ? employees : new Employees();
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
    Employee employee = employees.getEmployees().get(position);
    holder.bind(employee);
  }


  @Override
  public int getItemCount() {
    return employees == null ? 0 : employees.getEmployees().size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public EmployeeCardBinding dataBinding;
    ImageView ivPhoto;

    public ViewHolder(final EmployeeCardBinding dataBinding) {
      super(dataBinding.getRoot());
      this.dataBinding = dataBinding;
      this.ivPhoto = this.itemView.findViewById(R.id.employee_imageview);
    }

    public void bind(final Employee employee) {
      dataBinding.setVariable(model, employee);
      dataBinding.executePendingBindings();
      // Load the image into the view using Picasso and bind URL
      Log.d(TAG, "URL = " +employee.getPhotoUrlSmall());
      Picasso.get().load(employee.getPhotoUrlSmall()).placeholder(R.drawable.emp_placeholder).networkPolicy(NetworkPolicy.OFFLINE).error(R.drawable.emp_placeholder).into(ivPhoto, new Callback() {
        @Override
        public void onSuccess() {
          //Photos are displayed on the device
        }

        @Override
        public void onError(Exception e) {
          Log.d(TAG,"Offline image fetch failed");
          Picasso.get()
            .load(employee.getPhotoUrlSmall())
            .error(R.drawable.emp_placeholder)
            .into(ivPhoto, new Callback() {
              @Override
              public void onSuccess() {
                //Photos are displayed on the device
              }

              @Override
              public void onError(Exception e) {
                Log.d("Picasso","Could not fetch image");
              }
            });        }
      });

    }
  }
}