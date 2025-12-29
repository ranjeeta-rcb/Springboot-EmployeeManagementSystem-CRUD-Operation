package com.jsp.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.demo.entity.Employee;
import com.jsp.demo.repository.EmpRepository;



@Controller
public class MyController {

	@Autowired
	EmpRepository repository;

	@GetMapping({ "/", "/main" })
	public String loadMain() {
		return "main.html";
	}

	@GetMapping("/add")
	public String loadAdd() {
		return "add.html";
	}

	@PostMapping("/add")
	public String add(Employee employee, RedirectAttributes attributes) {
		repository.save(employee);
		attributes.addFlashAttribute("message", "Record Added Sccess");
		return "redirect:/main";
	}

	@GetMapping("/manage")
	public String manage(ModelMap map, RedirectAttributes attributes) {
		List<Employee> list = repository.findAll();
		if (list.isEmpty()) {
			attributes.addFlashAttribute("message", "No Records Present");
			return "redirect:/main";
		}
		map.put("list", list);
		return "view.html";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, RedirectAttributes attributes) {
		repository.deleteById(id);
		attributes.addFlashAttribute("message", "Record Deleted Success");
		return "redirect:/manage"; 
		}
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, ModelMap map) {
		Employee emp=repository.findById(id).get();
		map.put("emp", emp);
		return "edit"; 
		}
		
		@PostMapping("/update")
		public String update(@ModelAttribute Employee employee, RedirectAttributes attributes) {
			repository.save(employee);
			attributes.addFlashAttribute("message", "Record updated Sccess");
			return "redirect:/manage";
	}
}