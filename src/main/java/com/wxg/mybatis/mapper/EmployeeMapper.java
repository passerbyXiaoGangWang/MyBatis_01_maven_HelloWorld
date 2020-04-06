package com.wxg.mybatis.mapper;

import com.wxg.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    //多条记录封装一个map:Map<Integer,Employee>:键是这条记录的主键，值是记录封装后的javaBean对象
    //告诉mybatis封装这个map的时候使用哪个属性作为map的key
    @MapKey("id")
//    @MapKey("lastName")
    public Map<Integer,Employee> getEmpByLastNameLikeReturnMap(String lastName);

    //返回一条记录的map；key就是列名，值就是对应的值
    public Map<String,Object> getEmpByIdReturnMap(Integer id);

    /**
     * 通过姓名模糊查询，返回结果为List
     * @param lastName
     * @return
     */
    public List<Employee> getEmpsByLastNameLike(String lastName);

    /**
     * 查询时多个参数使用Map封装
     * @param map
     * @return
     */
    public Employee getEmpByMap(Map<String,Object> map);

    /**
     * 测试Mybatis多个参数查询
     *
     * 【命名参数】：明确指定封装参数时map的key；@Param("id")
     * 	多个参数会被封装成 一个map，
     * 		key：使用@Param注解指定的值
     * 		value：参数值
     * 	#{指定的key}取出对应的参数值
     *
     * @param id
     * @param lastName
     * @return
     */
    public Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);


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
    public Long addEmployee(Employee employee);

    /**
     * 修改员工
     * @param employee
     */
    public boolean updateEmp(Employee employee);

    /**
     * 删除员工
     * @param id
     */
    public void deleteEmpById(Integer id);
}
