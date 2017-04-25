package xyinc.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyinc.Converter.OrderConverter;
import xyinc.Dao.OrderDao;
import xyinc.View.Model.OrderDataVM;

@Service
public class OrderService {
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	OrderConverter orderConverter;
	
	public void createOrder(OrderDataVM orderDataVM){
		
		orderDao.create(orderConverter.convert(orderDataVM));
		
	}

}
