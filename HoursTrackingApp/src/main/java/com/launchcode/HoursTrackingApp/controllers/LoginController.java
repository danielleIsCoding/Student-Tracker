package com.launchcode.HoursTrackingApp.controllers;


import com.launchcode.HoursTrackingApp.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController
{
//    @Autowired
//    private UserService userService;

    @RequestMapping("/login")
    public String getLogin (Model model) {
        {
            User user = new User();
            model.addAttribute("user", user);

            return "login";
        }
    }

//    @RequestMapping("register")
//    public String getRegister (ModelMap model)
//    {
//        User user = new User();
//        model.put("user", user);
//
//        return "register";
//    }
//
//    @RequestMapping(value="/register", method=RequestMethod.POST)
//    public String postRegister (@ModelAttribute User user, ModelMap model)
//    {
//        if (!StringUtils.isEmpty(user.getPassword()) && !StringUtils.isEmpty(user.getConfirmPassword()))
//        {
//            if (!user.getPassword().equals(user.getConfirmPassword()))
//            {
//                model.put("error", "Passwords don't match");
//                return "register";
//            }
//        }
//
//        if (StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getConfirmPassword()))
//        {
//            model.put("error", "You must choose a password");
//            return "register";
//        }
//
//        user = userService.saveUser(user);
//
//        // dynamically logging in the user via Spring Security
//        Authentication auth =
//                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//
//        SecurityContextHolder.getContext().setAuthentication(auth);
//
//        return "redirect:/budgets";
//    }
}
