package com.alan.webnuocngot.admin_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired private AdminRepository repo;

    public List<Admin> listAdmin() {
        return (List<Admin>) repo.findAll();
    }
    public List<Admin> listAdminNotId(Integer id) {return repo.findAllByNotId(id);}
    public List<String> listNameAdmin() {
        return (List<String>) repo.findAllNameAdmin();
    }
    public void save(Admin admin) {
        repo.save(admin);
    }
    public List<Admin> searchAllNameAdmin(String name){
        return (List<Admin>) repo.findAllByNameAdmin(name);
    }
    public Admin getAdmin(Integer id) {
        return repo.findByIdA(id);
    }
    public int searchEmail(String email) {
       return repo.countByEmail(email);
    }

    public Admin getMe(String email){
        return repo.findByEmail(email);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public int  countAllAdmin() {
        return (int) repo.count();
    }
}
