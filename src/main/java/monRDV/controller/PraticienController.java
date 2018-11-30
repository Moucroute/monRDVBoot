package monRDV.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String list(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Long id, Model model) {

		List<RendezVousEnAttente> rdvEnAttentes = new ArrayList<>();

		List<RendezVous> rendezVouss = repoRendezVous.findAllWithCreneaux();
		model.addAttribute("page", "mesrendezVousEnAttente");

		for (RendezVous rdv : rendezVouss) {
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
				
				System.out.println("debut="+debut);
				System.out.println("fin="+fin);
			}
		}

		Optional<RendezVous> opt = repoRendezVous.findById(id);
		if (opt.isPresent()) {

			model.addAttribute("mesRendezvous", rendezVouss);
		}

		return "mesrendezVousEnAttente/rendezVous"; 

	}
}
