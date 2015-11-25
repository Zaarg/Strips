package org.no_ip.stieflo.web;

import java.time.LocalDateTime;
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
import org.no_ip.stieflo.entities.Strip;
import org.no_ip.stieflo.services.StripServiceInterface;
import org.no_ip.stieflo.web.forms.ZoekenForm;

@Controller 
@RequestMapping(path = "/strips", produces = MediaType.TEXT_HTML_VALUE)
class StripController { 
	private static final String STRIPS_VIEW = "strips/strips";
	private static final String STRIP_VIEW = "strips/strip";
	private static final String TOEVOEGEN_VIEW = "strips/toevoegen";
	private static final String REDIRECT_NA_TOEVOEGEN = "redirect:/strips";
	private static final String REDIRECT_STRIP_NIET_GEVONDEN = "redirect:/strips";
	private static final String REDIRECT_NA_VERWIJDEREN = "redirect:/strips/{id}/verwijderd";
	private static final String VERWIJDERD_VIEW = "strips/verwijderd";
	private static final String WIJZIGEN_VIEW = "strips/wijzigen";
	private static final String REDIRECT_NA_WIJZIGEN = "redirect:/strips";
	
	private static final Logger logger = Logger.getLogger(StripController.class.getName());
	private final StripServiceInterface stripService; 
	
	@Autowired 
	StripController(StripServiceInterface stripService) {  // Spring injecteert de parameter reeksService met de bean die de interface
		this.stripService = stripService;			// ReeksService implementeert: ReeksServiceImpl
	} 
	
	/** -------------------------- Strips bekijken en zoeken -------------------------- **/
	@RequestMapping(method = RequestMethod.GET) 
	ModelAndView reeksZoekenForm() {
		ModelAndView modelAndView = new ModelAndView(STRIPS_VIEW);
		modelAndView.addObject("strips", stripService.findAll());
		modelAndView.addObject(new ZoekenForm());
		return modelAndView; 
	}
	
	@RequestMapping(method = RequestMethod.GET, params = "zoekTerm") 
	ModelAndView reeksZoeken(@Valid ZoekenForm form, BindingResult bindingResult) {   
		ModelAndView modelAndView = new ModelAndView(STRIPS_VIEW);
		if ( ! bindingResult.hasErrors()) {
		    List<Strip> strips = new ArrayList<>(stripService.findInTitel(form.getZoekTerm()));
			if (strips.isEmpty()) {
		    	modelAndView.addObject("nietsgevonden", "De zoekterm werd helaas niet terug gevonden");
		    	modelAndView.addObject("strips", stripService.findAll());
		    } else {
		    	modelAndView.addObject("strips", strips);
		    }
		}
		return modelAndView; 
	}
	
	/** -------------------------- Strip Info bekijken  -------------------------- **/
	@RequestMapping(path = "{strip}", method = RequestMethod.GET) 
	ModelAndView read(@PathVariable Strip strip) {   
		ModelAndView modelAndView = new ModelAndView(STRIP_VIEW);
		if (strip != null) {
			modelAndView.addObject(strip);
		}
		return modelAndView;
	}
	
	/** -------------------------- Strip toevoegen -------------------------- **/
	@RequestMapping(path = "toevoegen", method = RequestMethod.GET)
	ModelAndView createForm() {
		return new ModelAndView(TOEVOEGEN_VIEW, "strip", new Strip())
			  .addObject("reeksen", stripService.findAllReeksen())
			  .addObject("auteurs", stripService.findAllAuteurs())
			  .addObject("vertalers", stripService.findAllVertalers())
			  .addObject("uitgevers", stripService.findAllUitgevers());
	}
	
	@RequestMapping(path = "toevoegen/clonedfrom{strip}", method = RequestMethod.GET)
	ModelAndView createBasedonPrevious(@PathVariable Strip strip) {
		ModelAndView modelAndView = new ModelAndView(TOEVOEGEN_VIEW);
		modelAndView.addObject("reeksen", stripService.findAllReeksen())
		  			.addObject("auteurs", stripService.findAllAuteurs())
		  			.addObject("vertalers", stripService.findAllVertalers())
		  			.addObject("uitgevers", stripService.findAllUitgevers());		
		if ( strip != null) {
			strip.setId(0);
			strip.setAlbumnr(strip.getAlbumnr()+1);
			strip.setTitel("");
			LocalDateTime now = LocalDateTime.now();
			strip.setJaar(Long.valueOf(now.getYear()));
			int length = strip.getIsbntext().length(); 
			if (length > 5) strip.setIsbntext(strip.getIsbntext().substring(0, length - 5));
			modelAndView.addObject(strip);
			System.err.println("jaar is "+strip.getJaar());
		} else {
			modelAndView.addObject(new Strip());
		}
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Strip strip, BindingResult bindingResult) {
	  if (bindingResult.hasErrors()) {
	    return TOEVOEGEN_VIEW;
	  }
	  stripService.create(strip);
	  return REDIRECT_NA_TOEVOEGEN;
	}
	
	/** -------------------------- Strip verwijderen -------------------------- **/
	@RequestMapping(path = "{strip}/verwijderen", method = RequestMethod.POST) 
	String delete(@PathVariable Strip strip, RedirectAttributes redirectAttributes) { 
		if (strip == null) { 
			return REDIRECT_STRIP_NIET_GEVONDEN;
		}
		long id = strip.getId();
		try {
			stripService.delete(id);
			redirectAttributes.addAttribute("id", id).addAttribute("titel", strip.getTitel());
			return REDIRECT_NA_VERWIJDEREN; 
	    } catch (Exception ex) {
	    	redirectAttributes.addAttribute("id", id).addAttribute("fout", "Strip verwijderen probleem");
	    	return REDIRECT_NA_VERWIJDEREN;
	    }
	}
		
