package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;
import cn.tarena.ht.service.DeptService;
import cn.tarena.ht.service.RoleService;
import cn.tarena.ht.service.UserService;

@Controller
@RequestMapping("/sysadmin/user")   //添加了日期转化格式
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/list")
	public String findAll(Model model){
		
		List<User> userList = userService.findAll();
		model.addAttribute("userList", userList);
		
		//跳转到用户展现页面
		return "/sysadmin/user/jUserList";	
	}
	
	//状态的启用
	@RequestMapping("/start")
	public String toStart(@RequestParam("userId")String[] userIds){
		int state = 1;  //启用
		userService.updateState(state,userIds);
		//跳转回用户列表页面
		return "redirect:/sysadmin/user/list";
	}
	
	//状态的停用
	@RequestMapping("/stop")
	public String toStop(@RequestParam("userId")String[] userIds){
		int state = 0;  //停用
		userService.updateState(state,userIds);
		//跳转回用户列表页面
		return "redirect:/sysadmin/user/list";
	}
	
	
	//用户删除操作
	@RequestMapping("/delete")
	public String toDelete(@RequestParam("userId")String[] userIds){
		
		userService.deleteUsers(userIds);
		
		//跳转到用户列表页面
		return "redirect:/sysadmin/user/list";
	}
	
	//跳转到用户的新增页面
	@RequestMapping("/tocreate")
	public String toCreate(Model model){
		
		//准备部门列表信息
		List<Dept> deptList = deptService.findAll();
		
		//准备上级领导列表  要求获取 userId和name属性
		List<UserInfo> managerList = userService.findUserList();
		
		model.addAttribute("deptList", deptList);
		model.addAttribute("managerList", managerList);
		
		return "/sysadmin/user/jUserCreate";
	}
	
	
	//用户新增
	@RequestMapping("/save")
	public String saveUser(User user){
		
		userService.saveUser(user);
		return "redirect:/sysadmin/user/list";
	}
	
	
	//跳转到用户修改页面
	@RequestMapping("/toupdate")
	public String toUpateUser(String userId,Model model){
		
		//根据userId查询用户信息
		User user = userService.findUserById(userId);
		
		//为页面准备下拉列表  部门列表
		List<Dept> deptList = deptService.findAll();
		
		//准备直属领导列表
		List<UserInfo> managerList = userService.findUserList();
		
		model.addAttribute("user", user);
		model.addAttribute("deptList", deptList);
		model.addAttribute("managerList", managerList);
		return "/sysadmin/user/jUserUpdate";
	
	}
	
	//用户修改
	@RequestMapping("/update")
	public String updateUser(User user){
		
		userService.updateUser(user);
		
		return "redirect:/sysadmin/user/list";
	}
	
	
	//跳转到用户角色分配页面
	@RequestMapping("/toRole")
	public String toRole(String userId,Model model) throws JsonProcessingException{
		//1.准备全部角色信息
		List<Role> roleList = roleService.findAll();
		//2.根据userId查询用户已经拥有的角色信息
		List<String> uRoleList = userService.finduRoleList(userId);
		
		for (Role role : roleList) {
			if(uRoleList.contains(role.getRoleId())){
				//证明用户已经拥有该角色信息  需要将checked属性设置为true
				role.setChecked(true);
			}
		}
		
		//由于页面中的zTree需要JSON串数据  所以需要将roleList 转化为JSON
		ObjectMapper objectMapper = new ObjectMapper();
		//通过objectMapper获取数据时，必须添加getXX（）否则不能获取数据
		String zTreeJSON = objectMapper.writeValueAsString(roleList);
		
		//为了实现新增操作 需要传递userId
		model.addAttribute("userId", userId);
		model.addAttribute("zTreeJSON", zTreeJSON);
		return "/sysadmin/user/jUserRole";
	}
	
	
	@RequestMapping("/saveUserRole")
	public String saveUserRole(String userId,String[] roleIds){
		
		//新增用户和角色信息
		userService.saveUserRole(userId,roleIds);
		
		return "redirect:/sysadmin/user/list";
	}
	
	
	
	
	
}
