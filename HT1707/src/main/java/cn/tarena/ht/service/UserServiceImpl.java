package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.UserInfoMapper;
import cn.tarena.ht.mapper.UserMapper;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;
import cn.tarena.ht.tool.Md5HashPassword;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	public List<User> findAll(){
		
		return userMapper.findAll();
	}

	@Override
	public void updateState(int state, String[] userIds) {
		
		userMapper.updateState(state,userIds);
		
	}

	//关联删除
	@Override
	public void deleteUsers(String[] userIds) {
		
		userInfoMapper.deleteUserInfos(userIds);
		userMapper.deleteUsers(userIds);    //报错了
		
	}
	
	//获取上级领导列表
	@Override
	public List<UserInfo> findUserList() {
		
		return userInfoMapper.findUserInfoList();
	}
	
	
	/**
	 * 新增操作时，需要同时操作2张表   
	 * 主表 user_p   从表user_info_p
	 * 需要同时操作2个对象
	 */
	@Override
	public void saveUser(User user) {
		//表示获取随机Id值 保证UserId不相同
		String userId = UUID.randomUUID().toString();
		
		//将密码进行加密
		user.setPassword
		(Md5HashPassword.getMd5Hash(user.getPassword(), user.getUsername()));
		user.setUserId(userId);
		user.setCreateTime(new Date());
		user.setUpdateTime(user.getCreateTime());
		
		
		UserInfo info = user.getUserInfo();
		info.setUserInfoId(userId);
		info.setCreateTime(user.getCreateTime());
		info.setUpdateTime(user.getUpdateTime());
		
		//需要同时入库  规则：先入库主表，再入库从表
		userMapper.saveUser(user);
		userInfoMapper.saveUserInfo(info);
	}

	@Override
	public User findUserById(String userId) {
		
		return userMapper.findUserById(userId);
	}

	@Override
	public void updateUser(User user) {
		
		//需要同时修改 user_p和user_info_p
		UserInfo info = user.getUserInfo();
		info.setUpdateTime(new Date());  //添加时间
		info.setUserInfoId(user.getUserId()); //赋值主键Id
		
		user.setUpdateTime(info.getUpdateTime());
		
		userMapper.updateUser(user);
		userInfoMapper.updateUser(info);
		
	}

	@Override
	public void saveUserRole(String userId, String[] roleIds) {
		
		//为了防止重复提交先删除
		userMapper.deleteRoleUsers(userId);
		
		//用户和角色的新增 
		for (String roleId : roleIds) {
			userMapper.saveUserRole(userId,roleId);
		}
		
	}

	@Override
	public List<String> finduRoleList(String userId) {
		
		return userMapper.finduRoleList(userId);
	}

	@Override
	public User findUserByU_P(String username, String password) {
		
		return userMapper.findUserByU_P(username,password);
	}

	@Override
	public User findUserByUserName(String username) {
		
		return userMapper.findUserByUserName(username);
	}

	@Override
	public List<String> findPrililegeInfoList(String userId) {
		
		return userMapper.findPrililegeInfoList(userId);
	}
	
	
	
	
	
}
