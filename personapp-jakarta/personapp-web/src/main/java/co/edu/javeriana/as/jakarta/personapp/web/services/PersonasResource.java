/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package co.edu.javeriana.as.jakarta.personapp.web.services;

import co.edu.javeriana.as.jakarta.personapp.ejb.beans.PersonaFacadeLocal;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.Persona;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.personaDTO;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import java.util.ArrayList;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.PathParam;
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
            List<personaDTO> per = new ArrayList<>();
            for(Persona p : personas)
            {
                personaDTO nuevo = new personaDTO(p.getCc(), p.getNombre(), p.getApellido(), p.getGenero());
                per.add(nuevo);
            }
            String json = new Gson().toJson(per);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"Respuesta\": \"Error 5\"}";
        }
    }

    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getId(@PathParam("id") int id) {
        try {
            System.out.println("entre");
            Persona personas = personaFacadeLocal.find(id);
            System.out.println("sali");
            personaDTO p = new personaDTO(personas.getCc(), personas.getNombre(), personas.getApellido(), personas.getGenero());
            String json = new Gson().toJson(p);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"Respuesta\": \"Error 5\"}";
        }
    }
    
    @PUT
    @Path("{id}/{nom}/{ap}/{gen}")
    @Produces(MediaType.APPLICATION_JSON)
    public String createPersona(@PathParam("id") int id,
            @PathParam("nom") String nom,
            @PathParam("ap") String ap,
            @PathParam("gen") String gen) {
        try {
            System.out.println("entre");
            Persona personas = personaFacadeLocal.find(id);
            personas.setApellido(ap);
            personas.setNombre(nom);
            personas.setGenero(gen);
            personaFacadeLocal.edit(personas);
            personaDTO p = new personaDTO(id, nom, ap, gen);
            String json = new Gson().toJson(p);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"Respuesta\": \"Error 5\"}";
        }
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String createPersona(@PathParam("id") int id) {
        try {
            Persona p = personaFacadeLocal.find(id);
            personaFacadeLocal.remove(p);
            
            String json = new Gson().toJson("eliminado");
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"Respuesta\": \"Error 5\"}";
        }
    }

    
}
