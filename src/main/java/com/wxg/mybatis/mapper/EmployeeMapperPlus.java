package com.wxg.mybatis.mapper;

import com.wxg.mybatis.bean.Employee;

public interface EmployeeMapperPlus {
    //通过id查询员工信息
    public Employee getEmpById(Integer id);
}
