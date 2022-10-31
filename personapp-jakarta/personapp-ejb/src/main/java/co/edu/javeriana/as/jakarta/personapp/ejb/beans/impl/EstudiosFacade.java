/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.javeriana.as.jakarta.personapp.ejb.beans.impl;

import co.edu.javeriana.as.jakarta.personapp.ejb.beans.AbstractFacade;
import co.edu.javeriana.as.jakarta.personapp.ejb.beans.EstudiosFacadeLocal;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.Estudios;
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
public class EstudiosFacade extends AbstractFacade<Estudios> implements EstudiosFacadeLocal {

    @PersistenceContext(unitName = "persona_pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudiosFacade() {
        super(Estudios.class);
    }
    
    @Override
    public void create(Estudios persona)
    {
        //"\"SELECT p FROM Persona p WHERE p.nombre = :nombre\""
        TypedQuery<Estudios> query = em.createQuery("INSERT INTO Estudios VALUES(" 
                + persona.getPersona().getCc()+"," +
                persona.getProfesion().getId() +"," +
                persona.getFecha() +"," +
                persona.getUniver()
                +")" ,Estudios.class);
        
        
        query.executeUpdate();
        
    }
    
    @Override
    public Estudios find(int id)
    {
       System.out.println("buscando...");
        TypedQuery<Estudios> query;
        query = em.createQuery("SELECT p FROM Estudios p WHERE p.id = :cc AND p.",Estudios.class);
        query.setParameter("cc",id);
        
        return query.getSingleResult();
        
    }
     
     @Override
    public void edit(Estudios persona)
    {
        TypedQuery<Profesion> query = em.createQuery("UPDATE Profesion "
                + "SET id = ?1 , nom = ?2 , des = ?3 "
                + "WHERE id = ?1",  Profesion.class);
        query.setParameter(1, persona.getPersona().getCc());
        query.setParameter(2, persona.getProfesion().getId());
        query.setParameter(3, persona.getFecha());
        
               
        
        query.executeUpdate();
    }

    //void remove(Persona persona);

    @Override
    public void remove(Estudios persona)
    {
        TypedQuery<Profesion> query = em.createQuery("DELETE FROM Profesion WHERE id = :cc" ,Profesion.class);
        query.setParameter("cc", persona.getPersona().getCc());
        
        query.executeUpdate();
    }
    
}
