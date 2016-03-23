package com.xxyh.mybatis.pojo;

import java.util.List;

/**
 * 用户包装类型
 */
public class UserQueryVO {

	// 传入多个id
	private List<Integer> ids;

	// 包装所需要的查询条件

	// 用户查询条件
	private UserCustom userCustom;

	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

}
