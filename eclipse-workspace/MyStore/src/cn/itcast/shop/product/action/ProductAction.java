package cn.itcast.shop.product.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
//用于接受数据的模型驱动
	private Product product=new Product();
	//注入商品的service
	private ProductService productService;
	//接受分类cid
	private Integer cid;
	private Integer csid;
	//注入一级分类的service
	private CategoryService categoryService;
	//接收当前页数
	private int page;
	
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	public String findByPid() {
		//调用service方法完成查询
		product=productService.findByPid(product.getPid());
		return "findByPid";
	}
	//根据分类的id查询商品
	public String findByCid() {
		//List<Category>  cList=categoryService.findAll();
		PageBean<Product> pageBean=productService.findByPageCid(cid,page);
		//将pagebean存入到值盏中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	public String findByCsid() {

		// 根据二级分类查询商品
		PageBean<Product> pageBean = productService.findByPageCsid(csid, page);
		// 将PageBean存入到值栈中:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
