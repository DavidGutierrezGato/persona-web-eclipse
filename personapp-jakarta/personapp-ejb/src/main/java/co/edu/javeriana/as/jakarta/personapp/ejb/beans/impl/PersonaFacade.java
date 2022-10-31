/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.javeriana.as.jakarta.personapp.ejb.beans.impl;

import co.edu.javeriana.as.jakarta.personapp.ejb.beans.AbstractFacade;
import co.edu.javeriana.as.jakarta.personapp.ejb.beans.PersonaFacadeLocal;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.Persona;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author aasanchez
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> implements PersonaFacadeLocal {

    @PersistenceContext(unitName = "persona_pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }
    
    
    @Override
    public void create(Persona persona)
    {
        //"\"SELECT p FROM Persona p WHERE p.nombre = :nombre\""
        TypedQuery<Persona> query = em.createQuery("INSERT INTO Persona VALUES(" 
                + persona.getCc().toString() +"," +
                persona.getNombre() +"," +
                persona.getApellido()+"," +
                persona.getGenero() +")" ,Persona.class);
        
        
        query.executeUpdate();
        
    }
    
    @Override
     public Persona find(String id)
    {
       System.out.println("buscando...");
        TypedQuery<Persona> query;
        query = em.createQuery("SELECT p FROM Persona p WHERE p.cc = :cc",Persona.class);
        query.setParameter("cc",id);
        
        return query.getSingleResult();
        
    }
     
     @Override
    public void edit(Persona persona)
    {
        TypedQuery<Persona> query = em.createQuery("UPDATE Persona "
                + "SET nombre = ?1 , apellido = ?2 , genero = ?3 "
                + "WHERE cc = ?4",  Persona.class);
        query.setParameter(1, persona.getNombre());
        query.setParameter(2, persona.getApellido());
        query.setParameter(3, persona.getGenero());
        query.setParameter(4, persona.getCc());
               
        
        query.executeUpdate();
    }

    //void remove(Persona persona);

    @Override
    public void remove(Persona persona)
    {
        TypedQuery<Persona> query = em.createQuery("DELETE FROM Persona WHERE persona.cc = :cc  AND delete_rule = 'CASCADE'" ,Persona.class);
        query.setParameter("cc", persona.getCc() );
        
        query.executeUpdate();
    }

    
}
