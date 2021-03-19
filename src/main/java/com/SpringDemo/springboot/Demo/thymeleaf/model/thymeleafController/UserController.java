package com.SpringDemo.springboot.Demo.thymeleaf.model.thymeleafController;


import com.SpringDemo.springboot.Demo.models.User;
import com.SpringDemo.springboot.Demo.service.orderService.OrderService;
import com.SpringDemo.springboot.Demo.service.userService.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private OrderService orderService;

    public UserController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/list")
    public String listUsers(Model theModel) {
        List<User> theUsers = userService.findAll();
        theModel.addAttribute("users", theUsers);
        return "users/list-users";

    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        User theUser = new User();
        theModel.addAttribute("user", theUser);
        return "users/user-form";
    }

    @PostMapping("/save")
    public String SaveUser(@ModelAttribute("user") User theUser) {
        userService.save(theUser);
        return "redirect:/users/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("ID") int theId) {

        userService.deleteById(theId);

        return "redirect:/users/list";

    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("ID") int theId,
                                    Model theModel) {

        User theUser = userService.findById(theId);

        theModel.addAttribute("user", theUser);

        return "users/user-form";
    }


}
