package entities;
// Generated 25-mars-2023 15:30:41 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * Client generated by hbm2java
 */
public class Client implements java.io.Serializable {

    private Integer idClient;
    private String nom;
    private String prenom;
    private String titre;
    private Set<Rv> rvs = new HashSet<Rv>(0);

    public Client() {
    }

    public Client(String nom, String prenom, String titre) {
        this.nom = nom;
        this.prenom = prenom;
        this.titre = titre;
    }

    public Client(String nom, String prenom, String titre, Set<Rv> rvs) {
        this.nom = nom;
        this.prenom = prenom;
        this.titre = titre;
        this.rvs = rvs;
    }

    public Integer getIdClient() {
        return this.idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Set<Rv> getRvs() {
        return this.rvs;
    }

    public void setRvs(Set<Rv> rvs) {
        this.rvs = rvs;
    }

    public String toString() {
        return " " + nom + "_" + prenom + "_" + titre;
    }

}
