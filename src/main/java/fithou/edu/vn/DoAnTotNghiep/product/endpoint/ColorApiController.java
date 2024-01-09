package fithou.edu.vn.DoAnTotNghiep.product.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.product.commands.createColor.CreateColorCommand;
import fithou.edu.vn.DoAnTotNghiep.product.dto.ColorDto;
import fithou.edu.vn.DoAnTotNghiep.product.query.getAllColors.GetAllColorQuery;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/v1/color")
public class ColorApiController {

    @Autowired
    private ISender sender;

    @GetMapping()
    public ResponseEntity<Collection<ColorDto>> getAllColors() {
        var result = sender.send(new GetAllColorQuery()).orThrow();
        return ResponseEntity.ok(result);
    }
    @PostMapping("/add")
    public ResponseEntity<String> createColor (@Valid @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody CreateColorCommand command) {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }
}
