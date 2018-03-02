package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tarena.ht.pojo.Dept;

public interface DeptMapper {
	
	//查询全部部门列表信息
	public List<Dept> findAll();
	
	//通过@Param注解将数据封装为一个map   Map = {deptIds:[1,2,3,4],state=0}
	public void updateState(@Param("deptIds")String[] deptIds,@Param("state") int state);
	
	//批量删除
	public void deleteDepts(String[] deptIds);
	
	//表示部门的新增
	public void saveDept(Dept dept);
	
	//根据deptId查询部门信息
	public Dept findDeptById(String deptId);
	
	//查询排除自己之外的数据
	public List<Dept> findParentDept(String deptId);
	
	//修改部门信息
	public void updateDept(Dept dept);
	
}
