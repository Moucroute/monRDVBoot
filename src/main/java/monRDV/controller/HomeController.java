package monRDV.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import monRDV.repository.IRepositorySpecialite;

@Controller
public class HomeController {

	@Autowired
	private IRepositorySpecialite repoSpecialite;

	public HomeController() {
		super();
	}

	@GetMapping("")
	public String home(Model model) {

		model.addAttribute("page", "home");
		model.addAttribute("specialites", repoSpecialite.findAll()); // ETAPE 3

		return "home/home";
	}

}
