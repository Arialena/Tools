package com.example.administrator.mytools.sharedpreferences;

/**
 * Created by ashwin on 11/11/16.
 */

public class Employee
{
    private String id, name;

    public Employee()
    { }

    public Employee(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return this.id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
