package com.AyurGo.controller;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyurGo.entity.User;
import com.AyurGo.entity.appointment;
import com.AyurGo.repository.UserRepo;
import com.AyurGo.service.AppointmentService;
import com.AyurGo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;

	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			User user = userRepo.findByEmail(email);
			m.addAttribute("user", user);
		}

	}

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/book")
	public String appointment() {
		return "bookappointment";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}

	/*
	 * @GetMapping("/user/profile") public String profile(Principal p, Model m) {
	 * String email = p.getName(); User user = userRepo.findByEmail(email);
	 * m.addAttribute("user", user); return "profile"; }
	 * 
	 * @GetMapping("/user/home") public String home() { return "home"; }
	 */

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session, Model m) {

		// System.out.println(user);

		User u = userService.saveUser(user);

		if (u != null) {
			// System.out.println("save sucess");
			session.setAttribute("msg", "Register successfully");

		} else {
			// System.out.println("error in server");
			session.setAttribute("msg", "Something wrong server");
		}
		return "redirect:/register";
	}
	
	@PostMapping("/saveAppointment")
	public String saveAppointment(@ModelAttribute appointment apt, HttpSession session, Model m,RedirectAttributes ra) {

		// System.out.println(user);

		appointment a = appointmentService.saveAppointment(apt);
		ra.addFlashAttribute("message", "Appointment Booked successfully !");

		if (a != null) {
			// System.out.println("save sucess");
			session.setAttribute("msgg", "Appointment Booked successfully");

		} else {
			// System.out.println("error in server");
			session.setAttribute("msgg", "Something wrong server");
		}
		return "redirect:/book";
	}
	
	

}
