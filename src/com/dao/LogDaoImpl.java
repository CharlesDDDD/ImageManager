package com.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Log;
import com.util.DBconn;
public class LogDaoImpl implements LogDao{
	public List<Log> getLogAll() {
		List<Log> list = new ArrayList<Log>();
    	try {
		    DBconn.init();
			ResultSet rs = DBconn.selectSql("select * from j2ee_log");
			while(rs.next()){
				Log log = new Log();
				log.setType(rs.getString("type"));
				log.setName(rs.getString("name"));
				log.setTime(rs.getString("time"));
				log.setUser(rs.getString("user"));
				log.setManipulation(rs.getString("manipulation"));
				list.add(log);
			}
			DBconn.closeConn();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean add(String user,String manipulation,String time,String type,String name) {
		boolean flag = false;
		DBconn.init();
		int i =DBconn.addUpdDel("insert into j2ee_log(user,manipulation,time,type,name) " +
				"values('"+user+"','"+manipulation+"','"+time+"','"+type+"','"+name+"')");
		if(i>0){
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}
}
