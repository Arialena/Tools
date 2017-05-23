package com.example.administrator.mytools.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;

/**
 * Created by ashwin on 11/11/16.
 */

public class SharedPreferencesManager
{
    private Context mContext;
    private static SharedPreferences mSharedPreferences;
    private static final String PREFERENCES = "my_preferences";


    public SharedPreferencesManager(Context context)
    {
        mSharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        mContext = context;
    }

    /**
     * employee
     */
    private static final String EMPLOYEE = "Employee";

    private Employee mEmployee;

    public Employee getEmployee()
    {
        if(mEmployee == null)
        {
            String tailor_info_string = mSharedPreferences.getString(EMPLOYEE, "");

            if (tailor_info_string.equals(""))
            {
                mEmployee = null;
            }
            else
            {
                mEmployee = JSON.parseObject(tailor_info_string, Employee.class);
            }
        }

        return mEmployee;
    }

    public void setEmployee( Employee employee )
    {
        if (employee == null)
        {
            mSharedPreferences.edit().putString(EMPLOYEE, "").commit();
        }
        else
        {
            mSharedPreferences.edit().putString(EMPLOYEE, JSON.toJSONString(employee)).commit();
        }

        mEmployee = employee;
    }
}
