package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * Controller for handling CRUD operations for this entity.
 * Provides endpoints for listing, adding, updating, and deleting bids.
 */
@Controller
public class CurveController {

	@Autowired
    private CurvePointRepository curvePointRepository;
	
	/**
     * Display all curvepoint lists.
     *
     * @param model model to add attributes
     * @param request HTTP request to get the logged user
     * @return the curvepoint list view
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model, HttpServletRequest request)
    {
    	model.addAttribute("curvePoints", curvePointRepository.findAll());
    	model.addAttribute("remoteUser", request.getRemoteUser());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
       
    	if (!result.hasErrors()) {
            curvePointRepository.save(curvePoint);
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

    	CurvePoint curvePoint = curvePointRepository.findById(id)
    	        .orElseThrow(() -> new IllegalArgumentException("Invalid CurvePoint Id:" + id));
    	    model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {

    	if (result.hasErrors()) {
            curvePoint.setId(id);
            return "curvePoint/update";
        }

        curvePointRepository.save(curvePoint);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {

    	CurvePoint curvePoint = curvePointRepository.findById(id)
    	        .orElseThrow(() -> new IllegalArgumentException("Invalid CurvePoint Id:" + id));
    	    curvePointRepository.delete(curvePoint);
        return "redirect:/curvePoint/list";
    }
}
