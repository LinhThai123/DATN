package fithou.edu.vn.DoAnTotNghiep.product.repository;

import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import fithou.edu.vn.DoAnTotNghiep.product.dto.ProductDTO;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Brand;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findBySlug(String slug);

    @Query("SELECT p FROM Product p")
    List<Product> getAllProducts();

    @Query("SELECT p.id AS productId, p.name AS productName, po.id AS productOptionId " +
            "FROM Product p LEFT JOIN p.productOptions po")
    List<Tuple> findProductsWithProductOptionIds();

    @Modifying
    @Query(value = "update product p set p.deleted_date = null where p.id = ?1", nativeQuery = true)
    void recoveryByProductId(String Id);

    Page<Product> findByNameContaining (String name, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT DISTINCT product.*\n" +
            "FROM product\n" +
            "WHERE product.id LIKE CONCAT('%',?1,'%') AND product.name LIKE CONCAT('%',?2,'%') AND product.category_id LIKE ?3 AND product.brand_id LIKE ?4 \n" +
            "ORDER BY ?5 ?6 \n" +
            "LIMIT ?7\n" +
            "OFFSET ?8")
    public List<Product> adminGetListProduct(String id, String name, String category, String brand, String order, String direction, int limit, int offset);

    @Query(nativeQuery = true, value = "SELECT COUNT( DISTINCT product.id) FROM product " +
            "INNER JOIN category ON category.id = product.category_id " +
            "INNER JOIN brand ON brand.id = product.brand_id " +
            "WHERE product.id LIKE CONCAT('%', ?1, '%') " +
            "AND product.name LIKE CONCAT('%', ?2, '%') " +
            "AND product.category_id = ?3 " +
            "AND product.brand_id = ?4 " )
    public int countAdminGetListProduct(String id, String name, String category, String brand);
}
