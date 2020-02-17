package com.sda.travel_agency.logic.export;

import com.sda.travel_agency.logic.dto.TripDTO;

import java.util.List;

public interface Export<T> {

    public String export(List<T> list, String path);
}
