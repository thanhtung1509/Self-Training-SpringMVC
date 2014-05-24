package com.sprhib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sprhib.model.Team;
import com.sprhib.service.TeamService;

@Controller
@RequestMapping(value="/teamrest")
public class TeamRestController {
	
	@Autowired
	private TeamService teamService;
	
	@RequestMapping(value="/listjson")
	public ModelAndView listOfTeams() {
		ModelAndView modelAndView = new ModelAndView("list-of-teams-json");
		return modelAndView;
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public @ResponseBody Team team(@PathVariable int id) {
		return teamService.getTeam(id);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody String saveTeam(@RequestBody Team team) {
		teamService.addTeam(team);
		return team.getId().toString();
    }
	
	@RequestMapping(value = "/get-all-teams", method = RequestMethod.GET)
    public @ResponseBody Iterable<Team> getAllUsers() {
        Iterable<Team> allTeams; 
        allTeams = teamService.getTeams();
        return allTeams;
    }
}
