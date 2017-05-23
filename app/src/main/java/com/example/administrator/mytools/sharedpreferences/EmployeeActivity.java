package com.example.administrator.mytools.sharedpreferences;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.mytools.R;

public class EmployeeActivity extends AppCompatActivity {

    private Employee mEmployee;
    private TextView mEmployeeTextView;
    private SharedPreferencesManager mSharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        mSharedPreferencesManager = new SharedPreferencesManager(this);

        initViews();
    }

    private void initViews()
    {
        mEmployeeTextView = (TextView) findViewById(R.id.employeeTextView);

        mEmployee = (Employee) mSharedPreferencesManager.getEmployee();

        if( mEmployee != null )
        {
            mEmployeeTextView.setText(mEmployee.getId() + " : " + mEmployee.getName());
        }
    }
}
