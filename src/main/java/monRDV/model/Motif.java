package monRDV.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "motif")
public class Motif {

	@Id
	@GeneratedValue
	@Column(name = "id", length = 100)
	private Long id;

	@Version
	private Integer version;

	@Column(name = "libelle", length = 100)
	private String libelle;

	@ManyToOne
	@JoinColumn(name = "specialite_id")
	private Specialite specialite;

	@OneToMany(mappedBy = "motif")
	private List<Modalite> modalites = new ArrayList<>();

	public Motif() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Motif(Long id, Integer version, String libelle) {
		super();
		this.id = id;
		this.version = version;
		this.libelle = libelle;
	}


	public Specialite getSpecialite() {
		return specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	public List<Modalite> getModalites() {
		return modalites;
	}

	public void setModalites(List<Modalite> modalites) {
		this.modalites = modalites;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
