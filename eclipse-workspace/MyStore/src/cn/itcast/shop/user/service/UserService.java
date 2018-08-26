package cn.itcast.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.vo.User;
@Transactional
public class UserService {
	// 注入UserDao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao=userDao;
	}
	public void save(User user) {
		// TODO Auto-generated method stub
		// 将数据存入到数据库
				user.setState(1); // 0:代表用户未激活.  1:代表用户已经激活
				userDao.save(user);
	}
	
	public User findByUsername(String username) {
		return userDao.findByUserName(username);
	}

	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}


}
