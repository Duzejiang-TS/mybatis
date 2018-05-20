package org.mybatis.first;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

public class MybatisFirst {
    @Test
    public void findUserByIdTest(){
        try {
            //mybatis配置文件
            String resource = "SqlMapConfig.xml";
            //得到配置文件流
            InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
            //创建会话工厂,传入mybatis的配置文件信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //通过工厂得到SqlSession
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //通过SqlSession操作数据库
            //第一个参数：映射文件中statement的id，等于=namesqpce+"."+statement的id
            //第二个参数：指定和映射文件中所匹配的parameterType类型的参数
            User user = sqlSession.selectOne("test.findUserBtId",1);
            System.out.println(user);
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test//根据用户名称模糊查找
    public void findUserByNameTest(){

        try {
            //mybatis配置文件
            String resource = "SqlMapConfig.xml";
            //得到配置文件流
            InputStream inputStream = null;
            inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
            //创建会话工厂,传入mybatis的配置文件信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //通过工厂得到SqlSession
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //list中的user和映射文件中resultType所指定的类型一致
            List<User> list = sqlSession.selectList("test.findUserByName","小明");
            System.out.println(list);
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test//添加用户
    public void insertUserTest() {

        try {
            //mybatis配置文件
            String resource = "SqlMapConfig.xml";
            //得到配置文件流
            InputStream inputStream = null;
            inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
            //创建会话工厂,传入mybatis的配置文件信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //通过工厂得到SqlSession
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //插入用户对象
            User user = new User();
            user.setUsername("yyy");
            user.setBirthday(new Date());
            user.setSex("1");
            user.setAddress("uuu");
            //list中的user和映射文件中resultType所指定的类型一致
            sqlSession.insert("test.insertUser", user);
            //提交事务
            sqlSession.commit();
            //获取用户信息主键
            System.out.println(user.getId());
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test//删除用户
    public void deleteUserTest() {

        try {
            //mybatis配置文件
            String resource = "SqlMapConfig.xml";
            //得到配置文件流
            InputStream inputStream = null;
            inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
            //创建会话工厂,传入mybatis的配置文件信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //通过工厂得到SqlSession
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //list中的user和映射文件中resultType所指定的类型一致
            sqlSession.delete("test.deleteUser", 3);
            //提交事务
            sqlSession.commit();
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test//更新用户信息
    public void updateUserTest() {

        try {
            //mybatis配置文件
            String resource = "SqlMapConfig.xml";
            //得到配置文件流
            InputStream inputStream = null;
            inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource);
            //创建会话工厂,传入mybatis的配置文件信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //通过工厂得到SqlSession
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //更新用户信息
            User user = new User();
            user.setId(4);
            user.setUsername("yyy");
            user.setBirthday(new Date());
            user.setSex("1");
            user.setAddress("uuu");

            sqlSession.update("test.updateUser",user);
            //提交事务
            sqlSession.commit();
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
