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

import org.no_ip.stieflo.entities.Reeks;
import org.no_ip.stieflo.entities.Uitgever;
import org.no_ip.stieflo.exceptions.HeeftNogStripsException;
import org.no_ip.stieflo.services.ReeksServiceInterface;
import org.no_ip.stieflo.web.forms.ZoekenForm;

@Controller 
@RequestMapping(path = "/reeksen", produces = MediaType.TEXT_HTML_VALUE)
class ReeksController { 
	private static final String REEKSEN_VIEW = "reeksen/reeksen";
	private static final String TOEVOEGEN_VIEW = "reeksen/toevoegen";
	private static final String REDIRECT_NA_TOEVOEGEN = "redirect:/reeksen";
	private static final String REDIRECT_REEKS_NIET_GEVONDEN = "redirect:/reeksen";
	private static final String REDIRECT_REEKS_HEEFT_NOG_STRIPS = "redirect:/reeksen";
	private static final String REDIRECT_NA_VERWIJDEREN = "redirect:/reeksen/{id}/verwijderd";
	private static final String VERWIJDERD_VIEW = "reeksen/verwijderd";
	private static final String WIJZIGEN_VIEW = "reeksen/wijzigen";
	private static final String REDIRECT_NA_WIJZIGEN = "redirect:/reeksen";
		
	private static final Logger logger = Logger.getLogger(ReeksController.class.getName());
	private final ReeksServiceInterface reeksService; 
	
	@Autowired 
	ReeksController(ReeksServiceInterface reeksService) {  
		this.reeksService = reeksService;
	} 
	
	/** --------------------- Reeksen zoeken en bekijken --------------------- **/
	@RequestMapping(method = RequestMethod.GET) 
	ModelAndView ZoekenForm() {
		ModelAndView modelAndView = new ModelAndView(REEKSEN_VIEW);
		modelAndView.addObject("reeksen", reeksService.findAll());
		modelAndView.addObject(new ZoekenForm());
		return modelAndView; 
	}
	
	@RequestMapping(method = RequestMethod.GET, params = "zoekTerm") 
	ModelAndView reeksZoeken(@Valid ZoekenForm form, BindingResult bindingResult) {   
		ModelAndView modelAndView = new ModelAndView(REEKSEN_VIEW);
		if ( ! bindingResult.hasErrors()) {
		    String zoekterm = form.getZoekTerm();
			List<Reeks> reeksen = new ArrayList<>(reeksService.findInNaam(zoekterm));
			if (reeksen.isEmpty()) {
		    	modelAndView.addObject("nietsgevonden", "De zoekterm werd helaas niet terug gevonden");
		    	modelAndView.addObject("reeksen", reeksService.findAll());
		    } else {
		    	modelAndView.addObject("reeksen", reeksen);
		    	modelAndView.addObject("zoekTerm",zoekterm);
		    }
		}
		return modelAndView; 
	}
	
	/** -------------------------- Reeks Info bekijken  -------------------------- **/			
	@RequestMapping(path = "{gekozenreeks}", method = RequestMethod.GET, params = "zoekTerm") 
	ModelAndView read(@PathVariable Reeks gekozenreeks, @Valid ZoekenForm form, BindingResult bindingResult) {   
		ModelAndView modelAndView = new ModelAndView(REEKSEN_VIEW);
		if ( ! bindingResult.hasErrors()) {
			String zoekterm = form.getZoekTerm();
			List<Reeks> reeksen = new ArrayList<>(reeksService.findInNaam(zoekterm));
			if (reeksen.isEmpty()) {
		    	modelAndView.addObject("nietsgevonden", "De zoekterm werd helaas niet terug gevonden");
		    	modelAndView.addObject("reeksen", reeksService.findAll());
		    } else {
		    	modelAndView.addObject("reeksen", reeksen);
		    	modelAndView.addObject("zoekTerm",zoekterm);
		    }
		}
		if (gekozenreeks != null) {
			modelAndView.addObject(gekozenreeks);
		}
		return modelAndView.addObject(form);
	}
	
	/** -------------------------- Reeksen toevoegen -------------------------- **/
	@RequestMapping(path = "toevoegen", method = RequestMethod.GET)
	ModelAndView createForm() {
	  return new ModelAndView(TOEVOEGEN_VIEW, "reeks", new Reeks()).addObject("genres", reeksService.findAllGenres());
	} 
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Reeks reeks, BindingResult bindingResult) {
	  if (bindingResult.hasErrors()) {
	    return TOEVOEGEN_VIEW;
	  }
	  reeksService.create(reeks);
	  return REDIRECT_NA_TOEVOEGEN;
	}
	
	/** -------------------------- Reeks verwijderen -------------------------- **/
	@RequestMapping(path = "{reeks}/verwijderen", method = RequestMethod.POST) 
	String delete(@PathVariable Reeks reeks, RedirectAttributes redirectAttributes) { 
		if (reeks == null) { 
			return REDIRECT_REEKS_NIET_GEVONDEN;
		}
		long id = reeks.getId();
		try {
			reeksService.delete(id);
			redirectAttributes.addAttribute("id", id).addAttribute("naam", reeks.getNaam());
			return REDIRECT_NA_VERWIJDEREN; 
	    } catch (HeeftNogStripsException ex) {
	    	redirectAttributes.addAttribute("id", id).addAttribute("fout", "Reeks bevat nog strips");
	    	return REDIRECT_REEKS_HEEFT_NOG_STRIPS;
	    }
	}
		
	@RequestMapping(path = "{id}/verwijderd", method = RequestMethod.GET) 
	ModelAndView deleted(String naam) {
		return new ModelAndView(VERWIJDERD_VIEW, "naam", naam);
	}
	
	/** -------------------------- Reeks wijzigen -------------------------- **/
	@RequestMapping(path ="{reeks}/wijzigen", method = RequestMethod.GET)
	ModelAndView updateForm(@PathVariable Reeks reeks) { 
	  if (reeks == null) {
	    return new ModelAndView(REDIRECT_REEKS_NIET_GEVONDEN);
	  }
	  return new ModelAndView(WIJZIGEN_VIEW).addObject(reeks).addObject("genres", reeksService.findAllGenres());
	} 	
	
	@RequestMapping(path = "{id}/wijzigen", method = RequestMethod.POST) 
	String update(@Valid Reeks reeks, BindingResult bindingResult) {
		logger.info("vertaler "+reeks.getId()+" "+reeks.getNaam()+" wijzigen" );
		if (bindingResult.hasErrors()) {
			return WIJZIGEN_VIEW;
		}
		reeksService.update(reeks);
		return REDIRECT_NA_WIJZIGEN;
	}
	
	/** ---------------------------- Databinding ---------------------------- **/			
	/*@InitBinder("reeks") 
	void initBinderReeks(WebDataBinder binder) {
	  binder.initDirectFieldAccess(); 
	}*/
} 