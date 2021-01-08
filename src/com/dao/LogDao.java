package com.dao;

import java.util.List;

import com.entity.Log;

public interface LogDao {
	// public boolean login(String name,String pwd);//登录
	public List<Log> getLogAll();//返回用户信息集合]
	public boolean add(String user,String manipulation,String time,String type,String name);
}
