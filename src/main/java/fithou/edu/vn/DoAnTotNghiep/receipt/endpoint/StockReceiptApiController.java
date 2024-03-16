package fithou.edu.vn.DoAnTotNghiep.receipt.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.common.response.ReceiptResponse;
import fithou.edu.vn.DoAnTotNghiep.receipt.command.createReceipt.CreateReceiptCommand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/stock-receipt")
@AllArgsConstructor
public class StockReceiptApiController {

    private final ISender sender;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ReceiptResponse> createStockReceipt(@Valid @RequestBody CreateReceiptCommand command) throws Exception {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }
}
