package fithou.edu.vn.DoAnTotNghiep.promotion.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.promotion.commands.createPromotion.CreatePromotionCommand;
import fithou.edu.vn.DoAnTotNghiep.promotion.commands.deletePromotion.DeletePromotionCommand;
import fithou.edu.vn.DoAnTotNghiep.promotion.commands.toggleDisablePromotion.ToggleDisablePromotion;
import fithou.edu.vn.DoAnTotNghiep.promotion.commands.updatePromotion.UpdatePromotionCommand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/promotion")
@AllArgsConstructor
public class PromotionApiController {
    @Autowired
    private ISender sender;

    @PostMapping("/add")
    public ResponseEntity<String> createPromotion (@Valid @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody CreatePromotionCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }
    @PutMapping("update")
    public ResponseEntity<Void> updatePromotion(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody @Valid UpdatePromotionCommand command) {
        var result = sender.send(command).orThrow();
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{promotionId}/toggleDisable")
    public ResponseEntity<String> toggleDisablePromotion(@PathVariable String promotionId) {
        var result = sender.send(new ToggleDisablePromotion(promotionId));
        return ResponseEntity.ok(result.orThrow());
    }

    @DeleteMapping("/{promotionId}")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deletePromotion(@PathVariable String promotionId) {
        var result = sender.send(new DeletePromotionCommand( promotionId)).orThrow();
        return ResponseEntity.noContent().build();
    }
}
