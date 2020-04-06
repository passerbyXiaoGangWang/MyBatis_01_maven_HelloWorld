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
     * 分段查询的基础上可以实现延迟加载
     * 分段查询的基础之上加上两个配置：全局配置中设置如下：
     * 	 	<setting name="lazyLoadingEnabled" value="true"/>
     * 	        lazyLoadingEnabled:延迟加载的全局开关。当开启时，所有关联对象都会延迟加载
     *      <setting name="aggressiveLazyLoading" value="true"/> ,在 3.4.1 及之前的版本中默认为 true
     *          aggressiveLazyLoading:开启时，任一方法的调用都会加载该对象的所有延迟加载属性。 否则，每个延迟加载属性会按需加载
     *
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
//            System.out.println(employee.getDept());
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
