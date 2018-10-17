/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simulprodata.model.util.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import simulprodata.model.logic.Persona;
import simulprodata.model.logic.Tiempo;
import simulprodata.model.logic.TimeSimul;

/**
 *
 * @author Ariel Arnedo
 */
public class FactoryXML {
    
    
    public static void createOuputXML(List<Persona> list, File file) throws Exception{
        Document document = new Document();
        Element rootElement = new Element("SimulProData");
        document.setRootElement(rootElement);
        
        {
            Element dataInf = new Element("DataInfo");
            dataInf.setText("DIA:"+new GregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH));
            rootElement.addContent(dataInf);
        }
        
        Element listPerson = new Element("ListPersons");
        
        for(Persona persona:list){
            Element person = new Element("Persona");
            person.addContent(new Element("Sexo").setText(persona.getSexo()));
            person.addContent(new Element("Tipo").setText(persona.getTipo()));
            if(persona.getCategorias() != null){
                for(String producto : persona.getCategorias())
                    person.addContent(new Element("Cateroria").setText(producto));
            }
            person.addContent(new Element("Momento_de_Recepcion_en_Caja").setText(persona.getTiempos().getMomentoDeRecepcionEnCaja().toString()));
            person.addContent(new Element("Momento_de_Recepcion_en_Espera").setText(persona.getTiempos().getMomentoDeRecepcionEnEspera().toString()));
            person.addContent(new Element("Momento_de_Salida").setText(persona.getTiempos().getMomentoDeSalida().toString()));

            listPerson.addContent(person);
        }
        rootElement.addContent(listPerson);
        XMLOutputter xMLOutputter = new XMLOutputter(Format.getPrettyFormat());
        xMLOutputter.output(document, new FileWriter(file));
    }
    
    public static java.util.List<List<String>> processCategory(File file) throws FileNotFoundException, IOException{
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        
        List<List<String>> list = new ArrayList<>();
        int i  = 0;
        String line;
        do{
            line = bufferedReader.readLine();
            if(line != null && line.compareTo("") != 0){
                List<String> categories = new ArrayList<>();
//                line = line.split(" ")[1].substring(0, line.split(" ")[1].length() - 1);
                line = line.split(" ")[1];
                categories.addAll(Arrays.asList(line.split(",")));
                list.add(categories);
//                System.out.println(list.get(i++));
            }
        }while(line != null && line.compareTo("") != 0);
        System.out.println(list.size());
        return list;
    }
    
//    public static List<Persona> process(File file) throws FileNotFoundException, IOException{
//        List<Persona> list = new ArrayList<>();
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//        String line;
//        do{
//            line = bufferedReader.readLine();
//            if(line != null && line.compareTo("") != 0){
//                Persona persona = new Persona();
//                Tiempo tiempos = new Tiempo();
//                String attributes[] = line.split("-")[1].split(",");
//                for (int i = 0; i < attributes.length; i++) {
//                    switch(i){
//                        case 0:{
//                            persona.setSexo(attributes[i]);
//                            break;
//                        }
//                        case 1:{
//                            persona.setTipo(attributes[i]);
//                            break;
//                        }
//                        case 2:{
//                            tiempos.setMomentoDeRecepcionEnCaja(TimeSimul.obtenerTiempo(attributes[i]));
//                            break;
//                        }
//                        case 3:{
//                            tiempos.setMomentoDeRecepcionEnEspera(TimeSimul.obtenerTiempo(attributes[i]));
//                            break;
//                        }
//                        case 4:{
//                            tiempos.setMomentoDeSalida(TimeSimul.obtenerTiempo(attributes[i]));
//                            break;
//                        }
//                    }
//                }
//                persona.setTiempos(tiempos);
//                list.add(persona);
//           }
//        }while(line != null && line.compareTo("") != 0);
//        //System.out.println(list.size());
//        return list;
//    }
    
     public static List<Persona> cargarListPerson(File file) throws JDOMException, IOException{
        List<Persona> list = new ArrayList<>();
        SAXBuilder sAXBuilder = new SAXBuilder();
        Document document = sAXBuilder.build(file);       
        
        Element root =document.getRootElement();
        
        Element listPersons = root.getChild("ListPersons");
        
        for (Element persons : listPersons.getChildren()) {
            Persona persona = new Persona();
            Tiempo tiempos = new Tiempo();
            List<String> categories = new ArrayList<>();
            for(Element attributes : persons.getChildren()){
                switch(attributes.getName()){
                    case "Sexo":{
                        persona.setSexo(attributes.getValue());
                        break;
                    }
                    case "Tipo":{
                        persona.setTipo(attributes.getValue());
                        break;
                    }
                    case "Momento_de_Recepcion_en_Caja":{
                        tiempos.setMomentoDeRecepcionEnCaja(TimeSimul.obtenerTiempo(attributes.getValue()));
                        break;
                    }
                    case "Momento_de_Recepcion_en_Espera":{
                        tiempos.setMomentoDeRecepcionEnEspera(TimeSimul.obtenerTiempo(attributes.getValue()));
                        break;
                    }
                    case "Momento_de_Salida":{
                        tiempos.setMomentoDeSalida(TimeSimul.obtenerTiempo(attributes.getValue()));
                        break;
                    }
                    case "Cateroria":{
                        categories.add(attributes.getValue());
                        break;
                    }
                }
            }
            persona.setTiempos(tiempos);
            persona.setCategorias(categories);
            list.add(persona);
        }      
        return list;
     }
    
    
}
