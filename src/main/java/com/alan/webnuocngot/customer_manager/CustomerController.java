package com.alan.webnuocngot.customer_manager;

import com.alan.webnuocngot.admin_manager.AdminController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired private CustomerService service;
    @Autowired private AdminController adminController;
    @GetMapping("/customer_manager")
    public String customerList(Model model) {
        if (adminController.checkLogin())
            return "redirect:/admin/login";
        List<Customer> listCustomer = service.listCustomer();
        model.addAttribute("listCustomer", listCustomer);

        return "customer_manager/customer_manager";
    }
    @GetMapping("/customer_manager/delete/{id}")
    public String CustomerDelete(@PathVariable("id") Integer id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("message", "the user ID "+ id +" has been deleted.");

        return "redirect:/customer_manager";
    }
}
