package com.alan.webnuocngot.product_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired private ProductRepository repo;

    public List<Product> listAll() {
        return (List<Product>) repo.findAll();
    }
    public Product getIdProduct(Integer id) {
        return repo.findByIdP(id);
    }
    public Product save(Product product) {
        repo.save(product);
        return product;
    }
    public Product get(Integer id) throws ProductNotFoundException {
        Optional<Product> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ProductNotFoundException("Could not find any product with ID "+ id);
    }
    public boolean kiemTra(Integer id) {
        return repo.existsById(id);
    }
    public void delete(Integer id) throws ProductNotFoundException {
        if (repo.findById(id).equals("")) {
            throw new ProductNotFoundException("Could not find any product with ID "+ id);
        }
        repo.deleteById(id);
    }

    public int countAllProduct() {
        return (int) repo.count();
    }

    public int countDistinctPCode() {
        return repo.countDistinctCode();
    }

    public List<String> findDistinctCode() {
        return repo.findDistinctCode();
    }

    public int countQuantity() {
        return repo.countAllQuantityProduct();
    }

    public int countQuantityByCode(String code) {
        return repo.countAllQuantityProductByCode(code);
    }
}
