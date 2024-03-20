package fithou.edu.vn.DoAnTotNghiep.receipt.command.createReceipt;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import fithou.edu.vn.DoAnTotNghiep.common.response.ReceiptResponse;
import fithou.edu.vn.DoAnTotNghiep.receipt.command.createReceiptItem.CreateReceiptItemCommand;
import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Supplier;
import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReceiptCommand implements IRequest<ReceiptResponse> {

    private String id;

    private String supplierId;

    private String employeeId;

    private String note ;

    private Integer total;

    private List<CreateReceiptItemCommand> createReceiptItemCommands ;

}
