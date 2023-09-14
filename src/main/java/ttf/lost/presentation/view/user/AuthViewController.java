package ttf.lost.presentation.view.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AuthViewController {
	@GetMapping("/character/auth")
	public String accountAuthenticationView() {
		return "character/authentication";
	}
}
