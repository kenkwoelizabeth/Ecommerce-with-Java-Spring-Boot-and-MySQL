package com.ecommerce.user;


import com.ecommerce.exception.UserNotFoundException;
import com.ecommerce.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model) {

        return "home";
    }

    @GetMapping("/users")
    public String listAll(Model model) {
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        List<Role> listRoles = userService.listRoles();
        User user = new User();
        user.setEnabled(true);
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("pageTitle", "Create New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        System.out.println(user);
        userService.save(user);
//message after saving user
        redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable(name = "id") Integer id,Model model,
                           RedirectAttributes redirectAttributes) {
try{
    User user = userService.get(id);
    List<Role> listRoles = userService.listRoles();

    model.addAttribute("user", user);
    model.addAttribute("pageTitle", "Edit user (ID: " + id + ")");
    model.addAttribute("listRoles", listRoles);

    return "user_form";
}catch (UserNotFoundException ex) {
//message after saving user
    redirectAttributes.addFlashAttribute("message", ex.getMessage());


    return "redirect:/users";

}
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Integer id,Model model,
                           RedirectAttributes redirectAttributes) {


            try{
              userService.delete(id);
               redirectAttributes.addFlashAttribute("message",
                       "The user ID " + id + "has been deleted successfully ");

            }catch (UserNotFoundException ex) {
//message after saving user
                redirectAttributes.addFlashAttribute("message", ex.getMessage());
            }

                return "redirect:/users";

            }
        }


