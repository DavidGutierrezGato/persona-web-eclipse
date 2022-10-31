/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package co.edu.javeriana.as.jakarta.personapp.web.services;

import co.edu.javeriana.as.jakarta.personapp.ejb.beans.ProfesionFacadeLocal;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.Persona;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.Profesion;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.Telefono;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.profesionDTO;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.telefonoDTO;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author guti2
 */
@Path("profesion")
@RequestScoped
public class ProfesionResource {

    @Context
    private UriInfo context;

    @EJB
    private ProfesionFacadeLocal profesionFacade;
    /**
     * Creates a new instance of ProfesionResource
     */
    public ProfesionResource() {
    }

    /**
     * Retrieves representation of an instance of co.edu.javeriana.as.jakarta.personapp.web.services.ProfesionResource
     * @return an instance of java.lang.String
     */
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get() {
        try {
            List<Profesion> personas = profesionFacade.findAll();
            List<profesionDTO> per = new ArrayList<>();
            for(Profesion p : personas)
            {
                profesionDTO nuevo = new profesionDTO(p.getId(), p.getNom(), p.getDes());
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
            Profesion telefono = profesionFacade.find(id);
            System.out.println("sali");
            
            profesionDTO t = new profesionDTO(telefono.getId(), telefono.getNom(),telefono.getDes());
            
            String json = new Gson().toJson(t);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"Respuesta\": \"Error 5\"}";
        }
    }
    
    @PUT
    @Path("{id}/{nom}/{des}")
    @Produces(MediaType.APPLICATION_JSON)
    public String createTelefono(@PathParam("id") int id,
            @PathParam("nom") String nom,
            @PathParam("des") String des)
             {
        try {
            System.out.println("entre");
            Profesion telefono = profesionFacade.find(id);
            
            telefono.setNom(nom);
            telefono.setDes(des);

            profesionFacade.edit(telefono);
            profesionDTO t = new profesionDTO(id, nom, des);
            String json = new Gson().toJson(t);
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
            Profesion p = profesionFacade.find(id);
            profesionFacade.remove(p);
            
            String json = new Gson().toJson("eliminado");
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"Respuesta\": \"Error 5\"}";
        }
    }
}
