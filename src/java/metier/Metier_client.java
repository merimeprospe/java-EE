/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import entities.Client;
import entities.Rv;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.connection;

/**
 *
 * @author PROSPER
 */
public class Metier_client {

    private final Session session = null;
    private Transaction transaction = null;

    public void creer(Client c) {
        try {
            System.out.println("metier interface");
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(c);
            System.out.println("detail :" + c.toString());
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        }
    }

    public List liste() {
        List<Client> results = null;
        try {
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "FROM Client C";
            Query query = session.createQuery(hql);
            results = query.list();
            session.close();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        }
        return results;
    }

    public List rechercheid(int id) {
        List<Client> results = null;
        try {
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String idRv = id + "";
            String hql = "FROM Client C where C.idClient = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
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

    public List rechercheid(String n) {
        List<Client> results = null;
        try {
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "FROM Client C where C.nom = :id or C.prenom = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", n);
            results = query.list();
            session.close();
            System.out.println("rrrrrrrrrrrrrrrrrrrrrrrr m_client : "+n+"=");
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        }
        return results;
    }
    
    public void modifier(int id, Client r) {
        List<Client> c = null;
        try {
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "FROM Client C where C.idClient = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            c = query.list();
            c.get(0).setNom(r.getNom());
            c.get(0).setPrenom(r.getPrenom());
            c.get(0).setRvs(r.getRvs());
            c.get(0).setTitre(r.getTitre());
            session.update(c.get(0));
            System.out.println("tttttttttttttttttttttttttttttt");
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        }
    }

    public void modifier1(Client r) {
        System.out.println("1 modif1---e " + r.getNom());
        try {
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            System.out.println("2 modif1---3 " + r.getNom());
            session.update(r);
            System.out.println("3 modif1---4 " + r.getNom());
            System.out.println("tttttttttttttttttttttttttttttt");
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        }
    }

    public void delet(int id) {
        List<Client> c = null;
        try {
            Session session = connection.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = "FROM Client C where C.idClient = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            c = query.list();
//            String hql = "DELETE FROM Creneaux "  + "WHERE idCreneaux = :idCreneaux";
//            Query query = session.createQuery(hql);
//            query.setParameter("idCreneaux", id);
//            int result = query.executeUpdate();
            if (c.get(0).getRvs().isEmpty()) {
                session.delete(c.get(0));
                System.out.println("suppresion client effectuer");
            } else {
                Metier_Rv mc = new Metier_Rv();
                for (Rv cr : c.get(0).getRvs()) {
                    System.out.println("pas de pp client");
                    mc.delet(cr.getIdRv());
                }
                System.out.println("pas de pp fin client");
                session.delete(c.get(0));
                System.out.println("suppresion client effectuer");
            }

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Client m = new Client("simo", "tedom", "titre");
        Client m1 = new Client("prosper", "ooo", "titre");
        //Creneaux c = new Creneaux(m,200,300);
        //
        Metier_client Mm = new Metier_client();
        List<Client> md = Mm.rechercheid("simo");
        for (Client cr : md) {
            System.out.println(cr.getNom());
        }
        //Mm.creer(m);
        //Mm.modifier(7,m1);
        //Mm.delet(1);
        //List<Client> liste = Mm.rechercheid(3);
//        List<Client> liste = Mm.liste();
//        for (Client cr : liste) {
//            System.out.println(cr.getNom());
//        }
    }
}
