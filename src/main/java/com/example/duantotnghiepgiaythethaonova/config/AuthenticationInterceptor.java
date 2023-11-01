//package com.example.duantotnghiepgiaythethaonova.config;
//
//import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung;
//import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung_VaiTro;
//import com.example.duantotnghiepgiaythethaonova.entity.VaiTro;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class AuthenticationInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        VaiTro vaiTro = (VaiTro) request.getSession().getAttribute("user");
//        if (vaiTro.getListNguoiDungVaiTro() != null) {
//            response.setStatus(403);
//            response.sendRedirect(request.getContextPath() + "/403");
//            return false;
//        }
//        return true;
//
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//}
