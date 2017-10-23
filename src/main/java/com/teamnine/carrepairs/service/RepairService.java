package com.teamnine.carrepairs.service;

import com.teamnine.carrepairs.domain.Repair;
import com.teamnine.carrepairs.exception.RepairNotFoundException;

import java.util.Date;
import java.util.List;

public interface RepairService {

    List<Repair> findAllRepairs();

    long save(Repair repair);

    Repair findById(Long id) throws RepairNotFoundException;

    void deleteRepair(Long id);

    List<Repair> searchByVehiclePlate(String vehiclePlate);

    List<Repair> searchByAFM(long afm);

    List<Repair> searchByDate(Date start,Date end);

    List<Repair> searchByDate(Date date);

    void updateRepair(Repair repair);

    List<Repair> searchByEmail(String email);
}
