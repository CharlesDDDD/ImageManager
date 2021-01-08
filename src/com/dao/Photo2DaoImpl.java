package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Photo2;
import com.util.DBconn;

public class Photo2DaoImpl implements Photo2Dao {
	public boolean add(Photo2 photo)  {
		DBconn.init();
		int i = 0;
		try {
		ResultSet rs = DBconn.selectSql("select * from j2ee_figure_pic");
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
		i = DBconn.addUpdDel("insert into j2ee_figure_pic(src,figname,name,country,resolution,ac_time) values('"+photo.getSrc()+"','"+photo.getFigname()+"','"+photo.getName()+"','"+photo.getCountry()+"','"+photo.getResolution()+"','"+photo.getAc_time()+"')");
		return true;
		}
		return false;
	
		
	}


	public boolean delete(String name) {
		boolean flag = false;
		DBconn.init();
		String sql = "delete from j2ee_figure_pic where name='"+ name +"'";
		int i = DBconn.addUpdDel(sql);
		System.out.print(i);
		flag = (i>0)?true:false;
		DBconn.closeConn();
		System.out.print(flag);
		return flag;
	}

	public boolean update(String name,String figname,String country,String resolution,String actime) {
		boolean flag = false;
		DBconn.init();
		try {
		ResultSet rs = DBconn.selectSql("select name,figname,country,resolution,ac_time from j2ee_figure_pic where name ='"+name+"'");
		while(rs.next()){
			if(rs.getString("name").equals(name) ){
				if(figname=="")
				{
					figname = rs.getString("figname");
				}
				if(country=="")
				{
					country = rs.getString("country");
				}
				if(resolution=="")
				{
					resolution = rs.getString("resolution");
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
		String sql1 ="update j2ee_figure_pic set figname ='"+ figname
				+"' , country ='"+country + "', resolution ='"
				+ resolution + "' , ac_time = '" + actime 
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
	public List <Photo2> search(String sql){
		List<Photo2> photolistList = new ArrayList<Photo2>();
		try {
		    DBconn.init();
			ResultSet rs = DBconn.selectSql(sql);
			while(rs.next()){
				Photo2 photo = new Photo2();
				System.out.println(rs.getString("name"));
				photo.setSrc(rs.getString("src"));
				photo.setName(rs.getString("name"));
				photo.setFigname(rs.getString("figname"));
				photo.setCountry(rs.getString("country"));
				photo.setResolution(rs.getString("resolution"));
				photo.setAc_time(rs.getString("ac_time"));
				photolistList.add(photo);
			}
			DBconn.closeConn();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return photolistList;
	}
	public List <Photo2> getAllPhotos(){
		List<Photo2> photolistList = new ArrayList<Photo2>();
		try {
		    DBconn.init();
		    String sql  = "select * from j2ee_figure_pic";
			ResultSet rs = DBconn.selectSql(sql);
			while(rs.next()){
				Photo2 photo = new Photo2();
				System.out.println(rs.getString("name"));
				photo.setSrc(rs.getString("src"));
				photo.setName(rs.getString("name"));
				photo.setFigname(rs.getString("figname"));
				photo.setCountry(rs.getString("country"));
				photo.setResolution(rs.getString("resolution"));
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