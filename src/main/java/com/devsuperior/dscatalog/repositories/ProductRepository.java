package com.devsuperior.dscatalog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cats WHERE "
			+ "(COALESCE(:categories) IS NULL OR cats IN :categories) AND "
			+ "(:name = '' OR LOWER(obj.name) LIKE LOWER(CONCAT('%',:name,'%'))) ")
	Page<Product> find(List<Category> categories, String name, Pageable pageable);
	
	
	//encodeURIComponent("PC Gamer") = PC%20Gamer  -> usar na app que chamar a rota
	//@Query("SELECT obj FROM Product obj JOIN FECTH obj.categories WHERE obj IN: products")
	//List<Product> findProductsCategories(List<Product> products);
}
