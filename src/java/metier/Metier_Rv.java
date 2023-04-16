/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import entities.Client;
import entities.Creneaux;
import entities.Medecin;
import entities.Rv;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.connection;

/**
 *
 * @author PROSPER
 */
public class Metier_Rv {
    private final Session session = null;
    private Transaction transaction = null;
    
    public void creer(Rv r){
        try{
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(r);
            System.out.println("detail :"+r.toString());
            session.flush() ;
            transaction.commit();
            System.out.println("creation effectuer");
        }catch (Exception ex){
            if(transaction != null)
            {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        } 
    }
    public List liste(){
       List<Rv> results = null ;
       try{
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "FROM Rv C";
            Query query = session.createQuery(hql);
            results = query.list();
            transaction.commit();
        }catch (Exception ex){
            if(transaction != null)
            {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        }    
        return results;
    }
    public List rechercheid(int id){
        List<Rv> results = null ;
        try{
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "FROM Rv C where C.idRv = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            results = query.list();
        }catch (Exception ex){
            if(transaction != null)
            {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        }
        return results;
    }
    public void modifier(int id,Rv r){
        List<Rv>c = null;
        try{
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "FROM Rv C where C.idRv = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            c = query.list();
            c.get(0).setClient(r.getClient());
            c.get(0).setCreneaux(r.getCreneaux());
            c.get(0).setJour(r.getJour());
            session.update(c.get(0));
            System.out.println("tttttttttttttttttttttttttttttt");
            System.out.println(c.get(0).getClient().getNom());
            transaction.commit();
            c = rechercheid(id);
            System.out.println(c.get(0).getClient().getNom());
            System.out.println("tttttttttttttttttttttttttttttt");
        }catch (Exception ex){
            if(transaction != null)
            {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        } 
    }
    public void delet(int id){
        List<Rv>results = null;
        try{
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
//            String hql = "DELETE FROM Creneaux "  + "WHERE idCreneaux = :idCreneaux";
//            Query query = session.createQuery(hql);
//            query.setParameter("idCreneaux", id);
//            int result = query.executeUpdate();
            String hql = "FROM Rv C where C.idRv = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            results = query.list();
            session.delete(results.get(0));
            System.out.println("suppresion Rv effectuer");
            transaction.commit();
            session.close();
        }catch (Exception ex){
            if(transaction != null)
            {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        }     
    }
    
     public static void main(String[] args) {
        // TODO code application logic here
        Client m = new Client("simo","tedom","titre");
        Client m1 = new Client("prosper","ooo","titre");
        Medecin md = new Medecin("simo","tedom","titre");
        Creneaux c2 = new Creneaux( md,"700","7000");
        Date d = new Date();
        Metier_Creneaux mc = new Metier_Creneaux();
        List<Creneaux> t = mc.rechercheid(1);
        Metier_client cm = new Metier_client();
        List<Client> cl = cm.rechercheid(1);
        Metier_Rv Mm = new Metier_Rv();
        List<Rv> md1 = Mm.rechercheid(3);
        for(Rv cr:md1){
            System.out.println(cr.getClient().getPrenom());
         }
        Rv g = new Rv(cl.get(0),t.get(0),d); 
        //Mm.creer(g);
        //Mm.modifier(7,g);
        Mm.delet(8);
        List<Rv> liste = Mm.liste();
        for(Rv cr:liste){
             System.out.println(cr.getClient().getNom());
        }
    }
    
}
