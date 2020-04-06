package com.wxg.mybatis.mapper;

import com.wxg.mybatis.bean.Department;

public interface DepartmentMapper {

    //通过id查询部门信息
  public Department getDeptById(Integer id);

  //查询部门的时候查询部门对应的所有员工信息也查询出来
  public Department getDeptByIdPlus(Integer id);

  //使用分步查询的方式根据Id查询部门信息以及该部门对应的所有员工信息
  public Department getDeptByIdStep(Integer id);
}
