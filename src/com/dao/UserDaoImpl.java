package com.dao;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.User;
import com.util.DBconn;
 
public class UserDaoImpl implements UserDao{
	
	public boolean register(User user) {
		boolean flag = false;
		DBconn.init();
		int i =DBconn.addUpdDel("insert into userdata(user,password) " +
				"values('"+user.getName()+"','"+user.getPwd()+"')");
		if(i>0){
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}
    public boolean login(String name, String pwd) {
		boolean flag = false;
		try {
			    DBconn.init();
				ResultSet rs = DBconn.selectSql("select * from userdata where user='"+name+"' and password='"+pwd+"'");
				while(rs.next()){
					if(rs.getString("user").equals(name) && rs.getString("password").equals(pwd)){
						flag = true;
					}
				}
				DBconn.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public List<User> getUserAll() {
		List<User> list = new ArrayList<User>();
    	try {
		    DBconn.init();
			ResultSet rs = DBconn.selectSql("select * from userdata");
			while(rs.next()){
				User user = new User();
				user.setName(rs.getString("user"));
				user.setPwd(rs.getString("password"));
				list.add(user);
			}
			DBconn.closeConn();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean update(String name, String pwd) {
		boolean flag = false;
		DBconn.init();
		String sql ="update user set user ='"+name
				+"' , password ='"+pwd
				+"' where user = "+name;
		int i =DBconn.addUpdDel(sql);
		if(i>0){
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}
	public boolean delete(String name) {
		boolean flag = false;
		DBconn.init();
		String sql = "delete from userdata where user='"+name+"'";
		int i =DBconn.addUpdDel(sql);
		if(i>0){
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}
    
}