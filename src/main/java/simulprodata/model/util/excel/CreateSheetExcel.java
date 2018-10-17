/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulprodata.model.util.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import simulprodata.model.logic.Persona;

/**
 *
 * @author Ariel Arnedo
 */
public class CreateSheetExcel {
        
    public static boolean createExcel(List<Persona> list, File file) throws IOException{
        FileOutputStream out;
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            createSheetHours(workbook, list);
            createSheetTime(workbook, list);
            createSheetQueueTime(workbook, list);
            createSheetProducts(workbook, list);
//            out = new FileOutputStream(file);
//            workbook.write(out);
        }
//        out.close();
        return true; 
    }
    
    //------------------------------------------------------------------------------------------------------------------------------------
    
    private static void createSheetHours(HSSFWorkbook workbook, List<Persona> personas){
        
        HSSFSheet sheetHours = workbook.createSheet("Horas de LLegadas y Salida");
        String cabecera[] = {"N°","Sexo","Tipo","Hora de llegada a Caja","Hora de salida de Caja","Hora de finalizada la Compra"};
    
        createTitle(workbook, sheetHours, cabecera);
        CellStyle cellStyle = createCellStyle(workbook, 3);
        Row row;
        
        for (int i = 1; i < personas.size()+1; i++) {
            row = sheetHours.createRow(i);
            for (int j = 0; j < cabecera.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(cellStyle);
                switch(j){
                    case 0:{
                        cell.setCellValue((i));
                        break;
                    }
                    case 1:{
                        cell.setCellValue(personas.get(i-1).getSexo());
                        break;
                    }
                    case 2:{
                        cell.setCellValue(personas.get(i-1).getTipo());
                        break;
                    }
                    case 3:{
                        cell.setCellValue(personas.get(i-1).getTiempos().getMomentoDeRecepcionEnCaja().toString());
                        break;
                    }
                    case 4:{
                        cell.setCellValue(personas.get(i-1).getTiempos().getMomentoDeRecepcionEnEspera().toString());
                        break;
                    }
                    case 5:{
                        cell.setCellValue(personas.get(i-1).getTiempos().getMomentoDeSalida().toString());
                        break;
                    }
                }
                sheetHours.autoSizeColumn(j);
            }
        }
    }
    
    private static void createSheetTime(HSSFWorkbook workbook, List<Persona> personas){
        HSSFSheet sheetTimes = workbook.createSheet("Tiempos entre llegadas");
        String cabecera[] = {"N°","Sexo","Tipo","Tiempo entre Llegadas a Caja","Tiempo de Servicio en caja","Tiempo de Servicio en Despacho"};
      
        createTitle(workbook, sheetTimes, cabecera);
        
        CellStyle cellStyle = createCellStyle(workbook, 3);
        
        Row row;
        
        double acum1,acum2,acum3;
            acum1=acum2=acum3=0;
        for (int i = 1; i < personas.size()+1; i++) {
            
            row = sheetTimes.createRow(i);
            for (int j = 0; j < cabecera.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(cellStyle);
                switch(j){
                    case 0:{
                        cell.setCellValue((i));
                        break;
                    }
                    case 1:{
                        cell.setCellValue(personas.get(i-1).getSexo());
                        break;
                    }
                    case 2:{
                        cell.setCellValue(personas.get(i-1).getTipo());
                        break;
                    }
                    case 3:{
                        if(i ==  1){
                           cell.setCellValue(0);
                        }else{
                            acum1+=personas.get(i-1).getTiempos().getMomentoDeRecepcionEnCaja().getDiferenciaEnMinutos(
                                    personas.get(i-2).getTiempos().getMomentoDeRecepcionEnCaja());
                            cell.setCellValue(personas.get(i-1).getTiempos().getMomentoDeRecepcionEnCaja().getDiferenciaEnMinutos(
                                    personas.get(i-2).getTiempos().getMomentoDeRecepcionEnCaja()
                            ));
                        }
                        break;
                    }
                    case 4:{
                        if(i ==  1){
                           cell.setCellValue(0);
                        }else{
                            acum2+=personas.get(i-1).getTiempos().getMomentoDeRecepcionEnEspera().getDiferenciaEnMinutos(
                                    personas.get(i-2).getTiempos().getMomentoDeRecepcionEnEspera());
                            cell.setCellValue(personas.get(i-1).getTiempos().getMomentoDeRecepcionEnEspera().getDiferenciaEnMinutos(
                                    personas.get(i-2).getTiempos().getMomentoDeRecepcionEnEspera()
                            ));
                        }
                        break;
                    }
                    case 5:{
                        if(i ==  1){
                           cell.setCellValue(0);
                        }else{
                            acum3+=personas.get(i-1).getTiempos().getMomentoDeSalida().getDiferenciaEnMinutos(
                                    personas.get(i-2).getTiempos().getMomentoDeSalida());
                            cell.setCellValue(personas.get(i-1).getTiempos().getMomentoDeSalida().getDiferenciaEnMinutos(
                                    personas.get(i-2).getTiempos().getMomentoDeSalida()
                            ));
                        }
                        break;
                    }    
                }
                sheetTimes.autoSizeColumn(j);
            }
        }
        System.out.println("Tiempo Aculumado entre Llegadas a Caja\tTiempo Aculumado de Servicio en caja\tTiempo Aculumado de Servicio en Despacho");
        System.out.println(acum1+"\t"+acum2+"\t"+acum3+"\t\t"+personas.size()+"\n");
        System.out.println("Promedio entre Llegadas a Caja\tPromedio de Servicio en caja\tPromedio de Servicio en Despacho");
        System.out.println(acum1/(double)personas.size()+"\t"+acum2/(double)personas.size()+"\t"+acum3/(double)personas.size());
    }
   
    private static void createSheetQueueTime(HSSFWorkbook workbook, List<Persona> personas){
        HSSFSheet sheetHours = workbook.createSheet("Tiempos en Colas");
        String cabecera[] = {"N°","Sexo","Tipo","Tiempo en cola de Caja","Tiempo en Cola de Servicio"};
    
        createTitle(workbook, sheetHours, cabecera);
        CellStyle cellStyle = createCellStyle(workbook, 3);
        Row row;
        
        for (int i = 1; i < personas.size()+1; i++) {
            row = sheetHours.createRow(i);
            for (int j = 0; j < cabecera.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(cellStyle);
                switch(j){
                    case 0:{
                        cell.setCellValue((i));
                        break;
                    }
                    case 1:{
                        cell.setCellValue(personas.get(i-1).getSexo());
                        break;
                    }
                    case 2:{
                        cell.setCellValue(personas.get(i-1).getTipo());
                        break;
                    }
                    case 3:{
                        cell.setCellValue(personas.get(i-1).getTiempos().getTiempoEnCaja());
                        break;
                    }
                    case 4:{
                        cell.setCellValue(personas.get(i-1).getTiempos().getTiempoDeAtencion());
                        break;
                    }
                }
                sheetHours.autoSizeColumn(j);
            }
        }
    }
    
    private static void createSheetProducts(HSSFWorkbook workbook, List<Persona> personas){
        HSSFSheet sheetProducts = workbook.createSheet("Productos Comprados");
        String cabecera[] = {"N°","Sexo","Tipo","Productos Comprados"};
      
        createTitle(workbook, sheetProducts, cabecera);
        
        Row row;
        CellStyle cellStyle = createCellStyle(workbook, 3);
        
        for (int i = 1; i < personas.size()+1; i++) {
            row = sheetProducts.createRow(i);
            for (int j = 0; j < cabecera.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(cellStyle);
                switch(j){
                    case 0:{
                        cell.setCellValue((i));
                        break;
                    }
                    case 1:{
                        cell.setCellValue(personas.get(i-1).getSexo());
                        break;
                    }
                    case 2:{
                        cell.setCellValue(personas.get(i-1).getTipo());
                        break;
                    }
                    case 3:{
                        StringBuilder sb = new StringBuilder();
                        for (int k = 0; k < personas.get(i-1).getCategorias().size() ; k++) {
                            sb.append(personas.get(i-1).getCategorias().get(k));
                            if(k != personas.get(i-1).getCategorias().size() - 1)
                                sb.append(", ");
                        }
                        cell.setCellValue(sb.toString());
                        break;
                    } 
                }
                sheetProducts.autoSizeColumn(j);
            }
        }
    }
    
    private static void createTitle(HSSFWorkbook workbook, HSSFSheet sheet, String[] titles){
        Row row = sheet.createRow(0);
        CellStyle cellStyle = createCellStyle(workbook, 0);
        int lastRow = 0;
        for (int i = 0 ; i < titles.length ; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(titles[i]);
            sheet.autoSizeColumn(i);
        }
    }
    
    private static CellStyle createCellStyle(HSSFWorkbook workbook, int type){
        CellStyle style =  workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        switch(type){
            case 0:{
                 style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND );
                 style.setFillForegroundColor(new HSSFColor.SKY_BLUE().getIndex());
                 
                break;
            }
            case 1:{
                
                break;
            }
            case 2:{
                
                break;
            }
        }
        return style;
    }    
}
