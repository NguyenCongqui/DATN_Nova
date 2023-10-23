package com.example.duantotnghiepgiaythethaonova.service.impl;


import com.example.duantotnghiepgiaythethaonova.service.TypeHelperService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TypeHelper implements TypeHelperService {
	
	@Override
	public int convertObjectTypeListInt(Integer input) {
		if(input != null ) {
			int b = input;
			return b;
		}else {
			int newData = -1;
			return newData;
		}
	}
	
	
	@Override
	public List<Integer> convertObjectTypeListInteger(List<Integer> input) {
		if(input != null ) {
			return input;
		}else {
			List<Integer> newData = new ArrayList<Integer>();
			return newData;
		}
	}
	
	
	@Override
	public String convertObjectTypeString(String input) {
		if(input != null) {
			return input;
		}else {
			String newData = "-1";
			return newData;
		}
	}
	
	@Override
	public BigDecimal convertObjectTypeBigDecimal(BigDecimal input) {
		if(input != null) {
			return input;
		}else {
			BigDecimal newData = new BigDecimal(-1L);
			return newData;
		}
	}
	
	
	@Override
	public Instant convertObjectTypeInstant(Instant input) {
		Instant data = input;
		if(data != null) {
			return data;
		}else {
			data = Instant.EPOCH;
			return data;
		}
	}
	
	
	@Override
	public Date convertObjectTypeDate(Date input) {
		Date data = input;
		if(data != null) {
			return data;
		}else {
			Date date = Date.from(Instant.EPOCH);
			data = date;
			return data;
		}
	}
	
	@Override
	public List<Boolean> convertObjectTypeListBoolean(List<Boolean> input){
		if(input != null) {
			return input;
		}else {
			List<Boolean> newData = new ArrayList<Boolean>(Arrays.asList(true, false));
			return newData;
		}	
	}
}
