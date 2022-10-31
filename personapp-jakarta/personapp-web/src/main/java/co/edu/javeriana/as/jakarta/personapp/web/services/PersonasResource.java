/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package co.edu.javeriana.as.jakarta.personapp.web.services;

import co.edu.javeriana.as.jakarta.personapp.ejb.beans.PersonaFacadeLocal;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.Persona;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

@Path("personas")
@RequestScoped
public class PersonasResource {

    @Context
    private UriInfo context;

    @EJB
    private PersonaFacadeLocal personaFacadeLocal;


    /**
     * Creates a new instance of GenericResource
     */

    public PersonasResource() {
    }

    /**
     * Retrieves representation of an instance of
     * co.edu.javeriana.as.jakarta.personapp.web.serices.GenericResource
     * 
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get() {
        try {
            List<Persona> personas = personaFacadeLocal.findAll();
            String json = new Gson().toJson(personas);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"Respuesta\": \"Error 5\"}";
        }
    }
}
