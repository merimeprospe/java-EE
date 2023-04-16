package bean;

import entities.Medecin;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import metier.Metier;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PROSPER
 */
@ManagedBean
@ViewScoped
public class MedecinBean {
    Metier dao = new Metier();
    private Medecin medecin;
    private List<Medecin> medecins;
    private String rech="";

    public String getRech() {
        return rech;
    }

    public void setRech(String rech) {
        this.rech = rech;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public List<Medecin> getMedecins() {
        Metier dao = new Metier();
        if (rech!="") {
            medecins = dao.rechercheMedecinliste(rech);
            for (Medecin cr : medecins) {
                System.out.println(cr.getNom());
            }
        }
        else{
            medecins = dao.listeMedecin();
        }
        return medecins;
    }

    public void setMedecins(List<Medecin> medecins) {
        this.medecins = medecins;
    }
    
    /**
     * Creates a new instance of ClientBean
     */
    public MedecinBean() {
        medecin = new Medecin();
    }
    
    public void addMessage(String m){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, m,null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void inser(){
       Metier dao = new Metier();
       dao.creerMedecin(medecin);
       medecin = new Medecin();
       addMessage("le medecin a ete enregistrer");
       System.out.println("bean interface");
    }
    
    public void update(){
       Metier dao = new Metier();
       dao.updateMedecin(medecin,medecin.getIdMedecin());
       System.out.println("1 bean interface");
       medecin = new Medecin();
       addMessage("le client a ete modifier");
       System.out.println("bean interface");
    }
    
    public void delet(){
       dao.deletMedecin(medecin.getIdMedecin());
        medecin = new Medecin();
       addMessage("le client a ete supprimer");
    }
}
