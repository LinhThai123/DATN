package fithou.edu.vn.DoAnTotNghiep.cart.endpoint;

import fithou.edu.vn.DoAnTotNghiep.cart.commands.addToCart.AddToCartCommand;
import fithou.edu.vn.DoAnTotNghiep.cart.commands.clearCart.ClearCartCommand;
import fithou.edu.vn.DoAnTotNghiep.cart.commands.removeItem.RemoveItemFromCartCommand;
import fithou.edu.vn.DoAnTotNghiep.cart.commands.updateCartItemQuantity.UpdateCartItemQuantityCommand;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/v1/cart")
@AllArgsConstructor
//@Secured("CAN_ORDER")
public class CartApiController {

    @Autowired
    private ISender sender;

    @PostMapping("/add-to-cart")
    public ResponseEntity<Void> addToCart(@Valid @io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody AddToCartCommand addToCartCommand) {
        sender.send(addToCartCommand).orThrow();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clear-cart")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> clearCart() {
        sender.send(new ClearCartCommand()).orThrow();
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update-item-quantity")
    public ResponseEntity<Void> updateCartItemQuantity(@Valid @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody UpdateCartItemQuantityCommand updateCartItemQuantityCommand) {
        sender.send(updateCartItemQuantityCommand).orThrow();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productOptionId}")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> removeItemFromCart(@PathVariable String productOptionId) {
        sender.send(new RemoveItemFromCartCommand(productOptionId)).orThrow();
        return ResponseEntity.noContent().build();
    }
}

