package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

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
public class BidListController {

	@Autowired
    private BidListRepository bidListRepository;
	
	/**
     * Display all bid lists.
     *
     * @param model model to add attributes
     * @param request HTTP request to get the logged user
     * @return the bid list view
     */
    @RequestMapping("/bidList/list")
    public String home(Model model, HttpServletRequest request)
    {

    	model.addAttribute("bidLists", bidListRepository.findAll());
    	model.addAttribute("remoteUser", request.getRemoteUser()); //
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {

    	if (!result.hasErrors()) {
            bidListRepository.save(bid);
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

    	BidList bid = bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
            model.addAttribute("bidList", bid);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {

    	if (result.hasErrors()) {
            bidList.setBidListId(id);
            return "bidList/update";
        }
        bidListRepository.save(bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {

    	BidList bid = bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
            bidListRepository.delete(bid);
        return "redirect:/bidList/list";
    }
}
