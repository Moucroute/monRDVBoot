package monRDV.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity
@Table(name = "praticien")
public class Praticien {

	@Id
	@GeneratedValue
	private long id;
	@Version
	private Integer version;
	@Column(name = "nom", length = 100)
	private String nom;
	@Column(name = "prenom", length = 100)
	private String prenom;
	@Column(name = "Prend_Carte_Vitale")
	private Boolean prendCarteVitale;
	@Column(name = "Validation_Auto")
	private Boolean validationAuto;

	@ManyToMany
	@JoinTable(name = "praticien_specialite", joinColumns = @JoinColumn(name = "praticien_id"), inverseJoinColumns = @JoinColumn(name = "specialite_id"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"praticien_id", "specialite_id" }))
	private List<Specialite> specialites = new ArrayList<>();

	@OneToMany(mappedBy = "praticien")
	private List<Lieu> lieux = new ArrayList<>();
	@OneToMany(mappedBy = "praticien")
	private List<Modalite> modalites = new ArrayList<>();
	@OneToMany(mappedBy = "praticien")
	private List<CreneauDisponible> creneaux = new ArrayList<>();

	@OneToOne(mappedBy = "praticien")
	private Utilisateur utilisateur;

	public Praticien() {
		super();
	}
	

	public List<Specialite> getSpecialites() {
		return specialites;
	}

	public void setSpecialites(List<Specialite> specialites) {
		this.specialites = specialites;
	}

	public List<Lieu> getLieux() {
		return lieux;
	}

	public void setLieux(List<Lieu> lieux) {
		this.lieux = lieux;
	}

	public List<Modalite> getModalites() {
		return modalites;
	}

	public void setModalites(List<Modalite> modalites) {
		this.modalites = modalites;
	}

	public List<CreneauDisponible> getCreneaux() {
		return creneaux;
	}

	public void setCreneauDisponibles(List<CreneauDisponible> creneaux) {
		this.creneaux = creneaux;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
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

	public boolean isPrendCarteVitale() {
		return prendCarteVitale;
	}

	public void setPrendCarteVitale(Boolean prendCarteVitale) {
		this.prendCarteVitale = prendCarteVitale;
	}

	public boolean isValidationAuto() {
		return validationAuto;
	}

	public void setValidationAuto(Boolean validationAuto) {
		this.validationAuto = validationAuto;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public Boolean getPrendCarteVitale() {
		return prendCarteVitale;
	}


	public Boolean getValidationAuto() {
		return validationAuto;
	}


	public void setCreneaux(List<CreneauDisponible> creneaux) {
		this.creneaux = creneaux;
	}
	
	

}
