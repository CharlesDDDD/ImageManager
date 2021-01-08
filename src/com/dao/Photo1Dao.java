package com.dao;

import java.util.List;

import com.entity.Photo1;


public interface Photo1Dao {
	public boolean add(Photo1 photo);
	public boolean delete(String name);
	public boolean update(String name,String country,String location,String lalg,String actime);
	public List <Photo1> search(String sql);
	public List <Photo1> getAllPhotos();
}
