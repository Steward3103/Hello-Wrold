package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.service.ModuleService;

@Controller
@RequestMapping("/sysadmin/module")
public class ModuleController {
	
	@Autowired
	private ModuleService moduleService;
	
	@RequestMapping("/list")
	public String findAll(Model model){
		
		List<Module> moduleList = moduleService.findAll();
		model.addAttribute("moduleList", moduleList);
		
		return "/sysadmin/module/jModuleList";
	}
	
	//模块删除
	@RequestMapping("/delete")
	public String toDelete(@RequestParam("moduleId")String[] moduleIds){
		
		moduleService.deleteModules(moduleIds);
		
		//跳转到模块列表页面
		return "redirect:/sysadmin/module/list";
	}
	
	//状态的启用
	@RequestMapping("/start")
	public String toStart(@RequestParam("moduleId")String[] moduleIds){
		int state = 1;  //启用
		moduleService.updateState(moduleIds,state);
		return "redirect:/sysadmin/module/list";
	}
	
	@RequestMapping("/stop")
	public String toStop(@RequestParam("moduleId")String[] moduleIds){
		int state = 0;  //停用
		moduleService.updateState(moduleIds,state);
		return "redirect:/sysadmin/module/list";
	}
	
	
	@RequestMapping("/tocreate")
	public String toCreate(Model model){
		
		//为新增页面准备上级模块列表信息
		List<Module> parentList = moduleService.findAll();
		
		model.addAttribute("parentList", parentList);
		//跳转到新增页面
		return "/sysadmin/module/jModuleCreate";
	}
	
	
	//新增模块信息
	@RequestMapping("/save")
	public String saveModule(Module module){
		
		moduleService.saveModule(module);
		
		//新增完成之后 页面跳转回列表页面
		return "redirect:/sysadmin/module/list";
	}
	
	//模块的查看
	@RequestMapping("/toview")
	public String toView(String moduleId,Model model){
		
		Module module = moduleService.findModuleById(moduleId);
		
		model.addAttribute("module", module);
		
		//跳转到查看页面
		return "/sysadmin/module/jModuleView";
	}
	
	@RequestMapping("/toupdate")
	public String toUpdate(String moduleId,Model model){
		//根据moduleId查询用户信息
		Module module = moduleService.findModuleById(moduleId);
		
		//2.准备上级模块的下拉列表
		List<Module> parentList = moduleService.findAll();
		
		model.addAttribute("module", module);
		model.addAttribute("parentList", parentList);
		
		//跳转到模块修改页面
		return "/sysadmin/module/jModuleUpdate";
	}
	
	//模块修改
	@RequestMapping("/update")
	public String updateModule(Module module){
		
		moduleService.updateModule(module);
		
		//跳转到模块列表页面
		return "redirect:/sysadmin/module/list";
	}
	
	
}
