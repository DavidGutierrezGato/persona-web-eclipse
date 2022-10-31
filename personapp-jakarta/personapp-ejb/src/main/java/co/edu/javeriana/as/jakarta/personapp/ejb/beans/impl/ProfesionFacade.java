/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.javeriana.as.jakarta.personapp.ejb.beans.impl;

import co.edu.javeriana.as.jakarta.personapp.ejb.beans.AbstractFacade;
import co.edu.javeriana.as.jakarta.personapp.ejb.beans.ProfesionFacadeLocal;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.Persona;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.Profesion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author aasanchez
 */
@Stateless
public class ProfesionFacade extends AbstractFacade<Profesion> implements ProfesionFacadeLocal {

    @PersistenceContext(unitName = "persona_pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesionFacade() {
        super(Profesion.class);
    }
    
    
    @Override
    public void create(Profesion persona)
    {
        //"\"SELECT p FROM Persona p WHERE p.nombre = :nombre\""
        TypedQuery<Profesion> query = em.createQuery("INSERT INTO Profesion VALUES(" 
                + persona.getId().toString() +"," +
                persona.getNom() +"," +
                persona.getDes()
                +")" ,Profesion.class);
        
        
        query.executeUpdate();
        
    }
    
    @Override
     public Profesion find(int id)
    {
       System.out.println("buscando...");
        TypedQuery<Profesion> query;
        query = em.createQuery("SELECT p FROM Profesion p WHERE p.id = :cc",Profesion.class);
        query.setParameter("cc",id);
        
        return query.getSingleResult();
        
    }
     
     @Override
    public void edit(Profesion persona)
    {
        TypedQuery<Profesion> query = em.createQuery("UPDATE Profesion "
                + "SET id = ?1 , nom = ?2 , des = ?3 "
                + "WHERE id = ?1",  Profesion.class);
        query.setParameter(1, persona.getId());
        query.setParameter(2, persona.getNom());
        query.setParameter(3, persona.getDes());
        
               
        
        query.executeUpdate();
    }

    //void remove(Persona persona);

    @Override
    public void remove(Profesion persona)
    {
        TypedQuery<Profesion> query = em.createQuery("DELETE FROM Profesion WHERE id = :cc" ,Profesion.class);
        query.setParameter("cc", persona.getId());
        
        query.executeUpdate();
    }
    
    
}
