package org.no_ip.stieflo.web;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.no_ip.stieflo.entities.Vertaler;
import org.no_ip.stieflo.exceptions.HeeftNogStripsException;
import org.no_ip.stieflo.services.VertalerServiceInterface;
import org.no_ip.stieflo.web.forms.ZoekenForm;

@Controller 
@RequestMapping(path = "/vertalers", produces = MediaType.TEXT_HTML_VALUE)
class VertalerController { 
	private static final String VERTALERS_VIEW = "vertalers/vertalers";
	private static final String TOEVOEGEN_VIEW = "vertalers/toevoegen";
	private static final String REDIRECT_NA_TOEVOEGEN = "redirect:/vertalers";
	private static final String REDIRECT_VERTALER_NIET_GEVONDEN = "redirect:/vertalers";
	private static final String REDIRECT_VERTALER_HEEFT_NOG_STRIPS = "redirect:/vertalers";
	private static final String REDIRECT_NA_VERWIJDEREN = "redirect:/vertalers/{id}/verwijderd";
	private static final String VERWIJDERD_VIEW = "vertalers/verwijderd";
	private static final String WIJZIGEN_VIEW = "vertalers/wijzigen";
	private static final String REDIRECT_NA_WIJZIGEN = "redirect:/vertalers";
		
	private static final Logger logger = Logger.getLogger(VertalerController.class.getName());
	private final VertalerServiceInterface vertalerService; 
	
	@Autowired 
	VertalerController(VertalerServiceInterface vertalerService) {  
		this.vertalerService = vertalerService;
	} 
	
	/** --------------------- Vertaler zoeken en bekijken --------------------- **/
	@RequestMapping(method = RequestMethod.GET) 
	ModelAndView ZoekenForm() {
		ModelAndView modelAndView = new ModelAndView(VERTALERS_VIEW);
		modelAndView.addObject("vertalers", vertalerService.findAll());
		modelAndView.addObject(new ZoekenForm());
		return modelAndView; 
	}
	
	@RequestMapping(method = RequestMethod.GET, params = "zoekTerm") 
	ModelAndView vertalerZoeken(@Valid ZoekenForm form, BindingResult bindingResult) {   
		ModelAndView modelAndView = new ModelAndView(VERTALERS_VIEW);
		if ( ! bindingResult.hasErrors()) {
		    List<Vertaler> vertalers = new ArrayList<>(vertalerService.findInNaam(form.getZoekTerm()));
			if (vertalers.isEmpty()) {
		    	modelAndView.addObject("nietsgevonden", "De zoekterm werd helaas niet terug gevonden");
		    	modelAndView.addObject("vertalers", vertalerService.findAll());
		    } else {
		    	modelAndView.addObject("vertalers", vertalers);
		    }
		}
		return modelAndView; 
	}
	
	/** -------------------------- Vertaler toevoegen -------------------------- **/
	@RequestMapping(path = "toevoegen", method = RequestMethod.GET)
	ModelAndView createForm() {
	  return new ModelAndView(TOEVOEGEN_VIEW, "vertaler", new Vertaler());
	} 
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Vertaler vertaler, BindingResult bindingResult) {
	  if (bindingResult.hasErrors()) {
	    return TOEVOEGEN_VIEW;
	  }
	  vertalerService.create(vertaler);
	  return REDIRECT_NA_TOEVOEGEN;
	}
	
	@RequestMapping(path = "{vertaler}/verwijderen", method = RequestMethod.POST) 
	String delete(@PathVariable Vertaler vertaler, RedirectAttributes redirectAttributes) { 
		if (vertaler == null) { 
			return REDIRECT_VERTALER_NIET_GEVONDEN;
		}
		long id = vertaler.getId();
		try {
			vertalerService.delete(id);
			redirectAttributes.addAttribute("id", id).addAttribute("naam", vertaler.getNaam());
			return REDIRECT_NA_VERWIJDEREN; 
	    } catch (HeeftNogStripsException ex) {
	    	redirectAttributes.addAttribute("id", id).addAttribute("fout", "Vertaler heeft nog strips");
	    	return REDIRECT_VERTALER_HEEFT_NOG_STRIPS;
	    }
	}
		
	@RequestMapping(path = "{id}/verwijderd", method = RequestMethod.GET) 
	ModelAndView deleted(String naam) {
		return new ModelAndView(VERWIJDERD_VIEW, "naam", naam);
	}
	
	/** -------------------------- Vertaler wijzigen -------------------------- **/
	@RequestMapping(path ="{vertaler}/wijzigen", method = RequestMethod.GET)
	ModelAndView updateForm(@PathVariable Vertaler vertaler) { 
	  if (vertaler == null) {
	    return new ModelAndView(REDIRECT_VERTALER_NIET_GEVONDEN);
	  }
	  return new ModelAndView(WIJZIGEN_VIEW).addObject(vertaler);
	} 	
	
	@RequestMapping(path = "{id}/wijzigen", method = RequestMethod.POST) 
	String update(@Valid Vertaler vertaler, BindingResult bindingResult) {
		logger.info("vertaler "+vertaler.getId()+" "+vertaler.getNaam()+" wijzigen" );
		if (bindingResult.hasErrors()) {
			return WIJZIGEN_VIEW;
		}
		vertalerService.update(vertaler);
		return REDIRECT_NA_WIJZIGEN;
	}
		
	/*@RequestMapping(path = "{id}/wijzigen", method = RequestMethod.POST) 
	String update(@PathVariable Long id) {
		logger.info("De id is         :"+id);
		Vertaler vertaler = vertalerService.read(id);
		logger.info("vertaler "+vertaler.getId()+" "+vertaler.getNaam()+" wijzigen" );
		if (vertaler.getId() == 0) {
			return WIJZIGEN_VIEW;
		}
		//nog ergens gewijzigde naam toekennen
		vertalerService.update(vertaler);
		logger.info("vertaler "+vertaler.getId()+" "+vertaler.getNaam()+" naar update gestuurd" );
		return REDIRECT_NA_WIJZIGEN;
	}*/
	
	/** ---------------------------- Databinding ---------------------------- **/
	/*@InitBinder("vertaler") 
	void initBinderVertaler(WebDataBinder binder) {
	  binder.initDirectFieldAccess(); 
	}*/
} 