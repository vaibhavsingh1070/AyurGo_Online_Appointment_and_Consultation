package com.AyurGo.controller;

import java.security.Principal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyurGo.Exception.UserNotFoundException;
import com.AyurGo.entity.User;
import com.AyurGo.entity.appointment;
import com.AyurGo.repository.UserRepo;
import com.AyurGo.service.AppointmentServiceImpl;
import com.AyurGo.service.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private AppointmentServiceImpl appointmentServiceImpl;

	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			User user = userRepo.findByEmail(email);
			m.addAttribute("user", user);
		}
	}

	@GetMapping("/dashboard/adminprofile")
	public String profile() {
		return "adminprofile";
	}

	@GetMapping("/dashboard")
	public String admin() {
		return "admintest";
	}

	
	 @GetMapping("/dashboard/userlist")
	    public String showUserList(Model model) {
	        List<User> listUsers = userServiceImpl.listAll();
	        model.addAttribute("listUsers", listUsers);
	        return "userlist";
	   }
	 
	 @GetMapping("/dashboard/appointmentlist")
	    public String showAppoitmnetList(Model model) {
	        List<appointment> listappointment = appointmentServiceImpl.listAll();
	        model.addAttribute("listappointment", listappointment);
	        return "appointmentlist";
	   }
	 
	  @GetMapping("/dashboard/appointmentlist/delete/{id}")
	    public String deleteAppointment(@PathVariable("id") Integer id, RedirectAttributes ra) {
	        try {
	        	appointmentServiceImpl.delete(id);
	            ra.addFlashAttribute("message", "The Appointment has been deleted.");
	        } catch (UserNotFoundException e) {
	            ra.addFlashAttribute("message", e.getMessage());
	        }
	        return "redirect:/admin/dashboard/appointmentlist";
	    }

}
