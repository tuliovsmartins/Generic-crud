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

import xyinc.Service.ClientService;
import xyinc.View.Model.ClientDataVM;


@Controller
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	ClientService clientService;

	@RequestMapping(value = "/createClient", method = RequestMethod.GET)
	public ModelAndView createClient(ClientDataVM clientDataVM) {
		
		ModelAndView mv = new ModelAndView("/client/createClient");
		return mv;

	}
	
	@RequestMapping(value = "/createClient", method = RequestMethod.POST)
	public ModelAndView saveClient(@ModelAttribute ClientDataVM clientDataVM, RedirectAttributes atributes, Model model) {
		
		clientDataVM.setCreateDate(new Date());
		clientService.createClient(clientDataVM);
		ModelAndView mv = new ModelAndView("redirect:/client/createClient");
		atributes.addFlashAttribute("message", "Cliente " + clientDataVM.getName() + " cadastrado com sucesso!");
		
		return mv;
		
	}
	
	@RequestMapping(value = "/retrieveClient", method = RequestMethod.GET)
	public ModelAndView retrieveClient(Model model) {
		
		
		ModelAndView mv = new ModelAndView("/client/retrieveClient");
		model.addAttribute("client", clientService.retrieveClient());
		return mv;

	}
	
	@RequestMapping(value = "/retrieveIndividualClient/{id}", method = RequestMethod.GET)
	public ModelAndView retrieveIndividualClient(Model model, @PathVariable("id") long id) {
		
		
		ModelAndView mv = new ModelAndView("/client/individualClient");
		model.addAttribute("client", clientService.retrieveIndividualClient(id));
		return mv;

	}
	
	@RequestMapping(value = "/deleteClient", method = RequestMethod.DELETE)
	public void deleteClient(Model model, @RequestBody ClientDataVM clientDataVM) {
		
		clientService.DeleteById(clientDataVM.getId());
		

	}
	
	
	@RequestMapping(value = "/editClient/{id}", method = RequestMethod.GET)
	public ModelAndView editViewClient(Model model,  @PathVariable("id") long id) {
		
		ModelAndView mv = new ModelAndView("/client/editClient");
		model.addAttribute("client", clientService.retrieveIndividualClient(id));
		return mv;
		
	}
	
	@RequestMapping(value = "/editClient/{id}", method = RequestMethod.PUT)
	public ModelAndView editClient(Model model, ClientDataVM clientDataVM, @PathVariable("id") long id, RedirectAttributes atributes) {
	
		clientDataVM.setCreateDate(new Date());
		clientDataVM.setId(id);
		clientService.createClient(clientDataVM);
		ModelAndView mv = new ModelAndView("redirect:/client/editClient/" + clientDataVM.getId());
		atributes.addFlashAttribute("message", "Cliente " + clientDataVM.getName() + " editado com sucesso!");
		
		return mv;
		
	}
	
		
}
