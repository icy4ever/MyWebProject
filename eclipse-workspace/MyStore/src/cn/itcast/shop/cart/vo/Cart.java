package cn.itcast.shop.cart.vo;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	//购物集合：Map的key
	private Map<Integer,CartItem> map=new LinkedHashMap<>();
	private double total;
	
	
}
