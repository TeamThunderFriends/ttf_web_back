package ttf.lost.presentation.view.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginViewController {

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

}
