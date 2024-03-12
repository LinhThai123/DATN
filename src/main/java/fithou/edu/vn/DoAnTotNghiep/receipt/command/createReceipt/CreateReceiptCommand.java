package fithou.edu.vn.DoAnTotNghiep.receipt.command.createReceipt;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReceiptCommand implements IRequest<String> {

    public static class CreateItemCommand {
        @NotNull(message = "Yêu cầu chọn sản phẩm")
        public String productOptionId;

        @Min( value = 1, message = "Yêu cầu nhập số lượng sản phẩm")
        public int quantity;

        @Min( value = 1, message = "Yêu cầu nhập giá sản phẩm")
        public int price;

    }
    private String note;

    @NotNull(message = "Yêu cầu chọn nhà cung cấp")
    public String supplierId;

    @Size(min = 1, message = "Yêu cầu chọn ít nhất 1 sản phẩm để nhập kho")
    private java.util.List<CreateItemCommand> stockReceiptItems;

}
