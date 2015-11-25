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
import org.no_ip.stieflo.entities.Auteur;
import org.no_ip.stieflo.exceptions.HeeftNogStripsException;
import org.no_ip.stieflo.services.AuteurServiceInterface;
import org.no_ip.stieflo.web.forms.ZoekenForm;

@Controller 
@RequestMapping(path = "/auteurs", produces = MediaType.TEXT_HTML_VALUE)
class AuteurController { 
	private static final String AUTEURS_VIEW = "auteurs/auteurs";
	private static final String TOEVOEGEN_VIEW = "auteurs/toevoegen";
	private static final String REDIRECT_NA_TOEVOEGEN = "redirect:/auteurs";
	private static final String REDIRECT_AUTEUR_NIET_GEVONDEN = "redirect:/auteurs";
	private static final String REDIRECT_AUTEUR_HEEFT_NOG_STRIPS = "redirect:/auteurs";
	private static final String REDIRECT_NA_VERWIJDEREN = "redirect:/auteurs/{id}/verwijderd";
	private static final String VERWIJDERD_VIEW = "auteurs/verwijderd";
	private static final String WIJZIGEN_VIEW = "auteurs/wijzigen";
	private static final String REDIRECT_NA_WIJZIGEN = "redirect:/auteurs";
		
	private static final Logger logger = Logger.getLogger(AuteurController.class.getName());
	private final AuteurServiceInterface auteurService; 
	
	@Autowired 
	AuteurController(AuteurServiceInterface auteurService) {  
		this.auteurService = auteurService;
	} 
	
	/** --------------------- Auteurs zoeken en bekijken --------------------- **/
	@RequestMapping(method = RequestMethod.GET) 
	ModelAndView ZoekenForm() {
		ModelAndView modelAndView = new ModelAndView(AUTEURS_VIEW);
		modelAndView.addObject("auteurs", auteurService.findAll());
		modelAndView.addObject(new ZoekenForm());
		return modelAndView; 
	}
	
	@RequestMapping(method = RequestMethod.GET, params = "zoekTerm") 
	ModelAndView auteurZoeken(@Valid ZoekenForm form, BindingResult bindingResult) {   
		ModelAndView modelAndView = new ModelAndView(AUTEURS_VIEW);
		if ( ! bindingResult.hasErrors()) {
		    List<Auteur> auteurs = new ArrayList<>(auteurService.findInNaam(form.getZoekTerm()));
			if (auteurs.isEmpty()) {
		    	modelAndView.addObject("nietsgevonden", "De zoekterm werd helaas niet terug gevonden");
		    	modelAndView.addObject("auteurs", auteurService.findAll());
		    } else {
		    	modelAndView.addObject("auteurs", auteurs);
		    }
		}
		return modelAndView; 
	}
	
	/** -------------------------- Auteur toevoegen -------------------------- **/
	@RequestMapping(path = "toevoegen", method = RequestMethod.GET)
	ModelAndView createForm() {
	  return new ModelAndView(TOEVOEGEN_VIEW, "auteur", new Auteur());
	} 
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Auteur auteur, BindingResult bindingResult) {
	  if (bindingResult.hasErrors()) {
	    return TOEVOEGEN_VIEW;
	  }
	  auteurService.create(auteur);
	  return REDIRECT_NA_TOEVOEGEN;
	}
	
	/** -------------------------- Auteur verwijderen -------------------------- **/
	@RequestMapping(path = "{auteur}/verwijderen", method = RequestMethod.POST) 
	String delete(@PathVariable Auteur auteur, RedirectAttributes redirectAttributes) { 
		if (auteur == null) { 
			return REDIRECT_AUTEUR_NIET_GEVONDEN;
		}
		long id = auteur.getId();
		try {
			auteurService.delete(id);
			redirectAttributes.addAttribute("id", id).addAttribute("naam", auteur.getNaam());
			return REDIRECT_NA_VERWIJDEREN; 
	    } catch (HeeftNogStripsException ex) {
	    	redirectAttributes.addAttribute("id", id).addAttribute("fout", "Auteur heeft nog strips");
	    	return REDIRECT_AUTEUR_HEEFT_NOG_STRIPS;
	    }
	}
		
	@RequestMapping(path = "{id}/verwijderd", method = RequestMethod.GET) 
	ModelAndView deleted(String naam) {
		return new ModelAndView(VERWIJDERD_VIEW, "naam", naam);
	}
	
	/** -------------------------- Auteur wijzigen -------------------------- **/
	@RequestMapping(path ="{auteur}/wijzigen", method = RequestMethod.GET)
	ModelAndView updateForm(@PathVariable Auteur auteur) { 
	  if (auteur == null) {
	    return new ModelAndView(REDIRECT_AUTEUR_NIET_GEVONDEN);
	  }
	  return new ModelAndView(WIJZIGEN_VIEW).addObject(auteur);
	} 	
	
	@RequestMapping(path = "{id}/wijzigen", method = RequestMethod.POST) 
	String update(@Valid Auteur auteur, BindingResult bindingResult) {
	  if (bindingResult.hasErrors()) {
	    return WIJZIGEN_VIEW;
	  }
	  auteurService.update(auteur);
	  return REDIRECT_NA_WIJZIGEN;
	}
	
	/*@InitBinder("auteur") 
	void initBinderAuteur(WebDataBinder binder) {
	  binder.initDirectFieldAccess(); 
	}*/
} 