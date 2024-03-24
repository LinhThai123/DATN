package fithou.edu.vn.DoAnTotNghiep.order.commands.createOrder;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import fithou.edu.vn.DoAnTotNghiep.payment.entity.enums.PaymentMethod;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderCommand implements IRequest<String> {
    @Getter
    @Setter
    public static class OrderItem {
        private int productOptionId;
        private int quantity;
    }

    @NotEmpty(message = "Cần chọn sản phẩm")
    public List<OrderItem> orderItems;

    @NotEmpty(message = "Cần nhập tên khách hàng")
    private String customerName;

    @NotEmpty(message = "Cần nhập địa chỉ")
    private String address;

    @NotEmpty(message = "Cần nhập số điện thoại")
    @Pattern(regexp = "^(0|\\+84)\\d{9,11}$", message = "Số điện thoại không đúng định dạng")
    private String phoneNumber;

    @Email(message = "Email không đúng định dạng")
    @NotEmpty(message = "Cần nhập email")
    private String email;

    private String note;

    private String promotionCode;

    @NotNull(message = "Phương thức thanh toán không được để trống")
    private PaymentMethod paymentMethod = PaymentMethod.COD;

    @NotNull(message = "Cần chọn dịch vụ vận chuyển")
    @NotBlank(message = "Cần chọn dịch vụ với chuyển")
    private String shipServiceId;
}
