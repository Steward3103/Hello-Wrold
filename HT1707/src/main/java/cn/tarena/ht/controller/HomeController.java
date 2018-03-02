package cn.tarena.ht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
		
	//home.action
	
	//转向欢迎页面
	@RequestMapping("/home")
	public String home(){
		return "/home/fmain";
	}
	
	//转向tilte标题栏页面
	@RequestMapping("/title")
	public String title(){
		return "/home/title";
	}
	
	//转向home的左侧页面
	/*@RequestMapping("/homeLeft")
	public String homeLeft(){
		return "/home/left";
	}
	
	//转向home的操作页面
	@RequestMapping("/homeMain")
	public String homeMain(){
		return "/home/main";
	}*/
	
	
	@RequestMapping("/{moduleName}/Left")
	public String moduleLeft(@PathVariable String moduleName){
		
		return "/"+moduleName+"/left";
	}
	
	@RequestMapping("/{moduleName}/Main")
	public String moduleMain(@PathVariable String moduleName){
		return "/"+moduleName+"/main";
	}
	
	
	/*restFul回顾
	 * localhost:8090/addUser/tom/12345
	@RequestMapping("/addUser/{uaername}/{password}")
	public String addUser(@PathVariable String username,@PathVariable String password){
		
	}*/

	
	
	/*@RequestMapping("/sysadminLeft")
	public String sysadminLeft(){
		return "/sysadmin/left";
	}
	
	@RequestMapping("/sysadminMain")
	public String sysadminMain(){
		return "/sysadmin/main";
	}
	
	@RequestMapping("/baseInfoLeft")
	public String sysadminLeftA(){
		return "/baseInfo/left";
	}
	
	@RequestMapping("/baseInfoMain")
	public String sysadminMainA(){
		return "/baseInfo/main";
	}*/
	
	
	
	
	
	
	
}
