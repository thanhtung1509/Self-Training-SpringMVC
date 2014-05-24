package com.sprhib.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sprhib.model.Team;
import com.sprhib.service.TeamService;
import com.sprhib.validator.TeamValidator;

@Controller
@RequestMapping(value="/team")
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	 @Autowired
	    private TeamValidator teamValidator;
	     
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(teamValidator);
    }

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addTeamPage() {
		ModelAndView modelAndView = new ModelAndView("add-team-form");
		modelAndView.addObject("team", new Team());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingTeam(@ModelAttribute @Valid Team team, BindingResult result) {
		if (result.hasErrors())
			return new ModelAndView("add-team-form");
		
		ModelAndView modelAndView = new ModelAndView("home");
		teamService.addTeam(team);
		
		String message = "Team was successfully added.";
		modelAndView.addObject("message", message);
		modelAndView.setViewName("home");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/list")
	public ModelAndView listOfTeams() {
		ModelAndView modelAndView = new ModelAndView("list-of-teams");
		
		List<Team> teams = teamService.getTeams();
		modelAndView.addObject("teams", teams);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editTeamPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-team-form");
		Team team = teamService.getTeam(id);
		modelAndView.addObject("team",team);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingTeam(@ModelAttribute @Valid Team team, BindingResult result, @PathVariable Integer id, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors())
			return new ModelAndView("edit-team-form");
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		teamService.updateTeam(team);
		
		String message = "Team was successfully edited.";
		modelAndView.setViewName("home");
		
		redirectAttributes.addFlashAttribute("message", message);
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteTeam(@PathVariable Integer id, final RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("home");
		teamService.deleteTeam(id);
		String message = "Team was successfully deleted.";
		modelAndView.addObject("message", message);
		
		redirectAttributes.addFlashAttribute("message", message);
		return modelAndView;
	}

}
