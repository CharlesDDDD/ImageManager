package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Photo1;
import com.util.DBconn;

public class Photo1DaoImpl implements Photo1Dao {
	public boolean add(Photo1 photo)  {
		DBconn.init();
		int i = 0;
		try {
		ResultSet rs = DBconn.selectSql("select * from j2ee_landscape_pic");
		while(rs.next()){
			if(rs.getString("name").equals(photo.getName()) ){
				i = 1;
			   }
		    }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		if(i==0)
		{
		i = DBconn.addUpdDel("insert into j2ee_landscape_pic(src,country,name,location,latitude_longtitude,ac_time) values('"+photo.getSrc()+"','"+photo.getCountry()+"','"+photo.getName()+"','"+photo.getLocation()+"','"+photo.getLatitude_longitude()+"','"+photo.getAc_time()+"')");
		return true;
		}
		return false;
	
		
	}


	public boolean delete(String name) {
		boolean flag = false;
		DBconn.init();
		String sql = "delete from j2ee_landscape_pic where name='"+ name +"'";
		int i = DBconn.addUpdDel(sql);
		System.out.print(i);
		flag = (i>0)?true:false;
		DBconn.closeConn();
		System.out.print(flag);
		return flag;
	}

	public boolean update(String name,String country,String location,String lalg,String actime) {
		boolean flag = false;
		DBconn.init();
		try {
		ResultSet rs = DBconn.selectSql("select name,country,location,latitude_longtitude,ac_time from j2ee_landscape_pic where name ='"+name+"'");
		while(rs.next()){
			if(rs.getString("name").equals(name) ){
				if(country=="")
				{
					country = rs.getString("country");
				}
				if(location=="")
				{
					location = rs.getString("location");
				}
				if(lalg=="")
				{
					lalg = rs.getString("latitude_longtitude");
				}
				if(actime=="")
				{
					actime = rs.getString("ac_time");
				}
			   }
		    }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//对图库的值进行更新
		String sql1 ="update j2ee_landscape_pic set country ='"+ country
				+"' , location ='"+location + "', latitude_longtitude ='"
				+ lalg + "' , ac_time = '" + actime 
				+"' where name = '"+name + "'";
		System.out.print(sql1);
		int j = DBconn.addUpdDel(sql1);
		if(j>0)
			flag = true;
		else {
			flag = false;
		}
		return flag;
	}
	public List <Photo1> search(String sql){
		List<Photo1> photolistList = new ArrayList<Photo1>();
		try {
		    DBconn.init();
			ResultSet rs = DBconn.selectSql(sql);
			while(rs.next()){
				Photo1 photo = new Photo1();
				System.out.println(rs.getString("name"));
				photo.setSrc(rs.getString("src"));
				photo.setName(rs.getString("name"));
				photo.setCountry(rs.getString("country"));
				photo.setLocation(rs.getString("location"));
				photo.setLatitude_longitude(rs.getString("latitude_longtitude"));
				photo.setAc_time(rs.getString("ac_time"));
				photolistList.add(photo);
			}
			DBconn.closeConn();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return photolistList;
	}
	public List <Photo1> getAllPhotos(){
		List<Photo1> photolistList = new ArrayList<Photo1>();
		try {
		    DBconn.init();
		    String sql  = "select * from j2ee_landscape_pic";
			ResultSet rs = DBconn.selectSql(sql);
			while(rs.next()){
				Photo1 photo = new Photo1();
				System.out.println(rs.getString("name"));
				photo.setSrc(rs.getString("src"));
				photo.setName(rs.getString("name"));
				photo.setCountry(rs.getString("country"));
				photo.setLocation(rs.getString("location"));
				photo.setLatitude_longitude(rs.getString("latitude_longtitude"));
				photo.setAc_time(rs.getString("ac_time"));
				photolistList.add(photo);
			}
			DBconn.closeConn();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return photolistList;
	}
}