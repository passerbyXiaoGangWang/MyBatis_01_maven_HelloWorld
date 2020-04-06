package com.wxg.mybatis.test;

import com.wxg.mybatis.bean.Employee;
import com.wxg.mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * 测试Mybatis单表增删改查
 * 1、mybatis允许增删改直接定义以下类型返回值
 *  		Integer、Long、Boolean、void
 * 	 2、我们需要手动提交数据
 * 	  		sqlSessionFactory.openSession();===》手动提交
 * 	  		sqlSessionFactory.openSession(true);===》自动提交
 */
public class MybatisTestCURD {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Test
    public  void test01() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
           Employee employee =  sqlSession.selectOne("com.wxg.mybatis.mapper.EmployeeMapper.selectEmp",1);
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 根据id查询员工
     * @throws IOException
     */
    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }

    }

    /**
     * 测试结果封装为Map对象
     * @throws IOException
     */
    @Test
    public void test03() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            HashMap<String, Object> hashMap = mapper.selectEmp(1);
            System.out.println(hashMap);
            System.out.println(hashMap.get("email"));
        }finally {
            sqlSession.close();
        }

    }

    /**
     * 测试新增员工
     * @throws IOException
     */
    @Test
    public void test04() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
        //1、获取到的SqlSession不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee();
            employee.setLastName("Jason");
            employee.setGender("1");
            employee.setEmail("jason@163.com");
            mapper.addEmployee(employee);
            System.out.println(employee);
            sqlSession.commit(); //手动提交
        }finally {
            sqlSession.close();
        }

    }

    /**
     * 测试修改员工，根据主键id
     * @throws IOException
     */
    @Test
    public void test05() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();
        //1、获取到的SqlSession不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee();
            employee.setId(5);
            employee.setLastName("Jason_update");
            employee.setGender("0");
            employee.setEmail("jason_update@163.com");
            mapper.updateEmp(employee);
            System.out.println(employee);
            sqlSession.commit();//手动提交
        }finally {
            sqlSession.close();
        }

    }

    /**
     * 根据主键id删除员工
     * @throws IOException
     */
    @Test
    public void test06() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();

        //1、获取到的SqlSession不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            mapper.deleteEmpById(4);
            sqlSession.commit();//手动提交
        }finally {
            sqlSession.close();
        }

    }


}
