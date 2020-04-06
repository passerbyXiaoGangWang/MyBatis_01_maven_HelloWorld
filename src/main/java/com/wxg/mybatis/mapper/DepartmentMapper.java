package com.wxg.mybatis.mapper;

import com.wxg.mybatis.bean.Department;

public interface DepartmentMapper {

    //通过id查询部门信息
  public Department getDeptById(Integer id);
}
