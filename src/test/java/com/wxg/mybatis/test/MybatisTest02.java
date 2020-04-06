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
import java.util.Map;


/**
 * 测试Mybatis多个参数查询的情况
 *
 * 多个参数：mybatis会做特殊处理。
 * 多个参数会被封装成 一个map，
 * 		key：param1...paramN,或者参数的索引也可以
 * 		value：传入的参数值
 * 	#{}就是从map中获取指定的key的值；
 */
public class MybatisTest02 {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }



    /**
     * 测试MyBatis多个参数查询的情况
     *  * 多个参数：mybatis会做特殊处理。
     *  * 多个参数会被封装成 一个map，
     *  * 		key：param1...paramN,或者参数的索引也可以
     *  * 		value：传入的参数值
     *  * 	#{}就是从map中获取指定的key的值；
     *
     * Cause: org.apache.ibatis.binding.BindingException:
     * Parameter 'id' not found. Available parameters are [0, 1, param1, param2]
     */
    @Test
    public void test01() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpByIdAndLastName(1, "tom");
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }

    }

    /**
     * 查询时多个参数使用Map封装
     * @throws IOException
     */
    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<String,Object> map = new HashMap<>();
            map.put("id",1);
            map.put("lastName","Tom");
            Employee employee = mapper.getEmpByMap(map);
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }

    }



}
