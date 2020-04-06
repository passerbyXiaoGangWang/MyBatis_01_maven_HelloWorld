package com.wxg.mybatis.test;

import com.wxg.mybatis.bean.Department;
import com.wxg.mybatis.bean.Employee;
import com.wxg.mybatis.mapper.DepartmentMapper;
import com.wxg.mybatis.mapper.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


/**
 * 集合类型的属性如何封装,查询部门的同时查询该部门所对应的所有员工信息
 *
 * 嵌套结果集的方式，使用collection标签定义关联的集合类型的属性封装规则
 *
 * 使用collection对 一对多 结果集的封装(一个部门有多个员工)
 *
 */
public class MybatisTestPlus02 {

    @Test
    public void test01() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department dept = mapper.getDeptByIdPlus(2);
            System.out.println(dept);
            System.out.println(dept.getEmps());
        }finally {
            sqlSession.close();
        }

    }

    //使用分段查询的方式实现
    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department dept = mapper.getDeptByIdStep(2);
            System.out.println(dept.getDepartmentName());
            System.out.println(dept.getEmps());
        }finally {
            sqlSession.close();
        }

    }

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
