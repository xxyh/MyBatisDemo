package com.xxyh.mybatis.mapper;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.xxyh.mybatis.pojo.Orders;
import com.xxyh.mybatis.pojo.OrdersCustom;
import com.xxyh.mybatis.pojo.User;

public class OrdersMapperCustomTest {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		String resource = "SqlMapConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
	}

	@Test
	public void testFindOrdersUser() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession
				.getMapper(OrdersMapperCustom.class);
		List<OrdersCustom> list = ordersMapperCustom.findOrdersUser();
		System.out.println(list);
		sqlSession.close();
	}

	@Test
	public void testFindOrdersUserResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom ordersMapperCustom = sqlSession
				.getMapper(OrdersMapperCustom.class);
		List<Orders> list = ordersMapperCustom.findOrdersUserResultMap();
		System.out.println(list);
		sqlSession.close();
	}

	@Test
	public void testFindOrdersAndOrderDetailResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom ordersMapperCustom = sqlSession
				.getMapper(OrdersMapperCustom.class);
		List<Orders> list = ordersMapperCustom
				.findOrdersAndOrderDetailResultMap();
		System.out.println(list);
		sqlSession.close();
	}

	@Test
	public void testFindUserAndItemsResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom ordersMapperCustom = sqlSession
				.getMapper(OrdersMapperCustom.class);
		List<Orders> list = ordersMapperCustom.findUserAndItemsResultMap();
		System.out.println(list);
		sqlSession.close();
	}

	// 查询订单关联查询用户，用户信息使用延迟加载
	@Test
	public void testFindUsersUserLazyLoading() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom ordersMapperCustom = sqlSession
				.getMapper(OrdersMapperCustom.class);
		// 查询订单信息（单表）
		List<Orders> list = ordersMapperCustom.findUsersUserLazyLoading();
		// 遍历上边的订单列表
		for (Orders orders : list) {
			User user = orders.getUser();
			System.out.println(user);
		}
	}
}
