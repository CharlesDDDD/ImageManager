package com.dao;
 
import java.util.List;

import com.entity.User;
 
public interface UserDao {
	public boolean login(String name,String pwd);//��¼ 
	public boolean register(User user);//ע��
	public List<User> getUserAll();//�����û���Ϣ����
	public boolean delete(String name) ;//����idɾ���û�
	public boolean update(String name, String pwd) ;//�����û���Ϣ
}