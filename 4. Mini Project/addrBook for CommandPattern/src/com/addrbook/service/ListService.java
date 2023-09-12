package com.addrbook.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.addrbook.model.AddrDto;

public interface ListService {
	public ArrayList<AddrDto> execute(HttpServletRequest request, HttpServletResponse response);

}
