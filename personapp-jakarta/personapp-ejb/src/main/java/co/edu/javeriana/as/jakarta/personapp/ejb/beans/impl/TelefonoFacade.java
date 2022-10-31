/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.javeriana.as.jakarta.personapp.ejb.beans.impl;

import co.edu.javeriana.as.jakarta.personapp.ejb.beans.AbstractFacade;
import co.edu.javeriana.as.jakarta.personapp.ejb.beans.TelefonoFacadeLocal;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.Persona;
import co.edu.javeriana.as.jakarta.personapp.ejb.entities.Telefono;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author aasanchez
 */
@Stateless
public class TelefonoFacade extends AbstractFacade<Telefono> implements TelefonoFacadeLocal {

    @PersistenceContext(unitName = "persona_pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TelefonoFacade() {
        super(Telefono.class);
    }
    
     @Override
    public void create(Telefono entity) {
        
        TypedQuery<Telefono> query = em.createQuery("INSERT INTO telefono VALUES(" 
                + entity.getNum().toString() +"," +
                entity.getOper()+"," +
                entity.getDuenio()+")" ,Telefono.class);
        
        query.executeUpdate();
        
    }

    @Override
    public void edit(Telefono entity) {
        TypedQuery<Telefono> query = em.createQuery("UPDATE Telefono "
                + "SET num = ?1 , oper = ?2 , duenio = ?3 "
                + "WHERE num = ?1",  Telefono.class);
        query.setParameter(1, entity.getNum());
        query.setParameter(2, entity.getOper());
        query.setParameter(3, entity.getDuenio());

        query.executeUpdate();
    }

    @Override
    public Telefono find(Object id) {
        TypedQuery<Telefono> query = em.createQuery("select p FROM Telefono p WHERE p.num = ?1" ,Telefono.class);
        query.setParameter(1, id);
        
        query.executeUpdate();
        
        return query.getSingleResult();
    }
    
    @Override
     public Telefono find2(String id)
    {
       System.out.println("buscando...");
        TypedQuery<Telefono> query;
        query = em.createQuery("SELECT t FROM Telefono t WHERE t.num = :num",Telefono.class);
        query.setParameter("num",id);
        
        return query.getSingleResult();
        
    }

  

    @Override
    public void remove(Telefono entity) {
         TypedQuery<Telefono> query = em.createQuery("DELETE FROM Telefono t WHERE t.num = ?1" ,Telefono.class);
        query.setParameter(1, entity.getNum());
        
        query.executeUpdate();
        
    }
    
    
}