	@RequestMapping(path = "{id}/verwijderd", method = RequestMethod.GET) 
	ModelAndView deleted(String titel) {
		return new ModelAndView(VERWIJDERD_VIEW, "titel", titel);
	}
	
	/** ---------------------------- Databinding ---------------------------- **/			
	/*@InitBinder("strip") 
	void initBinderStrip(WebDataBinder binder) {
	  binder.initDirectFieldAccess(); 
	}*/
	
	/** -------------------------- Strip wijzigen -------------------------- **/
	@RequestMapping(path ="{strip}/wijzigen", method = RequestMethod.GET)
	ModelAndView updateForm(@PathVariable Strip strip) { 
	  if (strip == null) {
	    return new ModelAndView(REDIRECT_STRIP_NIET_GEVONDEN);
	  }
	  return new ModelAndView(WIJZIGEN_VIEW).addObject(strip)
			  .addObject("reeksen", stripService.findAllReeksen())
			  .addObject("auteurs", stripService.findAllAuteurs())
			  .addObject("vertalers", stripService.findAllVertalers())
			  .addObject("uitgevers", stripService.findAllUitgevers());
	} 	
	
	@RequestMapping(path = "{id}/wijzigen", method = RequestMethod.POST) 
	String update(@Valid Strip strip, BindingResult bindingResult) {
		logger.info("strip "+strip.getId()+" "+strip.getTitel()+" wijzigen" );
		if (bindingResult.hasErrors()) {
			return WIJZIGEN_VIEW;
		}
		stripService.update(strip);
		return REDIRECT_NA_WIJZIGEN;
	}
	
	/* Vragen voor Frank
	 * 		opgelost: ja - 0. mag strips voor eindwerkje?
	 * 		opgelost: in CSS - 1. layout voor Spring forms? Veel met tabel gedaan...moet met CSS?
	 * 		opgelost automagisch - 2. images onder covers werken niet!
	 * 		opgelost: in service - 3.  meer dao's in service of meer services in controller? meesten suggereren alles in service doen en dao+controller zo simple mogelijk houden.
	 */
	
	
	 
	/*	
	private static final String REDIRECT_URL_HEEFT_NOG_WERKNEMERS = "redirect:/filialen/{id}";
	private static final String PER_POSTCODE_VIEW = "filialen/perpostcode";
	private static final String WIJZIGEN_VIEW = "filialen/wijzigen";
	private static final String REDIRECT_URL_NA_WIJZIGEN = "redirect:/filialen";
	private static final String AFSCHRIJVEN_VIEW = "filialen/afschrijven";
	private static final String REDIRECT_NA_AFSCHRIJVEN = "redirect:/";
	private static final String PER_ID_VIEW = "filialen/perid";*/		 
	
	/*
	@RequestMapping(path ="{reeks}/wijzigen", method = RequestMethod.GET)
	ModelAndView updateForm(@PathVariable Reeks reeks) { 
	  //Reeks reeks = reeksService.read(id);
	  if (reeks == null) {
	    return new ModelAndView(REDIRECT_URL_FILIAAL_NIET_GEVONDEN);
	  }
	  return new ModelAndView(WIJZIGEN_VIEW).addObject(reeks);
	} 	
	
	@RequestMapping(path = "{id}/wijzigen", method = RequestMethod.POST) 
	String update(@Valid Reeks reeks, BindingResult bindingResult) {
	  if (bindingResult.hasErrors()) {
	    return WIJZIGEN_VIEW;
	  }
	  reeksService.update(reeks);
	  return REDIRECT_URL_NA_WIJZIGEN;
	}
	
	@RequestMapping(path = "perpostcode", method = RequestMethod.GET) 
	ModelAndView findByPostcodeReeks() {   
		PostcodeReeks reeks = new PostcodeReeks();
		//reeks.setVanpostcode(1000);
		//reeks.setTotpostcode(9999);
		return new ModelAndView(PER_POSTCODE_VIEW).addObject(reeks); 
	}
	
	@RequestMapping(method=RequestMethod.GET, params={"vanpostcode", "totpostcode"})
	ModelAndView findByPostcodeReeks(@Valid PostcodeReeks reeks, BindingResult bindingResult) {
	  ModelAndView modelAndView = new ModelAndView(PER_POSTCODE_VIEW);
	  if ( ! bindingResult.hasErrors()) {
		  List<Reeks> filialen = reeksService.findByPostcodeReeks(reeks);
		  if (filialen.isEmpty()) {
		    bindingResult.reject("geenFilialen"); 
		  } 
		  else {
		    modelAndView.addObject("filialen", filialen);
		  }
		}
		return modelAndView;
	}  
	
	@RequestMapping(path = "afschrijven", method = RequestMethod.GET) 
	ModelAndView afschrijvenForm() {
		return new ModelAndView(AFSCHRIJVEN_VIEW, "filialen", reeksService.findNietAfgeschreven()).addObject(new AfschrijvenForm());
	} 
	
	@RequestMapping(path = "afschrijven", method = RequestMethod.POST) 
	ModelAndView afschrijven(@Valid AfschrijvenForm afschrijvenForm, BindingResult bindingResult) {
	  if (bindingResult.hasErrors()) { // als de gebruiker geen reeks selecteerde
	    return new ModelAndView(AFSCHRIJVEN_VIEW, "filialen", reeksService.findNietAfgeschreven());
	  }
	  reeksService.afschrijven(afschrijvenForm.getFilialen());
	  return new ModelAndView(REDIRECT_NA_AFSCHRIJVEN);
	} 
		
	@RequestMapping(path = "perid", method = RequestMethod.GET)
	String findById() {
		return PER_ID_VIEW;
	} */
	
} 