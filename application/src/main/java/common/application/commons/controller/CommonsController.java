package common.application.commons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/commons")
public record CommonsController() {

	@GetMapping("/accessDenied")
	public String addcessDenied() {
		String url="/commons/accessDenied";
		return url;
	}

}
