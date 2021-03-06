package smartThings.gestNuT.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
class HomeController {

    private static final String HOME_VIEW_NAME = "home/homeNotSignedIn";

    @ModelAttribute("module")
    String module() {
        return "home";
    }

    @RequestMapping(value="/")
    public ModelAndView showHomeUserPage(ModelAndView modelAndView) {
        modelAndView.setViewName(HOME_VIEW_NAME);
        return modelAndView;                          
    }

    @RequestMapping("/authenticate")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {
        String role =  authResult.getAuthorities().toString();

        if(role.contains("ROLE_ADMIN")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "homeAdminSignedIn"));                            
        }
        else if(role.contains("ROLE_USER")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "homeUserSignedIn"));       
        }
    }
}
