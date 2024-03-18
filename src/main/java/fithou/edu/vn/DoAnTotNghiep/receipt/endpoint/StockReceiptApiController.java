package fithou.edu.vn.DoAnTotNghiep.receipt.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.common.response.ReceiptResponse;
import fithou.edu.vn.DoAnTotNghiep.receipt.command.createReceipt.CreateReceiptCommand;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.Receipt;
import fithou.edu.vn.DoAnTotNghiep.receipt.service.ReceiptService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/stock-receipt")
@AllArgsConstructor
public class StockReceiptApiController {

    private final ISender sender;

    @Autowired
    private ReceiptService receiptService;
    @GetMapping()
    @ResponseBody
    public List<Receipt> getReceiptPage () {
        return receiptService.getListReceipt();
    }
    @PostMapping("/add")
    public ResponseEntity<ReceiptResponse> createStockReceipt(@Valid @RequestBody CreateReceiptCommand command) throws Exception {
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }
}
