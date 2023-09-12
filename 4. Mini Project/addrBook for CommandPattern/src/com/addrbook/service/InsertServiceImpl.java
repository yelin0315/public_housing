package com.addrbook.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.addrbook.model.AddrDao;
import com.addrbook.model.AddrDto;



public class InsertServiceImpl implements InsertService{
	private AddrDao addrDao;
	
	public InsertServiceImpl() {
		addrDao = AddrDao.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AddrDto insAddr = (AddrDto)request.getAttribute("insAddr");
		addrDao.insertAddr(insAddr);
		
	}
	


}
