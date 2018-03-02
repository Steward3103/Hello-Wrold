package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import cn.tarena.ht.pojo.User;

public interface UserMapper {
	public List<User> findAll();
	
	//修改状态
	public void updateState(@Param("state")int state,@Param("userIds")String[] userIds);
	
	//删除用户信息
	public void deleteUsers(String[] userIds);
	
	//新增用户信息
	public void saveUser(User user);
	
	//根据userID查询用户信息
	public User findUserById(String userId);
	
	//修改用户信息
	public void updateUser(User user);
	
	
	//用户和角色的新增  建议使用映射文件的方式 进行插入
	@Insert("insert into role_user_p(role_id,user_id) values(#{roleId},#{userId})")
	public void saveUserRole(@Param("userId")String userId, @Param("roleId")String roleId);
	
	//根据userId删除中间表数据
	public void deleteRoleUsers(String userId);
	
	//根据userId查询用户已经拥有的角色信息
	public List<String> finduRoleList(String userId);
	
	public User findUserByU_P(@Param("username")String username,@Param("password")String password);
	
	//根据用户名查询用户真实信息
	public User findUserByUserName(String username);
	//根据userId查询模块信息
	public List<String> findPrililegeInfoList(String userId);
}
