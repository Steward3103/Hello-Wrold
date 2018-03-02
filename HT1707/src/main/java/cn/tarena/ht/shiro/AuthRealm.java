package cn.tarena.ht.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.org.apache.xerces.internal.xs.StringList;

import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.service.UserService;

public class AuthRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	//权限控制
	
	//当前用户已经拥有的权限列表
			/*List<String> priList = new ArrayList<String>();
			priList.add("系统管理");
			priList.add("基础信息");
			priList.add("货运管理");*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/**
		 * 动态获取模块信息的思路
		 * 1.获取user对象  获取userId
		 * 2.通过userId---role_user_p---role_module_p---module_p---name属性
		 */
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		//通过userId查询当前用户的所有模块名称
		List<String> priList = userService.findPrililegeInfoList(user.getUserId());
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//为info对象赋值用户权限列表
		info.addStringPermissions(priList);
		return info;
	}

	
	//登陆认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		/*
		 * 为Shiro提供真实的用户数据
		 * 1.通过token获取用户名和密码
		 * 2.通过用户名查询用户的真实的信息  真实的密码
		 * 3.获取数据后 通过info对象返回给shiro安全管理器
		 */
		UsernamePasswordToken loginToken = (UsernamePasswordToken) token;
		String username = loginToken.getUsername();
		//通过用户名查询用户信息
		User user = userService.findUserByUserName(username);
		/**
		 * 1.principal  真实的用户对象
		 * 2.credentials  真实的密码
		 * 3.realmName    当前realm的名称
		 */
		AuthenticationInfo info = 
				new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		
		return info;
	}




	
	
	
	
}
