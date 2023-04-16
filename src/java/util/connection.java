/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entities.Creneaux;
import entities.Client;
import entities.Medecin;
import entities.Rv;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author PROSPER
 */
public class connection {
    private static SessionFactory session;
    static {
        try{
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Creneaux.class);
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Rv.class);
                configuration.addAnnotatedClass(Medecin.class);
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                SessionFactory factory = configuration.buildSessionFactory(builder.build());

                session = factory;
            }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public static SessionFactory getSessionFactory() {
        return session;
    }
}
