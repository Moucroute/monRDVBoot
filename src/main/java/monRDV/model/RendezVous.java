package monRDV.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "rendezvous")
public class RendezVous {

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private int version;

	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	private Utilisateur utilisateur;

	@ManyToOne
	@JoinTable(name = "patient_rendezvous")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "modalite_id")
	private Modalite modalite;

	@OneToMany(mappedBy = "rendezVous")
	private List<CreneauDisponible> creneaux = new ArrayList<CreneauDisponible>();

	public RendezVous() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<CreneauDisponible> getCreneaux() {
		return creneaux;
	}

	public void setCreneaux(List<CreneauDisponible> creneaux) {
		this.creneaux = creneaux;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Modalite getModalite() {
		return modalite;
	}

	public void setModalite(Modalite modalite) {
		this.modalite = modalite;
	}

	public List<CreneauDisponible> getcreneaux() {
		return creneaux;
	}

	public void setRendezVous(List<CreneauDisponible> creneaux) {
		this.creneaux = creneaux;
	}

}
