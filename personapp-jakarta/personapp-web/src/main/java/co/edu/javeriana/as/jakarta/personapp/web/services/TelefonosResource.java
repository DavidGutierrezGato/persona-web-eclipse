/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package co.edu.javeriana.as.jakarta.personapp.web.services;

import co.edu.javeriana.as.jakarta.personapp.ejb.beans.PersonaFacadeLocal;
import co.edu.javeriana.as.jakarta.personapp.ejb.beans.TelefonoFacadeLocal;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.Persona;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.Telefono;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.personaDTO;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.telefonoDTO;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
@Path("telefonos")
@RequestScoped
public class TelefonosResource {

    @Context
    private UriInfo context;
    
    @EJB
    private TelefonoFacadeLocal telefonoFacade;
    
    @EJB
    private PersonaFacadeLocal personaFacade;

    /**
     * Creates a new instance of TelefonosResource
     */
    public TelefonosResource() {
    }

    /**
     * Retrieves representation of an instance of co.edu.javeriana.as.jakarta.personapp.web.services.TelefonosResource
     * @return an instance of java.lang.String
     */
    /*@GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of TelefonosResource
     * @param content representation for the resource
     */
   
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get() {
        try {
            List<Telefono> personas = telefonoFacade.findAll();
            List<telefonoDTO> per = new ArrayList<>();
            for(Telefono p : personas)
            {
                telefonoDTO nuevo = new telefonoDTO(p.getNum(), p.getOper(), p.getDuenio().getCc());
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
    public String getId(@PathParam("id") String id) {
        try {
            System.out.println("entre");
            Telefono telefono = telefonoFacade.find2(id);
            System.out.println("sali");
            
            telefonoDTO t = new telefonoDTO(telefono.getNum(),telefono.getOper(),telefono.getDuenio().getCc());
            
            String json = new Gson().toJson(t);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"Respuesta\": \"Error 5\"}";
        }
    }
    
    @PUT
    @Path("{num}/{oper}/{duenio}")
    @Produces(MediaType.APPLICATION_JSON)
    public String createTelefono(@PathParam("num") String num,
            @PathParam("oper") String oper,
            @PathParam("duenio") int duenio)
             {
        try {
            System.out.println("entre");
            Telefono telefono = telefonoFacade.find2(num);
            Persona p = personaFacade.find(duenio);
            telefono.setNum(num); ;
            telefono.setOper(oper);
            telefono.setDuenio(p);
            telefonoFacade.edit(telefono);
            telefonoDTO t = new telefonoDTO(num, oper, duenio);
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
    public String createPersona(@PathParam("id") String id) {
        try {
            Telefono p = telefonoFacade.find2(id);
            telefonoFacade.remove(p);
            
            String json = new Gson().toJson("eliminado");
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"Respuesta\": \"Error 5\"}";
        }
    }
    
}
