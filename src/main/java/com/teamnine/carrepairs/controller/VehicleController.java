package com.teamnine.carrepairs.controller;

import com.teamnine.carrepairs.Utilities.Utilities;
import com.teamnine.carrepairs.converter.VehicleConverter;
import com.teamnine.carrepairs.domain.Vehicle;
import com.teamnine.carrepairs.exception.UserNotFoundException;
import com.teamnine.carrepairs.exception.VehicleNotFoundException;
import com.teamnine.carrepairs.model.SearchForm;
import com.teamnine.carrepairs.model.VehicleForm;
import com.teamnine.carrepairs.service.VehicleService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VehicleController {

    private static final String VEHICLE_LIST="vehicles";
    private static final String VEHICLE_FORM="vehicleForm";
    private static final String SEARCH_VEHICLE="searchVehicle";
    private static final String ERROR_VEHICLE="error";
    private static final String ERROR_USER="errorUser";
    private static final String DELETE_EXCEPTION ="delete" ;

    @Autowired
    private VehicleService vehicleService;

    List<Vehicle> vehicles;
    @RequestMapping(value = "/admin/vehicles",method = RequestMethod.GET)
    public String vehicleHome(Model model,@ModelAttribute(DELETE_EXCEPTION) String exception){
        if (model != null) {
            model.addAttribute(DELETE_EXCEPTION,exception);
        }
        model.addAttribute(VEHICLE_LIST,vehicleService.findAll());
        model.addAttribute(SEARCH_VEHICLE,new SearchForm());

        return "vehicle";
    }

    @RequestMapping(value="/admin/vehicles/new",method = RequestMethod.GET)
    public String addVehicle(Model model){
        model.addAttribute(VEHICLE_FORM,new VehicleForm());
        return "vehicleForm";
    }

    @RequestMapping(value="/admin/vehicles/new",method = RequestMethod.POST)
    public String addVehicle(Model model, @Valid @ModelAttribute(VEHICLE_FORM)
            VehicleForm vehicleForm, BindingResult bindingResult) throws UserNotFoundException {
        if (bindingResult.hasErrors()){
            model.addAttribute(VEHICLE_FORM,vehicleForm);
            return "vehicleForm";
        }
        vehicleService.insertVehicle(vehicleForm);
        return "redirect:/admin/vehicles";
    }

    @RequestMapping(value = "/admin/vehicles/edit",method = RequestMethod.GET)
    public String editVehicle(Model model,@RequestParam(name = "id",required = true) long id) throws VehicleNotFoundException {
        Vehicle vehicle=vehicleService.findById(id);
        model.addAttribute(VEHICLE_FORM, VehicleConverter.buildVehicleForm(vehicle));
        return "editVehicle";
    }

    @RequestMapping(value="admin/vehicles/edit",method = RequestMethod.POST)
    public String editVehicle(@Valid @ModelAttribute(VEHICLE_FORM) VehicleForm vehicleForm,BindingResult bindingResult,Model model,
                            @RequestParam(name = "id",required = true) long id){

        vehicleForm.setVehicleID(String.valueOf(id));
        if (bindingResult.hasErrors()){
            model.addAttribute(VEHICLE_FORM,vehicleForm);
            return "editVehicle";
        }

        vehicleService.editVehicle(vehicleForm);
        return "redirect:/admin/vehicles";
    }
    @RequestMapping(value = "admin/vehicles/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable String id) {
        vehicleService.deleteVehicle(Long.parseLong(id));
        return "redirect:/admin/vehicles";
    }

    @RequestMapping(value = "admin/vehicle/search", method = RequestMethod.GET)
    public String search(Model model, @ModelAttribute(SEARCH_VEHICLE) SearchForm searchForm) {
        String message="";
        String searchText = searchForm.getSearchText().replaceAll(" ", "");

        if (Utilities.isPlate(searchText)) {
            vehicles = vehicleService.searchVelicleByPlate(searchText);
            if (vehicles.isEmpty()) {
                message="Vehicle with plate num: ".concat(searchText).concat(" not found. ");

            }
        }
        else if (Utilities.isAfm(searchText)) {
            vehicles = vehicleService.searchVelicleByAfm(searchText);
            if (vehicles.isEmpty()) {
                message="Vehicle of owner with vat num: ".concat(searchText).concat(" not found. ");
            }
        }
        else {
            List<Vehicle> vehicles= new ArrayList<>();
            message="Please give a valid VAT num or Email ";}

        model.addAttribute(VEHICLE_LIST,vehicles);
        model.addAttribute(SEARCH_VEHICLE,new SearchForm());
        model.addAttribute("message", message);

        return "vehicle";
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public String VehicleException(Model model){
        model.addAttribute(ERROR_VEHICLE,"Vehicle Not found");
        return "editVehicle";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String UserException(Model model){
        model.addAttribute(ERROR_USER,"There is no user with this afm");
        model.addAttribute(VEHICLE_FORM,new VehicleForm());
        return "vehicleForm";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String DeleteException(Model model, RedirectAttributes redirectAttributes){
        //model.addAttribute(DELETE_EXCEPTION,"You cannot delete this user ");
        redirectAttributes.addFlashAttribute(DELETE_EXCEPTION,"You cannot delete this vehicle");
        return "redirect:/admin/owners";
    }
}
