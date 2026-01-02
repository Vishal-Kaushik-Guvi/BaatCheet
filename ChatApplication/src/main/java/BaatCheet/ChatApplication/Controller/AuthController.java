package BaatCheet.ChatApplication.Controller;

import BaatCheet.ChatApplication.Entity.User;
import BaatCheet.ChatApplication.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 🔐 Login Page
    @GetMapping("/signin")
    public String loginPage() {
        return "signin";
    }

    // 📝 Signup Page
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    // 📝 Handle Signup
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user, Model model) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "signup";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/signin?success";
    }

        @GetMapping("/signout")
    public String signOutPage() {
        // Spring Security intercepts this URL
        // and performs logout automatically
        return "redirect:/signin?signout";
    }
}

