package com.teamnine.carrepairs.service;

import com.teamnine.carrepairs.Utilities.Utilities;
import com.teamnine.carrepairs.converter.OwnerConverter;
import com.teamnine.carrepairs.converter.VehicleConverter;
import com.teamnine.carrepairs.domain.Owner;
import com.teamnine.carrepairs.domain.Vehicle;
import com.teamnine.carrepairs.exception.UserNotFoundException;
import com.teamnine.carrepairs.exception.VehicleNotFoundException;
import com.teamnine.carrepairs.model.OwnerForm;
import com.teamnine.carrepairs.model.VehicleForm;
import com.teamnine.carrepairs.repository.VehicleRepository;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public Vehicle searchByPlate(String plate) throws VehicleNotFoundException{
        Vehicle vehicle=vehicleRepository.findByPlate(plate);
        if (vehicle == null) {
            throw new VehicleNotFoundException("vehicle not found");
        }
        return vehicle;
    }

    @Override
    public Set<Vehicle> searchByOwnerAfm(int afm) {
        return vehicleRepository.findByOwnerAfm(afm);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public void insertVehicle(VehicleForm vehicleForm) throws UserNotFoundException {
        Vehicle vehicle= VehicleConverter.buildVehicle(vehicleForm);
        Owner owner= accountService.findOwnerbyAFM(Long.parseLong(vehicleForm.getOwnerAfm()));
        //throws exception
        vehicle.setOwner(owner);
        vehicleRepository.save(vehicle);
    }

    @Override
    public void editVehicle(VehicleForm vehicleForm) {
        Vehicle vehicle=VehicleConverter.buildVehicle(vehicleForm);
        Vehicle persistVehicle=vehicleRepository.findById(vehicle.getId());
        vehicle.setOwner(persistVehicle.getOwner());
        vehicle.setRepairs(persistVehicle.getRepairs());
        vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle findById(long id) throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(id);
        if (vehicle == null) {
            throw new VehicleNotFoundException("vehicle not found");
        }
        return vehicle;
    }

    public List<Vehicle> searchVelicleByPlate(String searchText){
        List<Vehicle> vehicles= new ArrayList<>();
        Vehicle vehicle;
        try {
            vehicle = searchByPlate( searchText);
            vehicles.add(vehicle);
        } catch (VehicleNotFoundException e) {
            e.printStackTrace();
        }
        return vehicles;

    }
    public List<Vehicle> searchVelicleByAfm(String searchText) {
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            vehicles.addAll(accountService.findOwnerbyAFM(Long.parseLong(searchText)).getVehicle());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.delete(id);
    }

    /*public List<> searchVehicleBySearchText(String searchText){

        StringBuilder stringBuilder = new StringBuilder();
        List<VehicleForm> vehicleForms= new ArrayList<>();
        Vehicle vehicle;

        if(Utilities.isPlate(searchText)){

            vehicle=searchByPlate( searchText);
            if(vehicle!=null){
                vehicleForms.clear();
                vehicleForms.add(VehicleConverter.buildVehicleForm( vehicle));
            }
            else{
                stringBuilder.append("Vehicle with plate num: ");
                stringBuilder.append(searchText);
                stringBuilder.append("not found. ");

            }

        }
        else if(Utilities.isAfm(searchText))
        {
           vehicleForms=(List)accountService.findOwnerbyAFM(Long.parseLong(searchText)).getVehicle();
            if(vehicleForms.isEmpty()){
                stringBuilder.append("Vehicle for vat number: ");
                stringBuilder.append(searchText);
                stringBuilder.append(" not found.");

            }

        }
        else{
            stringBuilder.append("Please give a valid email or afm");

        }

        return new Pair< List<VehicleForm>, String >(vehicleForms, stringBuilder.toString());
    }
*/





}
