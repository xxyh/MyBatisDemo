package com.xxyh.mybatis.dao;

import java.util.List;

import com.xxyh.mybatis.pojo.User;

public interface UserDao {
	
	// 根据id查询用户信息
	public User findUserById(int id) throws Exception;
	
	// 根据用户名称查询用户列表
	public List<User> findUserByName(String username) throws Exception;
	
	// 添加用户信息
	public void insertUser(User user) throws Exception;
	
	// 删除用户信息
	public void deleteUser(int id) throws Exception;
}
