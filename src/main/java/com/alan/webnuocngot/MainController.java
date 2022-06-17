package com.alan.webnuocngot;

import com.alan.webnuocngot.admin_manager.Admin;
import com.alan.webnuocngot.admin_manager.AdminController;
import com.alan.webnuocngot.admin_manager.AdminService;
import com.alan.webnuocngot.customer_manager.CustomerService;
import com.alan.webnuocngot.oder_manager.OderService;
import com.alan.webnuocngot.product_manager.Product;
import com.alan.webnuocngot.product_manager.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
//    field
    @Autowired private CustomerService customerService;
    @Autowired private ProductService productService;
    @Autowired private AdminService adminService;
    @Autowired private OderService oderService;
    @Autowired private AdminController adminController;

//    method
    @GetMapping("/")
    public String showView(Model model) {
        List<Product> listProduct = productService.listAll();
        model.addAttribute("listProduct", listProduct);
        return "index";
    }

//   Khởi tạo ra trang thống kê.
    @GetMapping("/manager/Statistical")
    public String statistical(Model model) {
        int userNumber = customerService.countAllCustomer();
        int adminNumber = adminService.countAllAdmin();
        int pCodeNumber = productService.countDistinctPCode();
        int countAllQuantity = productService.countQuantity();
//        int oderNumber = oderService.countAllOder();
        if (adminController.checkLogin())
            return "redirect:/admin/login";

        List<String> listQuantityByCode = new ArrayList<>();
        List<String> listCode = productService.findDistinctCode();
        for (String code : listCode) {
            int num = productService.countQuantityByCode(code);
            String quantity = code +": "+ num;
            listQuantityByCode.add(quantity);
        }

        model.addAttribute("userNumber", userNumber);
        model.addAttribute("adminNumber", adminNumber);
        model.addAttribute("productNumber", countAllQuantity);
        model.addAttribute("pCodeNumber", pCodeNumber);
        model.addAttribute("listQuantityByCode", listQuantityByCode);
//        model.addAttribute("oderNumber", oderNumber);

        if (adminController.checkSuperAdmin())
            model.addAttribute("superAdmin", "ok");
        return "statistical_manager/statistical";
    }
}
