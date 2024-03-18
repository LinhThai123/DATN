package fithou.edu.vn.DoAnTotNghiep.receipt.command.createReceiptItem;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReceiptItemCommand implements IRequest<String> {

    private String id;

    @Min(value = 1, message = "Số lượng nhập phải lớn hơn 0")
    @Max(value = 1000 , message = "Số lượng nhập phải bé hơn 1000")
    private int quantity;

    @Min(value = 0 , message = "Giá nhập phải lớn hơn 0")
    @Max(value = 1000000, message = "Giá nhập phải lớn hơn 1 triệu")
    private int price;

    private String productOptionId;

}
