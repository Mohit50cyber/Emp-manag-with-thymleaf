package com.exam.emp.controller;

import com.exam.emp.entity.Employee;
import com.exam.emp.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EmpController {

    @Autowired
    private EmpService service;


    @GetMapping("/")
    public String home(Model m){
        List<Employee> emp=service.getAllEmployee();
        m.addAttribute("emp",emp);
        return "index";
    }
    @GetMapping("/addEmp")
    public String addEmpForm(){

        return "add_emp";
    }
    @PostMapping("/register")
    public String empRegister(@ModelAttribute Employee employee, HttpSession session){
        System.out.println(employee);
        service.addEmp(employee);
        session.setAttribute("msg","Employee added successfully bhaii.........");
        return"add_emp";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id,Model m){
        Employee e =service.getEmpById(id);
        m.addAttribute("emp",e);
        return "edit";
    }

    @PostMapping("/update")
    public String updateEmp(@ModelAttribute Employee e,HttpSession session){
        service.addEmp(e);
        session.setAttribute("msg","Employee data updated sucessfully");
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable int id,HttpSession session){
        service.delete(id);
        session.setAttribute("msg","Employee data deleted sucessfully , now party................");
        return "redirect:/";
    }


}
