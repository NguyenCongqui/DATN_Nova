package com.example.duantotnghiepgiaythethaonova.util;

import com.example.duantotnghiepgiaythethaonova.security.NguoiDungDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtil {
	public static NguoiDungDetails getPrincipal() {
		NguoiDungDetails nguoiDung = (NguoiDungDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return nguoiDung;
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getAuthorities(){
		List<String> results = new ArrayList<>();
		
		List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().
				getAuthentication().getAuthorities());
		for(GrantedAuthority authority : authorities) {
			results.add(authority.getAuthority());
		}
		return results ;
	}
}
