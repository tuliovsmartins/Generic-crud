package xyinc.Converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import xyinc.Entity.Order;
import xyinc.View.Model.OrderDataVM;

@Component
public class OrderConverter {
	
	public Order convert(OrderDataVM orderDataVM){
		
		Order order = new Order();
		
		order.setId(orderDataVM.getId());
		order.setProductId(orderDataVM.getProductId());
		order.setQuantity(orderDataVM.getQuantity());
		order.setOrderDate(orderDataVM.getOrderDate());
		
		return order;
	
	}
	
	public OrderDataVM convert(Order order){
		
		OrderDataVM orderDataVM = new OrderDataVM();
		
		orderDataVM.setId(order.getId());
		orderDataVM.setProductId(order.getProductId());
		orderDataVM.setQuantity(order.getQuantity());
		orderDataVM.setOrderDate(order.getOrderDate());
		
		return orderDataVM;
	
	}
	
	public List<OrderDataVM> convertList(List<Order> order){
		
		List<OrderDataVM> orderDataVM = new  ArrayList<OrderDataVM>();
		
		for(Order orders : order){
			orderDataVM.add(OrderDataVM.convertFrom(orders));
        }
		
		return orderDataVM;
		
	}
	
}
