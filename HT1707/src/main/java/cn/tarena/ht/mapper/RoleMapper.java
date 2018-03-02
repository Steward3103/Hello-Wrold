package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.tarena.ht.pojo.Role;

public interface RoleMapper {
	/*
	 * 说明：如果是单表操作，可以使用注解形式。进行操作，
	 * 如果是多表关联查询，则使用注解不能完成任务。
	 * 即使单表操作，其中也不能包含其他标签
	 * 通过注解形式查询的结果集，应该和返回值保证一致。
	 * @Select
	@Insert
	@Delete
	@Update*/
	@Select(value="select * from role_p")
	public List<Role> findAll();
	
	public void saveRole(Role role);
	
	public void saveRoleModule(@Param("moduleId")String moduleId,@Param("roleId")String roleId);
	
	//根据roleId 删除中间表数据
	public void deleteRoleModule(String roleId);
	
	@Select("select module_id from role_module_p where role_id = #{roleId}")
	public List<String> findRoleModuleList(String roleId);
}
