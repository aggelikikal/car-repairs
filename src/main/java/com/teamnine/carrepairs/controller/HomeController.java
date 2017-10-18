package com.teamnine.carrepairs.controller;


import com.teamnine.carrepairs.domain.Owner;
import com.teamnine.carrepairs.repository.UserRepository;
import com.teamnine.carrepairs.service.RepairService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;


@Controller
public class HomeController {
    private  final static org.slf4j.Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private RepairService repairService;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value= "/admin/home", method = RequestMethod.GET)
    public String repairs(Model model){
       /* Owner owner =userRepository.findByEmailAndPassword("apo.mantzios@gmail.com ","12345");
        userRepository.delete(owner);*/
        model.addAttribute("repairs",repairService.findAllRepairs());
        return "home";
    }

}