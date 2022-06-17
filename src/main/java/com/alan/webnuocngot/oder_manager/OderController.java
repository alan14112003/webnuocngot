package com.alan.webnuocngot.oder_manager;

import com.alan.webnuocngot.admin_manager.AdminController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OderController {
    @Autowired OderService service;
    @Autowired AdminController adminController;

//    khởi tạo trang danh sách oder.
    @GetMapping("/admin/order")
    public String listOrder(Model model) {
        if (adminController.checkLogin())
            return "redirect:/admin/login";
        List<Oder> oderList = service.findAllOrder();
        model.addAttribute("oderList", oderList);
        return "oder_manager/oder_manager";
    }
//    Khởi tạo trang hóa đơn.
    @GetMapping("/bill/{id}")
    public String billOCP(@PathVariable("id") Integer id, Model model) {
        double total = 0;
        if (adminController.checkLogin())
            return "redirect:/admin/login";
        String billO = service.findO(id);
        String[] billOArr = billO.split(",");
        model.addAttribute("billOArr", billOArr);

        String billC = service.findC(id);
        String[] billCArr = billC.split(",");
        model.addAttribute("billCArr", billCArr);

        List<String> billOlP = service.findOlP(id);
        List<List<String>> billOlPArr = new ArrayList<>();
        for (int j = 0; j < billOlP.size(); j ++) {
            String bill = billOlP.get(j);
            String[] billtemp = bill.split(",");
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(j+1));
            for (int i = 0; i < billtemp.length; i++) {
                list.add(billtemp[i]);
            }
            billOlPArr.add(list);
        }
        model.addAttribute("billOlPArr", billOlPArr);
        for (int i = 0; i < billOlPArr.size(); i++) {
            double totalP = Double.parseDouble(billOlPArr.get(i).get(3)) * Integer.parseInt(billOlPArr.get(i).get(5));
            total += totalP;
        }
        model.addAttribute("total", total+20000);
        return "oder_manager/bill";
    }
}
