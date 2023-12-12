package com.mvc.start;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.practice.vo.Demo;

import pac.service.com.DemoService;

@Controller
public class InitialController {

	@Autowired
	DemoService objDemoService;

	//@PostMapping("/first")
	@RequestMapping(value = "/first", method = RequestMethod.GET)
	public String getHomePage(HttpServletRequest req, HttpServletResponse res) {
		ArrayList objarrayList = null;
		try {
			objarrayList = new ArrayList();
			System.out.println("InitialController");
			objarrayList = objDemoService.getCommentsDetails();
			req.setAttribute("list", objarrayList);
			System.out.println(objarrayList);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return "Home";
	}
	@PostMapping("/save")
	@ResponseBody
	//@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("connection") Demo objFieldsVO, Model m) {
		ArrayList objarrayList = null;
		Demo objDemo = null;
		ModelAndView mv = new ModelAndView();
		try {
			System.out.println("data successfully saving..." + objFieldsVO);
			objDemoService.save(objFieldsVO);
			System.out.println("Fetching all record from Databse");
			objarrayList = new ArrayList();
			objDemo = new Demo();
			objarrayList = objDemoService.getCommentsDetails();
			System.out.println(objarrayList);
			mv.addObject("list", objarrayList);
			// req.setAttribute("list", objarrayList);
			mv.setViewName("Home");

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return mv;
	}

	//@PostMapping("/deleteRecord")
	@ResponseBody
	@DeleteMapping(value = "/deleteRecord/{id}")
	public ModelAndView deleteRecord(HttpServletRequest req, HttpServletResponse res,@PathVariable("id") int id) {
		ArrayList objarrayList = null;
		ModelAndView mv = new ModelAndView();
		try {
			objDemoService.deleterecord(id);
			objarrayList = new ArrayList();
			System.out.println("deleted Record");
			objarrayList = objDemoService.getCommentsDetails();
			mv.addObject("list", objarrayList);
			mv.setViewName("Home");
			
		}catch (Exception e) {
			e.printStackTrace();
		}

		return mv;
	}

}
