package com.wxg.mybatis.mapper;

import com.wxg.mybatis.bean.Employee;

public interface EmployeeMapperPlus {
    //通过id查询员工信息
    public Employee getEmpById(Integer id);

    //查询员工的同时查询该员工的部门信息
    public Employee getEmpAndDept(Integer id);

}
