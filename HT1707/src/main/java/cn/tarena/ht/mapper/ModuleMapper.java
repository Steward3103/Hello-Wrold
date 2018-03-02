package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tarena.ht.pojo.Module;

public interface ModuleMapper {
	public List<Module> findAll();

	public void deleteModules(String[] moduleIds);

	public void updateState(@Param("moduleIds")String[] moduleIds,@Param("state")int state);

	public void saveModule(Module module);

	public Module findModuleById(String moduleId);

	public void updateModule(Module module);
}
