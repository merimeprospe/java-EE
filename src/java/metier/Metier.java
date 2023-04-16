/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.util.List;
import entities.Client;
import entities.Creneaux;
import entities.Medecin;
import entities.Rv;

/**
 *
 * @author PROSPER
 */
public class Metier {
    Metier_client cl = new Metier_client();
    Metier_Creneaux c = new Metier_Creneaux();
    Metier_medecin m = new Metier_medecin();
    Metier_Rv r = new Metier_Rv();
    
    public void creerClient(Client ob ){
        System.out.println("metier clobal interface");
        cl.creer(ob);
    }
    public void creerCreneaux(Creneaux ob ){
        c.creer(ob);
    }
    public void creerRv(Rv ob ){
        r.creer(ob);
    }
    public void creerMedecin(Medecin ob){
        m.creer(ob);
    }
    
    
    public void updateClient(Client ob){
        cl.modifier1(ob);
    }
    public void updateCreneaux(Creneaux ob, int id){
        c.modifier(id, ob);
    }
    public void updateRv(Rv ob ,int id){
        r.modifier(id, ob);
    }
    public void updateMedecin(Medecin ob , int id){
        m.modifier(id,ob);
    }
    
    
    public void deletMedecin(int id){
        m.delet(id);
    }
    public void deletClient(int id){
        cl.delet(id);
    }
    public void deletCreneaux(int id){
        c.delet(id);
    }
    public void deletRv(int id){
        r.delet(id);
    }
    
    public Medecin rechercheMedecin(int id){
        List<Medecin> l =m.rechercheid(id);
        return l.get(0);
    }
    public Client rechercheClient(int id){
        List<Client> l = cl.rechercheid(id);
        return l.get(0);
    }
    public List<Medecin> rechercheMedecinliste(String id){
        List<Medecin> l =m.rechercheid(id);
        return l;
    }
    public List<Client> rechercheClientliste(String id){
        List<Client> l = cl.rechercheid(id);
        System.out.println("rrrrrrrrrrrrrrrrrrrrrrrr metier: "+id);
        return l;
    }
    public Creneaux rechercheCreneaux(int id){
        List<Creneaux> l = c.rechercheid(id);
        return l.get(0);
    }
    public Rv rechercheRv(int id){
        List<Rv> l = r.rechercheid(id);
        return l.get(0);
    }
    
    
    public List<Medecin> listeMedecin(){
        return m.liste();
    }
    public List<Client> listeClient(){
        return cl.liste();
    }
    public List<Creneaux> listeCreneaux(){
        return c.liste();
    }
    public List<Rv> listeRv(){
        return r.liste();
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Client m = new Client("simo", "tedom", "titre");
        Client m1 = new Client("prosper", "ooo", "titre");
        //Creneaux c = new Creneaux(m,200,300);
        //
        Metier Mm = new Metier();
        List<Client> md = Mm.rechercheClientliste("simo");
        for (Client cr : md) {
            System.out.println(cr.getNom());
        }
    }
}
