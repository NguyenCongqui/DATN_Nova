package com.example.duantotnghiepgiaythethaonova.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public interface TypeHelperService {

	Date convertObjectTypeDate(Date input);

	Instant convertObjectTypeInstant(Instant input);

	BigDecimal convertObjectTypeBigDecimal(BigDecimal input);

	String convertObjectTypeString(String input);

	List<Integer> convertObjectTypeListInteger(List<Integer> input);

	int convertObjectTypeListInt(Integer input);

	List<Boolean> convertObjectTypeListBoolean(List<Boolean> input);

}
