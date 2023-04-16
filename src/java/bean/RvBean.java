/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Client;
import entities.Creneaux;
import entities.Medecin;
import entities.Rv;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import metier.Metier;
import metier.Metier_medecin;

/**
 *
 * @author PROSPER
 */
@ManagedBean
@ViewScoped
public class RvBean {
    Metier dao = new Metier();
    private Rv rv;
    private List<Rv> rvs;
    private int idr;
    private int idc;
    private String d;
    
    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public Rv getRv() {
        return rv;
    }

    public void setRv(Rv rv) {
        this.rv = rv;
    }

    public List<Rv> getRvs() {
        Metier dao = new Metier();
        rvs = dao.listeRv();
        return rvs;
    }

    public void setRvs(List<Rv> rvs) {
        this.rvs = rvs;
    }
    
    /**
     * Creates a new instance of ClientBean
     */
    public RvBean() {
        rv = new Rv();
    }
    
    public void addMessage(String m){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, m,null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void inser(){
       System.out.println("tttttttttttttttttttttt: "+getD());
       LocalDate jourLD = LocalDate.parse(getD());
       ZoneId defaultZoneId = ZoneId.systemDefault();
       Date jour1 = Date.from(jourLD.atStartOfDay(defaultZoneId).toInstant()); 
       Metier dao = new Metier();
       Creneaux m = dao.rechercheCreneaux(idr);
       Client c = dao.rechercheClient(idc);
       rv.setClient(c);
       rv.setCreneaux(m);
       rv.setJour(jour1);
       dao.creerRv(rv);
       rv = new Rv();
       addMessage("le client a ete enregistrer");
       System.out.println("bean interface");
    }
    
    public void update(){
       LocalDate jourLD = LocalDate.parse(getD());
       ZoneId defaultZoneId = ZoneId.systemDefault();
       Date jour1 = Date.from(jourLD.atStartOfDay(defaultZoneId).toInstant()); 
       Metier dao = new Metier();
       Creneaux m = dao.rechercheCreneaux(idr);
       Client c = dao.rechercheClient(idc);
       rv.setClient(c);
       rv.setCreneaux(m);
       rv.setJour(jour1);
       System.out.println("bean interface");
       dao.updateRv(rv, rv.getIdRv());
       rv = new Rv();
       addMessage("le client a ete modifier");
    }
    
    public void delet(){
       dao.deletRv(rv.getIdRv());
       rv = new Rv();
       addMessage("le client a ete supprimer");
    }
}
