package com.sda.travel_agency.logic.export;

import com.sda.travel_agency.logic.dto.HotelDTO;
import com.sda.travel_agency.util.Constants;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelExportHotel {

    public String export(List<HotelDTO> hotelDTOList, String path) {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("hotels");
        //Create header
        int rowNum = 0;
        Row header = sheet.createRow(rowNum++);
        //header style
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        int headerCellNo = 0;
        //header cells
        Cell nameCell = header.createCell(headerCellNo++);
        nameCell.setCellValue(Constants.HOTEL_NAME);
        Cell standardCell = header.createCell(headerCellNo++);
        standardCell.setCellValue(Constants.HOTEL_STANDARD);
        Cell descriptionCell = header.createCell(headerCellNo++);
        descriptionCell.setCellValue(Constants.HOTEL_DESCRIPTION);
        Cell cityCell = header.createCell(headerCellNo++);
        cityCell.setCellValue(Constants.CITY);
        Cell countryCell = header.createCell(headerCellNo++);
        countryCell.setCellValue(Constants.COUNTRY);
        Cell continentCell = header.createCell(headerCellNo++);
        continentCell.setCellValue(Constants.CONTINENT);
        //Iterate over data and write to sheet
        for (HotelDTO hotelDTO : hotelDTOList) {
            Row row = sheet.createRow(rowNum++);
            int cellNum = 0;
            Cell name = row.createCell(cellNum++);
            name.setCellValue(hotelDTO.getName());
            Cell standard = row.createCell(cellNum++);
            standard.setCellValue(hotelDTO.getStandard());
            Cell description = row.createCell(cellNum++);
            description.setCellValue(hotelDTO.getDescription());
            Cell city = row.createCell(cellNum++);
            city.setCellValue(hotelDTO.getCityDTO().getName());
            Cell country = row.createCell(cellNum++);
            country.setCellValue(hotelDTO.getCityDTO().getCountryDTO().getName());
            Cell continent = row.createCell(cellNum++);
            continent.setCellValue(hotelDTO.getCityDTO().getCountryDTO().getContinentDTO().getName());
        }
        //write to excel file
        try {
            File file = new File(path + "/hotels.xlsx");
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
            return Constants.EXPORT_SUCCESSFULLY;
        } catch (FileNotFoundException fileEx) {
            return Constants.EXPORT_FAIL_NO_FILE;
        } catch (IOException ioEx) {
            return Constants.EXPORT_FAIL_DURING_WRITE;
        }
    }
}
