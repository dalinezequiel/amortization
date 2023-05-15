package com.simor.controller;

import java.io.IOException;
import java.sql.Date;

import com.simor.dao.HolidayDAO;
import com.simor.model.FeriadoModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HolidayCom {
	private static FeriadoModel defaul = null;

	public HolidayCom() {
		super();
	}

	public static FeriadoModel sys_hol(String date, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("edit") != null) {
			return HolidayDAO.holidayByDate(Date.valueOf(date));
		}
		defaul = new FeriadoModel();
		defaul.setDataFeriado(Date.valueOf("2000-01-01"));
		return defaul;
	}

	public static boolean sys_del(String date, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("edit") != null) {
			if (date != null && !date.isEmpty()) {
				return HolidayDAO.deleteByDate(Date.valueOf(date));
			} else {
				return HolidayDAO.deleteByDate(Date.valueOf("2000-01-01"));
			}
		}
		return HolidayDAO.deleteByDate(Date.valueOf("2000-01-01"));
	}
}
