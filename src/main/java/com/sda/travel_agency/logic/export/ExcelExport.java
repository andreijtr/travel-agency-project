package com.sda.travel_agency.logic.export;

import com.sda.travel_agency.logic.dto.TripDTO;

import java.util.List;

public class ExcelExport<T> implements Export<T>{

    @Override
    public String export(List<T> list, String path) {
        return null;
    }
}
