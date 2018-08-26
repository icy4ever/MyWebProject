package cn.itcast.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.UUIDUtils;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user=new User();
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService=userService;
	}
	public User getModel() {
		return user;
	}
	public String registPage() {
		return "registPage";
	}
	public String findByName() throws IOException {
		//调用Service进行查询：
		User existUser=userService.findByUsername(user.getUsername());
		//获得response对象,项页面输出：
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(existUser !=null) {
			//查询到该用户：用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		}else {
			//没查询到该用户：用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}
	public String regist() {
		String checkcode1=(String)ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcode1)) {
			this.addActionError("验证码输入错误！");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("注册成功！");
		return "msg";
	}

	public String loginPage() {
		return "loginPage";
	}
	//登陆
	public String login() {
		User existUser=userService.login(user);
		if(existUser==null) {
			this.addActionError("登陆失败！未注册或账户错误！");
			return LOGIN;
			//登录失败
		}
		else {
			ServletActionContext.getRequest().getSession().setAttribute("existUser",existUser);
			return "loginSuccess";
		}
	}
	//用户推出
	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	
}
