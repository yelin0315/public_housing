package com.addrbook.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.addrbook.model.AddrDao;
import com.addrbook.model.AddrDto;

public class UpdateServiceImpl implements UpdateService{
	private AddrDao addrDao;
	
	public UpdateServiceImpl() {
		addrDao = AddrDao.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int updId =(int)request.getAttribute("updId");
		AddrDto updAddr=(AddrDto)request.getAttribute("updAddr");
		addrDao.updateAddr(updId, updAddr);
		
	}
	


}
