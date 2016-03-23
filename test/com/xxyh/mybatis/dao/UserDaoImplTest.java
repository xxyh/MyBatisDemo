package com.xxyh.mybatis.dao;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.xxyh.mybatis.pojo.User;

public class UserDaoImplTest {

	private SqlSessionFactory factory;

	// 在测试方法之前执行
	@Before
	public void setUp() throws Exception {
		String resource = "SqlMapConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		factory = new SqlSessionFactoryBuilder().build(is);

	}

	@Test
	public void testFindUserById() throws Exception {
		// 创建UserDao的对象
		UserDao userDao = new UserDaoImpl(factory);

		User user = userDao.findUserById(1);

		System.out.println(user);
	}

}
