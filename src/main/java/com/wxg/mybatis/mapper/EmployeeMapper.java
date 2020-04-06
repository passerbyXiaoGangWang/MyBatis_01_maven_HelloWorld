package com.wxg.mybatis.mapper;

import com.wxg.mybatis.bean.Employee;

import java.util.HashMap;

public interface EmployeeMapper {



    public Employee getEmpById(Integer id);

    /**
     * 结果封装为Map对象
     * @param id
     * @return
     */
    public HashMap<String,Object> selectEmp(Integer id);

    /**
     * 添加员工
     * @param employee
     */
    public void addEmployee(Employee employee);

    /**
     * 修改员工
     * @param employee
     */
    public void updateEmp(Employee employee);

    /**
     * 删除员工
     * @param id
     */
    public void deleteEmpById(Integer id);
}
