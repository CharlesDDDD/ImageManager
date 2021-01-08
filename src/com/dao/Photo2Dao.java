package com.dao;

import java.util.List;

import com.entity.Photo2;


public interface Photo2Dao {
	public boolean add(Photo2 photo);
	public boolean delete(String name);
	public boolean update(String name,String figname,String country,String resolution,String actime);
	public List <Photo2> search(String sql);
	public List <Photo2> getAllPhotos();
}
