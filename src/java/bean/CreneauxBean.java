/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Creneaux;
import entities.Medecin;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import metier.Metier;

/**
 *
 * @author PROSPER
 */
@ManagedBean
@ViewScoped
public class CreneauxBean {
    private Creneaux creneau;
    private List<Creneaux> creneaux;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Creneaux getCreneau() {
        return creneau;
    }

    public void setCreneau(Creneaux creneau) {
        this.creneau = creneau;
    }

    public List<Creneaux> getCreneaux() {
        Metier dao = new Metier();
        creneaux = dao.listeCreneaux();
        return creneaux;
    }

    public void setCreneaux(List<Creneaux> creneaux) {
        this.creneaux = creneaux;
    }

   /**
     * Creates a new instance of ClientBean
     */
    public CreneauxBean() {
        creneau = new Creneaux();
    }
    
    public void addMessage(String m){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, m,null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
   
    public void inser(){
       Metier dao = new Metier();
       Medecin m = dao.rechercheMedecin(this.id);
       creneau.setMedecin(m);
       System.out.println(m.toString());
       dao.creerCreneaux(creneau);
       System.out.println("bean interface");
       creneau = new Creneaux();
       addMessage("le client a ete enregistrer");
    }
    
    public void update(){
       Metier dao = new Metier();
       dao.updateCreneaux(creneau, creneau.getIdCreneau());
       creneau = new Creneaux();
       addMessage("le client a ete modifier");
    }
    
    public void delet(){
       Metier dao = new Metier();
       dao.deletCreneaux(creneau.getIdCreneau());
       creneau = new Creneaux();
       addMessage("le client a ete supprimer");
    }
}
