/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import entities.Creneaux;
import entities.Medecin;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.connection;

/**
 *
 * @author PROSPER
 */
public class Metier_medecin {
    private final Session session = null;
    private Transaction transaction = null;
    
    public void creer(Medecin c){
        try{
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(c);
            System.out.println("detail :"+c.toString());
            transaction.commit();
        }catch (Exception ex){
            if(transaction != null)
            {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        }     
    }
    public List liste(){
        List<Medecin>  results = null ;
        try{
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "FROM Medecin C";
            Query query = session.createQuery(hql);
            results = query.list();
            session.close();
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
        List<Medecin>  results = null ;
       try{
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String idRv = id+"";
            String hql = "FROM Medecin C where C.idMedecin = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            results = query.list();
            //session.close();
        }catch (Exception ex){
            if(transaction != null)
            {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        } 
        return results;
    }
    
    public List rechercheid(String n) {
        List<Medecin> results = null;
        try {
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "FROM Medecin C where C.nom = :id or C.prenom = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", n);
            results = query.list();
            //session.close();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        }
        return results;
    }
    
    public void modifier(int id,Medecin r){
        List<Medecin>c = null;
        try{
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "FROM Medecin C where C.idMedecin = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            c = query.list();
            c.get(0).setNom(r.getNom());
            c.get(0).setPrenom(r.getPrenom());
            c.get(0).setCreneauxes(r.getCreneauxes());
            c.get(0).setTitre(r.getTitre());
            session.update(c.get(0));
            System.out.println("tttttttttttttttttttttttttttttt");
            transaction.commit();
        }catch (Exception ex){
            if(transaction != null)
            {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        } 
    }
    public void delet(int id){
       List<Medecin>c = null;
       try{
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "FROM Medecin C where C.idMedecin = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            c = query.list();
//            String hql = "DELETE FROM Creneaux "  + "WHERE idCreneaux = :idCreneaux";
//            Query query = session.createQuery(hql);
//            query.setParameter("idCreneaux", id);
//            int result = query.executeUpdate();
            if(c.get(0).getCreneauxes().isEmpty()){
                session.delete(c.get(0));
                System.out.println("suppresion medecin effectuer");
            }
            else{
                Metier_Creneaux mc = new Metier_Creneaux();
                for (Creneaux cr : c.get(0).getCreneauxes()) {
                    System.out.println("pas de pp");
                    mc.delet(cr.getIdCreneau());
                }
                System.out.println("pas de pp fin");
                session.delete(c.get(0));
                System.out.println("suppresion medecin effectuer");
            }
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
        Medecin m = new Medecin("simo","tedom","titre");
        Medecin m1 = new Medecin("prosper","ooo","titre");
        //Creneaux c = new Creneaux(m,200,300);
        //
        Metier_medecin Mm = new Metier_medecin();
        List<Medecin> md = Mm.rechercheid("prosper");
        for(Medecin cr:md){
             System.out.println(cr.getNom());
         }
        //Mm.creer(m);
        //Mm.modifier(10,m1);
//        Mm.delet(1);
//        //List<Medecin> liste = Mm.rechercheid(3);
//        List<Medecin> liste = Mm.liste();
//        for(Medecin cr:liste){
//            System.out.println(cr.getNom());
//        }
    }
}
