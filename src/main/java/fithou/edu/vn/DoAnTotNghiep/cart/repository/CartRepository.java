package fithou.edu.vn.DoAnTotNghiep.cart.repository;

import fithou.edu.vn.DoAnTotNghiep.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
@Repository
public interface CartRepository extends JpaRepository<CartItem, CartItem.CartItemKey> {
    String deleteAllByUserId(String userId);

    Collection<CartItem> findAllByUserId(String userId);

    Optional<CartItem> findByUserIdAndProductOptionId(String userId, String productOptionId);

    void deleteAllByProductOptionIdIn(Collection<String> productOptionIds);

}
