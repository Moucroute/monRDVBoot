package monRDV.model;

import java.util.ArrayList;
import java.util.List;

public class InscriptionFormPraticien {

	private String nom;
	private String prenom;
	private String telephone;
	private String motDePasse;
	private String confirmationMotDePasse;
	private String email;
	private Boolean prendCarteVitale;
	private List<Specialite> specialites = new ArrayList<>();
	private String specialite;

	public InscriptionFormPraticien() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getConfirmationMotDePasse() {
		return confirmationMotDePasse;
	}

	public void setConfirmationMotDePasse(String confirmationMotDePasse) {
		this.confirmationMotDePasse = confirmationMotDePasse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getPrendCarteVitale() {
		return prendCarteVitale;
	}

	public void setPrendCarteVitale(Boolean prendCarteVitale) {
		this.prendCarteVitale = prendCarteVitale;
	}

	public List<Specialite> getSpecialites() {
		return specialites;
	}

	public void setSpecialites(List<Specialite> specialites) {
		this.specialites = specialites;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

}
