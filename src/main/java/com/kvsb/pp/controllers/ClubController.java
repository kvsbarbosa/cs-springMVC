package com.kvsb.pp.controllers;

import com.kvsb.pp.dto.ClubDTO;
import com.kvsb.pp.entities.Club;
import com.kvsb.pp.services.ClubService;
import com.kvsb.pp.services.impl.ClubServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClubController {

    @Autowired
    private ClubServiceImpl service;


    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubDTO> clubs = service.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @GetMapping("/clubs/new")
    public String createClubForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }

    @PostMapping("clubs/new")
    public String saveClub(@ModelAttribute("club") Club club) {
        service.save(club);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long clubId, Model model) {
        ClubDTO club = service.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
    }
    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId, @ModelAttribute("club") ClubDTO club) {
        club.setId(clubId);
        service.updateClub(club);
        return "redirect:/clubs";
    }
}