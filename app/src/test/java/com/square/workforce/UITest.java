package com.square.workforce;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import com.square.workforce.model.Employees;
import com.square.workforce.view.WorkForceListActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.LooperMode;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MediatorLiveData;

import static android.os.Looper.getMainLooper;
import static org.robolectric.Shadows.shadowOf;
import static org.robolectric.annotation.LooperMode.Mode.PAUSED;

@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P, application = TestWorkForceApplication.class) // Value of Build.VERSION_CODES.P is 28
@LooperMode(PAUSED)
public class UITest {

  WorkForceListActivity mockWorkForceListActivity;

  @Before
  public void Init() {
    mockWorkForceListActivity = Robolectric.setupActivity(WorkForceListActivity.class);
    Shadows.shadowOf(Looper.getMainLooper()).idle();
  }

  @Test
  public void testViewsAndViewmodelNotNull() {
    Shadows.shadowOf(Looper.getMainLooper()).idle();
    Assert.assertNotNull(mockWorkForceListActivity.viewModel);
    Assert.assertNotNull(mockWorkForceListActivity.viewFlipper);
    Assert.assertNotNull(mockWorkForceListActivity.progressBar);
    Assert.assertNotNull(mockWorkForceListActivity.employeesListView);
    Assert.assertNotNull(mockWorkForceListActivity.employeesListAdapter);
  }

  @Test
  public void testSortEmployeesByParameter() {
    Employees employees = TestUtils.getMalformedData();
    Shadows.shadowOf(Looper.getMainLooper()).idle();
    Assert.assertTrue(mockWorkForceListActivity.viewModel.sortEmployeesByParameter(WorkForceViewModel.SORT_BY_NAME, employees).equals(TestUtils.getSortedByNameMalformedData()));
    employees = TestUtils.getData();
    Assert.assertTrue(mockWorkForceListActivity.viewModel.sortEmployeesByParameter(WorkForceViewModel.SORT_BY_NAME, employees).equals(TestUtils.getSortedByNameData()));
  }

}