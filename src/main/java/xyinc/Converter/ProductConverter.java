package xyinc.Converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import xyinc.Entity.Product;
import xyinc.View.Model.ProductDataVM;


@Component
public class ProductConverter {
	
	public Product convert(ProductDataVM productDataVM){
		
		Product product = new Product();
		
		product.setId(productDataVM.getId());
		product.setName(productDataVM.getName());
		product.setDescription(productDataVM.getDescription());
		product.setPrice(productDataVM.getPrice());
		product.setQuantity(productDataVM.getQuantity());
		product.setCreateDate(productDataVM.getCreateDate());
		
		return product;
	
	}
	
	public ProductDataVM convert(Product product){
		
		ProductDataVM productDataVM = new ProductDataVM();
		
		productDataVM.setId(product.getId());
		productDataVM.setName(product.getName());
		productDataVM.setDescription(product.getDescription());
		productDataVM.setPrice(product.getPrice());
		productDataVM.setQuantity(product.getQuantity());
		productDataVM.setCreateDate(product.getCreateDate());
		
		return productDataVM;
	
	}
	
	public List<ProductDataVM> convertList(List<Product> product){
		
		List<ProductDataVM> productDataVM = new  ArrayList<ProductDataVM>();
		
		for(Product products : product){
			productDataVM.add(ProductDataVM.convertFrom(products));
        }
		
		return productDataVM;
		
	}

}
