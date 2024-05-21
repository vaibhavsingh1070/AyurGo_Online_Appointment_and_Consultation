package com.AyurGo.service;

import java.util.List;

import com.AyurGo.entity.appointment;

public interface AppointmentService {
	
	public appointment saveAppointment(appointment apt);
	
	public void removeSessionMessage();
	
	public List<appointment> listAll();


}
