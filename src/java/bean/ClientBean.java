/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Client;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import metier.Metier;
import metier.Metier_client;

/**
 *
 * @author PROSPER
 */
@ManagedBean
@ViewScoped
public class ClientBean {
    Metier dao = new Metier();
    private Client client;
    private List<Client> clients;
    private List<Client> rechclients;
    private String rech="";

    public String getRech() {
        return rech;
    }

    public void setRech(String rech) {
        this.rech = rech;
    }

    public List<Client> getRechclients() {
        System.out.println("rrrrrrrrrrrrrrrrrrrrrrrr bean_client: debut");
        Metier Mm = new Metier();
        if (rech!="") {
            System.out.println("rrrrrrrrrrrrrrrrrrrrrrrr bean_client b : "+rech);
            rechclients = Mm.rechercheClientliste(rech);
            for (Client cr : rechclients) {
            System.out.println(cr.getNom());
        }
        }
        System.out.println("rrrrrrrrrrrrrrrrrrrrrrrr bean_client: fin");
        return rechclients;
    }

    public void setRechclients(List<Client> rechclients) {
        this.rechclients = rechclients;
    }
    

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Client> getClients() {
        Metier dao = new Metier();
        if (rech!="") {
            System.out.println("rrrrrrrrrrrrrrrrrrrrrrrr bean_client b : "+rech);
            clients = dao.rechercheClientliste(rech);
            for (Client cr : rechclients) {
            System.out.println(cr.getNom());
            }
        }
        else{
            clients = dao.listeClient();
        }
        return clients;
    }
    
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    
    
    /**
     * Creates a new instance of ClientBean
     */
    public ClientBean() {
        client = new Client();
    }
    
    public void addMessage(String m){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, m,null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void inser(){
       Metier dao = new Metier();
       dao.creerClient(client);
       client = new Client();
       addMessage("le client a ete enregistrer");
       System.out.println("bean interface");
    }
    
    public void update(){
       Metier dao = new Metier();
       dao.updateClient(client);
       client = new Client();
       addMessage("le client a ete modifier");
    }
    
    public void delet(){
       dao.deletClient(client.getIdClient());
       client = new Client();
       addMessage("le client a ete supprimer");
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
