package com.alan.webnuocngot.product_manager;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findAllByGas(String gas);
    Product findByIdP(Integer id);

    @Query(value = "SELECT count(distinct p.pCode) FROM Product p")
    int countDistinctCode();

    @Query(value = "SELECT sum(p.quantity) from Product p")
    int countAllQuantityProduct();

    @Query(value = "SELECT sum(p.quantity) from Product p where p.pCode = :code")
    int countAllQuantityProductByCode(@Param("code") String code);

    @Query(value = "SELECT distinct p.pCode from Product p")
    List<String> findDistinctCode();
}
