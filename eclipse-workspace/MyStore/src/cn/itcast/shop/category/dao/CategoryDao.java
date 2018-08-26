package cn.itcast.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.category.vo.Category;

public class CategoryDao extends HibernateDaoSupport{
public List<Category> findAll(){
	String hql="from Category";
	List<Category> list=this.getHibernateTemplate().find(hql);
	return list;
}
}
