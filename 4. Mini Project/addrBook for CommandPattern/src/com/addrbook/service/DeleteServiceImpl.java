package com.addrbook.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.addrbook.model.AddrDao;

public class DeleteServiceImpl implements DeleteService{
	private AddrDao addrDao;
	
	public DeleteServiceImpl() {
		addrDao = AddrDao.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int delId = (int)request.getAttribute("delId");
		addrDao.deleteAddr(delId);
		
	}
	


}
