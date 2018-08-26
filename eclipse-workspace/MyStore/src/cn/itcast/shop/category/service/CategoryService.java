package cn.itcast.shop.category.service;

import java.util.List;
import cn.itcast.shop.category.dao.CategoryDao;
import cn.itcast.shop.category.vo.Category;

public class CategoryService {
	//注入categorydao
	private CategoryDao categoryDao;


	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}


	public List<Category> findAll() {
		
		return categoryDao.findAll();
	}
	
}
