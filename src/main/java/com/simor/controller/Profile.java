package com.simor.controller;

import com.simor.dao.AccountDAO;
import com.simor.model.ContaModel;

public class Profile {
	
	public Profile() {
		super();
	}

	// USUARIO DA APLICAÇÃO
	public static String sys_profile() {
		return AccountDAO.userProfile().getProfile();
	}
	
	public static ContaModel sys_prof(String profile) {
		return AccountDAO.info_prof(profile);
	}
}
