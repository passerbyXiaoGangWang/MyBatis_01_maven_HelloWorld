package com.wxg.mybatis.test;

import com.wxg.mybatis.bean.Employee;
import com.wxg.mybatis.mapper.EmployeeMapper;
import com.wxg.mybatis.mapper.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


/**
 * 测试使用resultMap对结果集的封装
 */
public class MybatisTestPlus01 {

    /**
     * 查询员工信息的同时查询部门信息
     * 测试简单的resultMap封装单表查询结果集
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }

    }

    /**
     * 查询员工信息的同时查询部门信息
     * 测试复杂的resultMap封装多表 连接查询 的结果集
     * @throws IOException
     */
    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.getEmpAndDept(1);
            System.out.println(employee);
            System.out.println(employee.getDept());
        }finally {
            sqlSession.close();
        }

    }

    /**
     * 查询员工信息的同时查询部门信息
     * 使用association进行分步查询
     * @throws IOException
     */
    @Test
    public void test03() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.getEmpByIdStep(1);
            System.out.println(employee.getLastName());
            System.out.println(employee.getDept());
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
