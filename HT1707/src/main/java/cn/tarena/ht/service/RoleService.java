package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Role;

public interface RoleService {
	public List<Role> findAll();

	public void saveRole(Role role);

	public void saveRoleModule(String roleId, String[] moduleIds);
	
	//根据角色信息，查询所有的模块的Id值
	public List<String> findRoleModuleList(String roleId);
}
