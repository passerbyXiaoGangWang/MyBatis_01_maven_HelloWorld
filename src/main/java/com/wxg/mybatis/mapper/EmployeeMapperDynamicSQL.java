package com.wxg.mybatis.mapper;

import com.wxg.mybatis.bean.Employee;

import java.util.List;

/**
 * 测试Mybatis动态SQL
 */
public interface EmployeeMapperDynamicSQL {

    //查询员工，要求，携带了哪个字段 查询条件就带上这个字段的值
    public List<Employee> getEmpsByConditionIf(Employee employee);
    //测试Trim
    public List<Employee> getEmpsByConditionTrim(Employee employee);
}
