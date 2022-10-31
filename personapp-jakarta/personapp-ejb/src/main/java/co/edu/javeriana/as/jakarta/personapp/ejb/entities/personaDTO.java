/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.javeriana.as.jakarta.personapp.ejb.entities;

/**
 *
 * @author guti2
 */
public class personaDTO {
    
    int cc;
    
    String nombre;
    
    String apellido;
    
    String genero;

    public personaDTO(int cc, String nombre, String apellido, String genero) {
        this.cc = cc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
    }
    
    
}
