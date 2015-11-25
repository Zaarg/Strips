package org.no_ip.stieflo.web;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.no_ip.stieflo.entities.Uitgever;
import org.no_ip.stieflo.exceptions.HeeftNogStripsException;
import org.no_ip.stieflo.services.UitgeverServiceInterface;
import org.no_ip.stieflo.web.forms.ZoekenForm;

@Controller 
@RequestMapping(path = "/uitgevers", produces = MediaType.TEXT_HTML_VALUE)
class UitgeverController { 
	private static final String UITGEVERS_VIEW = "uitgevers/uitgevers";
	private static final String TOEVOEGEN_VIEW = "uitgevers/toevoegen";
	private static final String REDIRECT_NA_TOEVOEGEN = "redirect:/uitgevers";
	private static final String REDIRECT_UITGEVER_NIET_GEVONDEN = "redirect:/uitgevers";
	private static final String REDIRECT_UITGEVER_HEEFT_NOG_STRIPS = "redirect:/uitgevers";
	private static final String REDIRECT_NA_VERWIJDEREN = "redirect:/uitgevers/{id}/verwijderd";
	private static final String VERWIJDERD_VIEW = "uitgevers/verwijderd";
	private static final String WIJZIGEN_VIEW = "uitgevers/wijzigen";
	private static final String REDIRECT_NA_WIJZIGEN = "redirect:/uitgevers";
	
	private static final Logger logger = Logger.getLogger(UitgeverController.class.getName());
	private final UitgeverServiceInterface uitgeverService; 
	
	@Autowired 
	UitgeverController(UitgeverServiceInterface uitgeverService) {  
		this.uitgeverService = uitgeverService;
	} 
	
	/** --------------------- Uitgever zoeken en bekijken --------------------- **/
	@RequestMapping(method = RequestMethod.GET) 
	ModelAndView ZoekenForm() {
		ModelAndView modelAndView = new ModelAndView(UITGEVERS_VIEW);
		modelAndView.addObject("uitgevers", uitgeverService.findAll());
		modelAndView.addObject(new ZoekenForm());
		return modelAndView; 
	}
	
	@RequestMapping(method = RequestMethod.GET, params = "zoekTerm") 
	ModelAndView uitgeverZoeken(@Valid ZoekenForm form, BindingResult bindingResult) {   
		ModelAndView modelAndView = new ModelAndView(UITGEVERS_VIEW);
		if ( ! bindingResult.hasErrors()) {
		    List<Uitgever> uitgevers = new ArrayList<>(uitgeverService.findInNaam(form.getZoekTerm()));
			if (uitgevers.isEmpty()) {
		    	modelAndView.addObject("nietsgevonden", "De zoekterm werd helaas niet terug gevonden");
		    	modelAndView.addObject("uitgevers", uitgeverService.findAll());
		    } else {
		    	modelAndView.addObject("uitgevers", uitgevers);
		    }
		}
		return modelAndView; 
	}
	
	/** -------------------------- Uitgever toevoegen -------------------------- **/
	@RequestMapping(path = "toevoegen", method = RequestMethod.GET)
	ModelAndView createForm() {
	  return new ModelAndView(TOEVOEGEN_VIEW, "uitgever", new Uitgever());
	} 
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Uitgever uitgever, BindingResult bindingResult) {
	  if (bindingResult.hasErrors()) {
	    return TOEVOEGEN_VIEW;
	  }
	  uitgeverService.create(uitgever);
	  return REDIRECT_NA_TOEVOEGEN;
	}
	
	/** -------------------------- Uitgever verwijderen -------------------------- **/
	@RequestMapping(path = "{uitgever}/verwijderen", method = RequestMethod.POST) 
	String delete(@PathVariable Uitgever uitgever, RedirectAttributes redirectAttributes) { 
		if (uitgever == null) { 
			return REDIRECT_UITGEVER_NIET_GEVONDEN;
		}
		long id = uitgever.getId();
		try {
			uitgeverService.delete(id);
			redirectAttributes.addAttribute("id", id).addAttribute("naam", uitgever.getNaam());
			return REDIRECT_NA_VERWIJDEREN; 
	    } catch (HeeftNogStripsException ex) {
	    	redirectAttributes.addAttribute("id", id).addAttribute("fout", "Uitgever heeft nog strips");
	    	return REDIRECT_UITGEVER_HEEFT_NOG_STRIPS;
	    }
	}
		
	@RequestMapping(path = "{id}/verwijderd", method = RequestMethod.GET) 
	ModelAndView deleted(String naam) {
		return new ModelAndView(VERWIJDERD_VIEW, "naam", naam);
	}
	
	/** -------------------------- Uitgever wijzigen -------------------------- **/
	@RequestMapping(path ="{uitgever}/wijzigen", method = RequestMethod.GET)
	ModelAndView updateForm(@PathVariable Uitgever uitgever) { 
	  if (uitgever == null) {
	    return new ModelAndView(REDIRECT_UITGEVER_NIET_GEVONDEN);
	  }
	  return new ModelAndView(WIJZIGEN_VIEW).addObject(uitgever);
	} 	
	
	@RequestMapping(path = "{id}/wijzigen", method = RequestMethod.POST) 
	String update(@Valid Uitgever uitgever, BindingResult bindingResult) {
		logger.info("vertaler "+uitgever.getId()+" "+uitgever.getNaam()+" wijzigen" );
		if (bindingResult.hasErrors()) {
			return WIJZIGEN_VIEW;
		}
		uitgeverService.update(uitgever);
		return REDIRECT_NA_WIJZIGEN;
	}
	
	/** -------------------------- Databinding voor Plaats toevoegen -------------------------- **/
	@InitBinder("plaats")
	void initBinderPlaats(WebDataBinder binder) {
	  binder.initDirectFieldAccess(); 
	}
	
	@InitBinder("uitgever")
	void initBinderUitgever(WebDataBinder binder) {
	  binder.initDirectFieldAccess(); 
	}
	
} 