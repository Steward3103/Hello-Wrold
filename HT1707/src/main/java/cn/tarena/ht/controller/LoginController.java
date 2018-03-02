package cn.tarena.ht.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.service.UserService;
import cn.tarena.ht.tool.Md5HashPassword;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/toLogin")
	public String toLogin(){
		
		//应该跳转到用户的登陆页面
		return "/sysadmin/login/login";
	}
	
	
	@RequestMapping("/login")
	public String Login(String username,String password,Model model,HttpSession session){
		
		//判断用户名和密码是否为空
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			//表示用户名和密码为空
			model.addAttribute("errorInfo", "用户名或密码不能为空");
			
			//跳转到用户登陆页面
			return "/sysadmin/login/login";
		}
		
		//通过subject进行登陆操作
		Subject subject = SecurityUtils.getSubject();
		//String md5HashPassword = Md5HashPassword.getMd5Hash(password, username);
		//token 票令牌   作用包装用户名和密码的     狮子精  大象精  大鹏鸟  小钻风 
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		//如果用户登陆不正确  则shiro会报错，如果用户名和密码正确 则执行下一行代码，如果登陆正确，
		//shiro则会放行当前subject的全部请求
		try {
			subject.login(token);   
			
			//获取用户真实信息
			User user = (User) subject.getPrincipal();
			//将用户信息存入session域
			session.setAttribute("userSession", user);
			
			//subject.getSession().setAttribute("userSession", user);   subject获取session
			return "redirect:/home.action";	
		} catch (AuthenticationException e) {
			e.printStackTrace();  //打印报错信息  查询真实的报错
			//表示用户名和密码不正确
			model.addAttribute("errorInfo", "用户名或密码不正确");
			return "/sysadmin/login/login";
		}
		
	}

	/**
	 * 思路：
	 * 	1.判断用户名或密码是否为空或空串  如果为空应该给用户提示信息
	 *  2.根据用户名和密码查询用户信息，如果用户信息不为null 则证明用户名和密码正确
	 *    页面应该跳转到系统首页。
	 *    入股user对象为空，则证明用户输入的用户名和密码不正确，给用户提示信息。
	 *  
	 * @param username
	 * @param password
	 * @return
	 */
	/*@RequestMapping("/login")
	public String Login(String username,String password,Model model,HttpSession session){
		
		//判断用户名和密码是否为空
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			//表示用户名和密码为空
			model.addAttribute("errorInfo", "用户名或密码不能为空");
			
			//跳转到用户登陆页面
			return "/sysadmin/login/login";
		}
		
		//将明文转化为密文：
		String md5Password = Md5HashPassword.getMd5Hash(password, username);
		
		//根据用户名和密码查询数据
		User user = userService.findUserByU_P(username,md5Password);
		
		if(user == null){
			//表示用户名和密码不正确
			model.addAttribute("errorInfo", "用户名或密码不正确");
			return "/sysadmin/login/login";
		}
		
		//将用户信息存入session域
		session.setAttribute("userSession", user);
		
		//表示用户名和密码正确
		return "redirect:/home.action";	
	}*/
	
	
	@RequestMapping("/logout")
	public String loginOut(HttpSession httpSession){
		
		//将user对象从session域中删除
		httpSession.removeAttribute("userSession");
		//页面应该跳转到登陆页面
		return "/sysadmin/login/login";
	}
	
	
	
	
}
