package com.xxyh.mybatis.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.xxyh.mybatis.pojo.User;

public class UserDaoImpl implements UserDao {

	// 需要向dao实现类中注入SqlSessionFactory
	// 通过构造函数注入
	private SqlSessionFactory factory;

	public UserDaoImpl(SqlSessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public User findUserById(int id) throws Exception {
		SqlSession session = factory.openSession();
		User user = session.selectOne("test.findUserById", id);
		session.close();
		return user;
	}

	@Override
	public List<User> findUserByName(String username) throws Exception {
		SqlSession session = factory.openSession();
		List<User> users = session.selectList("test.findUserByName", username);
		session.close();
		return users;
	}

	@Override
	public void insertUser(User user) throws Exception {
		SqlSession session = factory.openSession();
		session.insert("test.insertUser", user);
		// 提交事务
		session.commit();
		session.close();
	}

	@Override
	public void deleteUser(int id) throws Exception {
		SqlSession session = factory.openSession();
		session.delete("test.deleteUser", id);
		session.commit();
		session.close();
	}

}
