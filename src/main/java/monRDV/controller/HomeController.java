package monRDV.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import monRDV.repository.IRepositoryLieu;
import monRDV.repository.IRepositorySpecialite;

@Controller
public class HomeController {

	@Autowired
	private IRepositorySpecialite repoSpecialite;
	
	@Autowired
	private IRepositoryLieu repoLieu;

	public HomeController() {
		super();
	}

	@GetMapping("")
	public String home(Model model) {

		model.addAttribute("page", "home");
		model.addAttribute("specialites", repoSpecialite.findAll()); // ETAPE 3
		model.addAttribute("lieux", repoLieu.findAll());

		return "home/home";
	}

}
