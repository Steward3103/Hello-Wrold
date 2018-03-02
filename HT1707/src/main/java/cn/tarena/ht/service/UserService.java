package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;

public interface UserService {
	public List<User> findAll();
	
	public void updateState(int state, String[] userIds);
	
	public void deleteUsers(String[] userIds);

	public List<UserInfo> findUserList();

	public void saveUser(User user);

	public User findUserById(String userId);

	public void updateUser(User user);

	public void saveUserRole(String userId, String[] roleIds);

	public List<String> finduRoleList(String userId);
	
	public User findUserByU_P(String username, String password);
	
	//shiro通过用户名获取用户信息
	public User findUserByUserName(String username);
	//通过userId查询用户全部模块信息
	public List<String> findPrililegeInfoList(String userId);
}
