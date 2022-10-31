/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.javeriana.as.jakarta.personapp.ejb.entities;

import java.sql.Date;

/**
 *
 * @author guti2
 */
public class estudiosDTO {
    
    int idProf;
    int ccPer;
    Date fecha;
    String  univer;

    public estudiosDTO(int idProf, int ccPer, Date fecha, String univer) {
        this.idProf = idProf;
        this.ccPer = ccPer;
        this.fecha = fecha;
        this.univer = univer;
    }
    
            
    
}
