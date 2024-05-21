package com.AyurGo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.AyurGo.Exception.UserNotFoundException;
import com.AyurGo.entity.appointment;
import com.AyurGo.repository.AppointmentRepo;

import jakarta.servlet.http.HttpSession;


@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private AppointmentRepo appointmentRepo;

	@Override
	public appointment saveAppointment(appointment apt) {
		appointment newapt = appointmentRepo.save(apt);
		
		return newapt;
	}

	@Override
	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msgg");
		
	}

	@Override
	public List<appointment> listAll() {
		return (List<appointment>) appointmentRepo.findAll();
	
	}
	
	 public void delete(Integer id) throws UserNotFoundException {
	        Long count = appointmentRepo.countById(id);
	        if (count == null || count == 0) {
	            throw new UserNotFoundException("Could not find any users with ID " + id);
	        }
	        appointmentRepo.deleteById(id);
	    }

}
