package com.dao;

import java.util.List;

import com.entity.Photo;


public interface PhotoDao {
	public boolean add(Photo photo);
	public boolean delete(String name);
	public boolean update(String name,String country,String resolution,String lalg,String actime);
	public List <Photo> search(String sql);
	public List <Photo> getAllPhotos();
}
