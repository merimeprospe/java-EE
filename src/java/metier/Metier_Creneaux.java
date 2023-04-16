/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import entities.Creneaux;
import entities.Medecin;
import entities.Rv;
import java.util.List;
import org.hibernate.Query;
import util.connection;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author PROSPER
 */
public class Metier_Creneaux {
    private final Session session = null;
    private Transaction transaction = null;
    
    public void creer(Creneaux c){
        try{
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(c);
            System.out.println("detail :"+c.toString());
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
    public List liste(){
       List<Creneaux> results = null ;
       try{
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "FROM Creneaux C";
            Query query = session.createQuery(hql);
            results = query.list();
            System.out.println("creation effectuer");
            transaction.commit();
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
        List<Creneaux> results = null ;
        try{
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "FROM Creneaux C where C.idCreneau = :id";
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
    public void modifier(int id,Creneaux c1){
        List<Creneaux>c = null;
        try{
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "FROM Creneaux C where C.idCreneau = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            c= query.list();
            c.get(0).setHdebut(c1.getHdebut());
            c.get(0).setHfin(c1.getHfin());
            c.get(0).setMedecin(c1.getMedecin());
            session.update(c.get(0));
            System.out.println("modification effectuer");
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
    public void delet(int id){
        List<Creneaux>c = null;
        try{
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "FROM Creneaux C where C.idCreneau = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            c = query.list();
//            String hql = "DELETE FROM Creneaux "  + "WHERE idCreneaux = :idCreneaux";
//            Query query = session.createQuery(hql);
//            query.setParameter("idCreneaux", id);
//            int result = query.executeUpdate();
            if(c.get(0).getRvs().isEmpty()){
                session.delete(c.get(0));
                System.out.println("suppresion creneaux effectuer");
            }
            else{
                Metier_Rv mc = new Metier_Rv();
                for (Rv cr : c.get(0).getRvs()) {
                    System.out.println("pas de pp crenaux");
                    mc.delet(cr.getIdRv());
                }
                System.out.println("pas de pp fin crenaux");
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
        Metier_Creneaux mc = new Metier_Creneaux();
        List<Creneaux> t = mc.rechercheid(11);
        for(Creneaux cr:t){
            System.out.println("ffffffffffffffffff");
            System.out.println(cr.getHdebut());
         }
        List<Medecin> md = Mm.rechercheid(10);
        for(Medecin cr:md){
             System.out.println(cr.getNom());
         }
        Creneaux c2 = new Creneaux( md.get(0),"800","9000");
        //mc.creer(c2);
        mc.modifier(11,c2);
        //mc.delet(1);
        //List<Creneaux> liste = mc.rechercheid(3);
//        List<Creneaux> liste = mc.liste();
//        for(Creneaux cr:liste){
//            System.out.println(cr.getMedecin().getNom());
//        }
    }
}
