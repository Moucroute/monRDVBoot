package monRDV.model;

import java.util.Date;

public class RendezVousEnAttente {
	private Date dtRdv;
	private long duree;
	private String motif;
	private String nom;
	private String prenom;

	public RendezVousEnAttente() {
		super();
	}

	public RendezVousEnAttente(Date dtRdv, long duree, String motif, String nom, String prenom) {
		super();
		this.dtRdv = dtRdv;
		this.duree = duree;
		this.motif = motif;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Date getDtRdv() {
		return dtRdv;
	}

	public void setDtRdv(Date dtRdv) {
		this.dtRdv = dtRdv;
	}

	public long getDuree() {
		return duree;
	}

	public void setDuree(long duree) {
		this.duree = duree;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
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

}
