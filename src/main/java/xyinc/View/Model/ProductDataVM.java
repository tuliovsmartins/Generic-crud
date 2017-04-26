package xyinc.View.Model;

import java.math.BigDecimal;
import java.util.Date;
import xyinc.Entity.Product;

public class ProductDataVM {

	
	private long id;
	private String name;
	private String description;
	private BigDecimal price;
	private long quantity;
	private Date createDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public static ProductDataVM convertFrom(Product product){
		
		ProductDataVM productDataVM = new ProductDataVM();
		
		productDataVM.setId(product.getId());
		productDataVM.setName(product.getName());
		productDataVM.setDescription(product.getDescription());
		productDataVM.setPrice(product.getPrice());
		productDataVM.setQuantity(product.getQuantity());
		productDataVM.setCreateDate(product.getCreateDate());
		
		return productDataVM;
		
	}
}
