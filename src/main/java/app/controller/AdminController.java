package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;




@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public ModelAndView index(ModelAndView modelAndView) {

        modelAndView.setViewName("admin");
        modelAndView.addObject("users", userService.getAllUsers());
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView getNewPage(ModelAndView modelAndView) {
        modelAndView.setViewName("new");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("roles", roleService.getAllRoles());

        return modelAndView;
    }

    @PostMapping()
    public ModelAndView newPage(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");

        userService.save(user);

        return modelAndView;
    }


    @GetMapping("/{id}/edit")
    public ModelAndView getEdit(ModelAndView modelAndView, @PathVariable("id") int id) {
        modelAndView.addObject("user", userService.getUser(id));
        modelAndView.addObject("allroles", roleService.getAllRoles());
        modelAndView.addObject("roles", roleService.getAllRoles());
        modelAndView.setViewName("/edit");
        return modelAndView;
    }

    @PatchMapping("/{id}")
    public ModelAndView edit(@ModelAttribute("user") User user) {
        userService.update(user);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        userService.delete(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }
}
