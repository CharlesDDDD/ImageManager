package com.dao;

import java.util.List;

import com.entity.Log;

public interface LogDao {
	// public boolean login(String name,String pwd);//��¼
	public List<Log> getLogAll();//�����û���Ϣ����]
	public boolean add(String user,String manipulation,String time,String type,String name);
}
