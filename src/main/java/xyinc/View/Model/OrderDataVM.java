package xyinc.View.Model;

import java.util.Date;
import xyinc.Entity.Order;

public class OrderDataVM {
	
	private long id;
	private long productId;
	private long quantity;
	private Date orderDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public static OrderDataVM convertFrom(Order order){
		
		OrderDataVM orderDataVM = new OrderDataVM();
		
		orderDataVM.setId(order.getId());
		orderDataVM.setProductId(order.getProductId());
		orderDataVM.setQuantity(order.getQuantity());
		orderDataVM.setOrderDate(order.getOrderDate());
		
		return orderDataVM;
		
	}

}
