package com.SpringDemo.springboot.Demo.thymeleaf.model.thymeleafController;


import com.SpringDemo.springboot.Demo.dao.OrderItemRepository;
import com.SpringDemo.springboot.Demo.dao.OrderRepository;
import com.SpringDemo.springboot.Demo.models.Order;
import com.SpringDemo.springboot.Demo.models.OrderItem;
import com.SpringDemo.springboot.Demo.models.User;
import com.SpringDemo.springboot.Demo.service.orderItemService.OrderItemService;
import com.SpringDemo.springboot.Demo.service.orderService.OrderService;
import com.SpringDemo.springboot.Demo.service.userService.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private OrderItemService orderItemService;

    public OrderController(OrderRepository orderRepository, OrderItemService orderItemService, OrderItemRepository orderItemRepository, OrderService orderService) {
        this.orderService = orderService;
        this.orderItemRepository = orderItemRepository;
        this.orderItemService = orderItemService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/list")
    public String listOrders(Model theModel) {
        List<Order> theOrders = orderService.findAll();
        theModel.addAttribute("orders", theOrders);
        return "orders/list-theOrders";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        theModel.addAttribute("orderItem", new OrderItem());
        return "orders/order-form";
    }

    @PostMapping("/save")
    public String SaveOrder(@ModelAttribute("orderItem") OrderItem theOrderItem) {
        Order newOrder = new Order();
        User user = new User();
        user.setId(1);
        newOrder.setUser(user);
        orderRepository.save(newOrder);

        OrderItem newOrderItem = new OrderItem();
        newOrderItem.setOrdersItemId(newOrder.getId());
        newOrderItem.setQuantity(theOrderItem.getQuantity());
        newOrderItem.setPrice(theOrderItem.getPrice());
        newOrderItem.setItems(theOrderItem.getItems());
        orderItemRepository.save(newOrderItem);

        return "redirect:/orders/list";

    }


    @PostMapping("/delete")
    public String delete(@RequestParam("ID") int theId) {

        orderService.deleteById(theId);

        return "redirect:/orders/list";

    }

}
