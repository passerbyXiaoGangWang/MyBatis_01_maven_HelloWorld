package com.wxg.mybatis.mapper;

import com.wxg.mybatis.bean.Employee;

import java.util.List;

public interface EmployeeMapperPlus {
    //通过id查询员工信息
    public Employee getEmpById(Integer id);

    //查询员工的同时查询该员工的部门信息
    public Employee getEmpAndDept(Integer id);

    //使用association进行分步查询
    public Employee getEmpByIdStep(Integer id);

    //通过部门编号查询员工信息
    public List<Employee> getEmpsByDeptId(Integer did);

}
