package com.simor.controller;

import com.simor.dao.AccountDAO;

public class Profile {
	
	public Profile() {
		super();
	}

	// USUARIO DA APLICAÇÃO
	public static String sys_profile() {
		return AccountDAO.userProfile().getProfile();
	}
}
