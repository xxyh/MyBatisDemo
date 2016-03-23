package com.xxyh.mybatis.day1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;
import com.xxyh.mybatis.pojo.User;

public class MyBatisTest1 {
	// 根据用户id查询用户信息，得到一条记录
	@Test
	public void findUserById() throws IOException {

		// MyBatis配置文件
		String resource = "SqlMapConfig.xml";

		// 获得配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂，传入MyBatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		// 通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		// 通过SqlSession操作数据库
		/**
		 * 第一个参数：映射文件的statement的id，=namespace+"."+statement id
		 * 第二个参数：指定和映射文件中所匹配的parameterType类型的参数
		 */
		User user = sqlSession.selectOne("test.findUserById",1);
		
		System.out.println(user);
		
		sqlSession.close();
	}
	
	// 根据用户名称模糊查询用户列表
	@Test
	public void findUserByName() throws IOException {
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = factory.openSession();
		List<User> userList = session.selectList("test.findUserByName", "小明");
		System.out.println(userList);
		session.close();
	}
	
	// 添加用户
	@Test
	public void insertUser() throws IOException {
		String resource = "SqlMapConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		User user = new User();
		user.setUsername("xxyh");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("上海");
		session.insert("test.insertUser", user);
		
		// 提交事务
		session.commit();
		
		// 获取用户信息主键
		System.out.println("------"+user.getId()+"------");
		
		// 关闭会话
		session.close();
	}
	
	// 根据id删除用户信息
	@Test
	public void deleteUser() throws IOException {
		String resource = "SqlMapConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		session.delete("test.deleteUser", 30);	// 将后面添加的xxyh用户删除
		session.commit();
		session.close();
	}
	
	// 更新用户信息
	@Test
	public void updateUser() throws IOException {
		String resource = "SqlMapConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		
		User user = new User();
		user.setId(28);
		user.setUsername("xxyh111");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("上海浦东");
		
		session.update("test.updateUser", user);
		
		session.commit();
		session.close();
	}
}
