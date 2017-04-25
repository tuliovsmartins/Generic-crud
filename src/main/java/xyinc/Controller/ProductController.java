package xyinc.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xyinc.Service.ProductService;
import xyinc.View.Model.ProductDataVM;

@Controller
@RequestMapping("/product")
public class ProductController {

	
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/createProduct", method = RequestMethod.GET)
	public ModelAndView createProduct(ProductDataVM productDataVM) {
		
		ModelAndView mv = new ModelAndView("/product/createProduct");
		return mv;

	}
	
	@RequestMapping(value = "/createProduct", method = RequestMethod.POST)
	public ModelAndView saveClient(@ModelAttribute ProductDataVM productDataVM, RedirectAttributes atributes, Model model) {
		
		productDataVM.setCreateDate(new Date());
		productService.createProduct(productDataVM);
		ModelAndView mv = new ModelAndView("redirect:/product/createProduct");
		atributes.addFlashAttribute("message", "Produto " + productDataVM.getName() + " cadastrado com sucesso!");
		
		return mv;
		
	}
	
	@RequestMapping(value = "/retrieveProduct", method = RequestMethod.GET)
	public ModelAndView retrieveProduct(Model model) {
		
		
		ModelAndView mv = new ModelAndView("/product/retrieveProduct");
		model.addAttribute("product", productService.retrieveProduct());
		return mv;

	}
	
	@RequestMapping(value = "/retrieveIndividualProduct/{id}", method = RequestMethod.GET)
	public ModelAndView retrieveIndividualProduct(Model model, @PathVariable("id") long id) {
		
		
		ModelAndView mv = new ModelAndView("/product/individualProduct");
		model.addAttribute("product", productService.retrieveIndividualProduct(id));
		return mv;

	}
	
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.DELETE)
	public void deleteProduct(Model model, @RequestBody ProductDataVM productDataVM) {
		
		productService.DeleteById(productDataVM.getId());
		

	}
	
	
	@RequestMapping(value = "/editProduct/{id}", method = RequestMethod.GET)
	public ModelAndView editViewProduct(Model model,  @PathVariable("id") long id) {
		
		ModelAndView mv = new ModelAndView("/product/editProduct");
		model.addAttribute("product", productService.retrieveIndividualProduct(id));
		return mv;
		
	}
	
	@RequestMapping(value = "/editProduct/{id}", method = RequestMethod.PUT)
	public ModelAndView editClient(Model model, ProductDataVM productDataVM, @PathVariable("id") long id, RedirectAttributes atributes) {
	
		productDataVM.setCreateDate(new Date());
		productDataVM.setId(id);
		productService.createProduct(productDataVM);
		ModelAndView mv = new ModelAndView("redirect:/product/editProduct/" + productDataVM.getId());
		atributes.addFlashAttribute("message", "Produto " + productDataVM.getName() + " editado com sucesso!");
		
		return mv;
		
	}
}
