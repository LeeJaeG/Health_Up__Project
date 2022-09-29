package com.healthup.project.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.healthup.project.service.KakaoAPI;

@Controller
public class MainController {

	KakaoAPI kakaoapi = new KakaoAPI();

	@GetMapping("/")
	public String main1(Model model, HttpSession session) {

		model.addAttribute("userId", session.getAttribute("userId"));

		return "main";
	}

	// 카카오로그인
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam("code") String code, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		// 1. 인증코드 요청
		String accessToken = kakaoapi.getAccessToken(code);

		// System.out.println("======accessToken"+accessToken);

		// 2. 인증코드로 토큰 전달
		HashMap<String, Object> userInfo = kakaoapi.getUserInfo(accessToken);

		System.out.println("login info : " + userInfo.toString());

		if (userInfo.get("email") != null) {
			session.setAttribute("userId", userInfo.get("email"));
			session.setAttribute("accessToken", accessToken);
		}

		System.out.println("----------Token" + accessToken);

		mav.addObject("userId", userInfo.get("email"));

		mav.setViewName("main");
		return mav;
	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		// 원래는 api에서 만들어서 해야했지만 그냥 빼서 로그아웃보내도 돌아가긴함
		// kakaoapi.kakaoLogout((String)session.getAttribute("accessToken"));
		session.removeAttribute("accessToken");
		session.removeAttribute("userId");
		mav.setViewName("main");
		return mav;
	}
}
