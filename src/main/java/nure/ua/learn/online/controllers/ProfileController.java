package nure.ua.learn.online.controllers;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.DataValidator;
import nure.ua.learn.online.dto.ProfileData;
import nure.ua.learn.online.entities.User;
import nure.ua.learn.online.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final DataValidator dataValidator;

    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }

    @GetMapping("/editProfile")
    public String editProfile() {
        return "editProfile";
    }

    @PostMapping("/editProfile")
    public RedirectView editProfile(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        ProfileData profileData = new ProfileData(request);
        Map<String, String> validationErrors = dataValidator.validateData(profileData);
        if (!validationErrors.isEmpty()) {
            request.getSession().setAttribute("profileData", profileData);
            request.getSession().setAttribute("validationErrors", validationErrors);
            return new RedirectView("editProfile");
        }
        currentUser.setFirstName(profileData.getFirstName());
        currentUser.setLastName(profileData.getLastName());
        currentUser.setEmail(profileData.getEmail());
        userService.updateUser(currentUser);
        request.getSession().setAttribute("currentUser", currentUser);
        return new RedirectView("index");
    }
}
