package com.xxyh.mybatis.mapper;

import java.util.List;

import com.xxyh.mybatis.pojo.Orders;
import com.xxyh.mybatis.pojo.OrdersCustom;

public interface OrdersMapperCustom {
	
	// 查询订单关联查询用户信息
	public List<OrdersCustom> findOrdersUser() throws Exception;
	
	// 查询订单关联查询用户，使用resultMap
	public List<Orders> findOrdersUserResultMap() throws Exception;
	
	// 查询订单(关联用户)和订单明细
	public List<Orders> findOrdersAndOrderDetailResultMap() throws Exception;

	// 查询用户及购买的商品信息，使用resultmap
	public List<Orders> findUserAndItemsResultMap() throws Exception;
	
	// 查询订单关联查询用户，用户信息是延迟加载
	public List<Orders> findUsersUserLazyLoading() throws Exception;
}
