package com.xxyh.mybatis.mapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.xxyh.mybatis.pojo.User;
import com.xxyh.mybatis.pojo.UserCustom;
import com.xxyh.mybatis.pojo.UserQueryVO;

public class UserMapperTest {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		// 创建SqlSessionFactory
		String resource = "SqlMapConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
	}

	@Test
	public void testFindUserById() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 创建UserMapper对象,mybatis自动创建mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		// 调用UserMapper的方法
		User user = userMapper.findUserById(1);

		sqlSession.close();
		System.out.println(user);
	}

	@Test
	public void testFindUserByName() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		List<User> users = userMapper.findUserByName("小明");
		session.close();
		System.out.println(users);
	}

	@Test
	public void testFindUserByHashMap() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1);
		map.put("username", "王五");
		User user = userMapper.findUserByHashMap(map);
		System.out.println(user);
	}

	// 用户信息综合查询
	@Test
	public void testFindUserList() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);

		// 创建包装对象，设置查询条件
		UserQueryVO userQueryVO = new UserQueryVO();
		UserCustom userCustom = new UserCustom();
		// userCustom.setSex("1");
		userCustom.setUsername("三丰");

		List<Integer> ids = new ArrayList<>();
		ids.add(1);
		ids.add(10);
		ids.add(24);
		userQueryVO.setIds(ids);

		userQueryVO.setUserCustom(userCustom);
		List<UserCustom> userList = userMapper.findUserList(userQueryVO);
		System.out.println(userList);
	}

	@Test
	public void testFindUserCount() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);

		UserQueryVO userQueryVO = new UserQueryVO();
		UserCustom userCustom = new UserCustom();
		userCustom.setSex("1");
		userCustom.setUsername("小明");

		userQueryVO.setUserCustom(userCustom);

		int userCount = userMapper.findUserCount(userQueryVO);
		System.out.println(userCount);
	}

	@Test
	public void testFindUserByIdResultMap() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		User user = userMapper.findUserByIdResultMap(1);
		System.out.println(user);

	}

	// 一级缓存测试
	@Test
	public void testCache1() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		// 下面查询使用一个SqlSession
		// 第一次发起请求，查询id为1的用户
		User user1 = userMapper.findUserById(1);
		System.out.println(user1);

		// 更新user1的信息，清空缓存
		user1.setUsername("王五一");
		userMapper.updateUser(user1);
		// 执行commit清空缓存
		sqlSession.commit();

		// 第二次发起请求，查询id为1的用户
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);

		sqlSession.close();
	}

	// 二级缓存测试
	@Test
	public void testCache2() throws Exception {
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		SqlSession sqlSession3 = sqlSessionFactory.openSession();

		// 创建代理对象
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		User user1 = userMapper1.findUserById(1);
		System.out.println(user1);
		// 将sqlSession中的数据写到二级缓存区域
		sqlSession1.close();
		
		// 使用SqlSession3执行commit操作，清空二级缓存
//		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
//		User user = userMapper3.findUserById(1);
//		user.setAddress("上海");
//		userMapper3.updateUser(user);
//		sqlSession3.commit();
//		sqlSession3.close();
		
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		User user2 = userMapper2.findUserById(1);
		System.out.println(user2);
	}
}
