package monRDV.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import monRDV.model.CreneauDisponible;
import monRDV.model.RendezVous;
import monRDV.model.RendezVousEnAttente;
import monRDV.repository.IRepositoryCreneauDisponible;
import monRDV.repository.IRepositoryModalite;
import monRDV.repository.IRepositoryPatient;
import monRDV.repository.IRepositoryRendezVous;

@Controller
@RequestMapping("/mesRendezVousEnAttente")
public class PraticienController {

	@Autowired
	private IRepositoryRendezVous repoRendezVous;

	@Autowired
	private IRepositoryModalite repoModalite;

	@Autowired
	private IRepositoryCreneauDisponible repoCreneauDisponible;

	@Autowired
	private IRepositoryPatient repoPatient;

	public PraticienController() {
		super();

	}

	@GetMapping({ "/list" })
	public String list(@RequestParam Long idPraticien, Model model) {

		List<RendezVousEnAttente> rdvEnAttentes = new ArrayList<>();

		List<RendezVous> rendezVouss = repoRendezVous.findAllWithCreneauxByPraticien(idPraticien);
		model.addAttribute("page", "mesrendezVousEnAttente");
		 

		for (RendezVous rdv : rendezVouss) {
			System.out.println(rdv.getId());
			if (!rdv.getStatut()) {
				RendezVousEnAttente rdvEnAttente = new RendezVousEnAttente();
				rdvEnAttente.setNom(rdv.getPatient().getNom());
				rdvEnAttente.setPrenom(rdv.getPatient().getPrenom());
				rdvEnAttente.setMotif(rdv.getModalite().getMotif().getLibelle());

				Date debut = null;
				Date fin = null;
				for (CreneauDisponible creneau : rdv.getCreneaux()) {
					if (debut == null) {
						debut = creneau.getDebut();
					} else if (debut.getTime() > creneau.getDebut().getTime()) {
						debut = creneau.getDebut();
					}
					if (fin == null) {
						fin = creneau.getFin();
					} else if (fin.getTime() < creneau.getFin().getTime()) {
						fin = creneau.getFin();
					}

				}

				rdvEnAttente.setDtRdv(debut);

				long duree = fin.getTime() - debut.getTime();

				rdvEnAttente.setDuree(duree / 60000);

				System.out.println("dtRdv=" + rdvEnAttente.getDtRdv());
				System.out.println("duree=" + rdvEnAttente.getDuree());
				
				rdvEnAttentes.add(rdvEnAttente);

//				model.addAttribute("rendezVouss", rdvEnAttente);
			}
		}

			model.addAttribute("rdvEnAttentes", rdvEnAttentes);

		return "praticien/praticiens";

	}
	
}
