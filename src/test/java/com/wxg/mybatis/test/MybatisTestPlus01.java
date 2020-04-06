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

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
