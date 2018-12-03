package monRDV.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autre")
public class autre {

	@GetMapping("contact")
	public String contact(Model model) {

		return ("Autres/contact");
	}

	@GetMapping("mention_legales")
	public String mention_legales(Model model) {

		return ("Autres/mention_legales");
	}
	
	
	@GetMapping("charte_utilisation")
	public String charte_utilisation(Model model) {

		return ("Autres/charte_utilisation");
	}

}
