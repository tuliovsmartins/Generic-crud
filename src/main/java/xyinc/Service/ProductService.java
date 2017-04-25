package xyinc.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyinc.Converter.ProductConverter;
import xyinc.Dao.ProductDao;
import xyinc.View.Model.ProductDataVM;

@Service
public class ProductService {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductConverter productConverter;
	
	public void createProduct(ProductDataVM productDataVM){
		
		productDao.create(productConverter.convert(productDataVM));
		
	}
	
	public List<ProductDataVM> retrieveProduct(){
	
		List<ProductDataVM> productDataVM = productConverter.convertList(productDao.getAll());
		
		return productDataVM;
		
	}
	
	public ProductDataVM retrieveIndividualProduct(long id){
		
		ProductDataVM productDataVM = productConverter.convert(productDao.getById(id));
		
		return productDataVM;
		
	}
	
	public void DeleteById(long id){
		ProductDataVM productDataVM = productConverter.convert(productDao.getById(id));
		productDao.deleteById(productConverter.convert(productDataVM));
		
		
	}

}
