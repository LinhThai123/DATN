package fithou.edu.vn.DoAnTotNghiep.product.commands.createProductOption;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductOptionCommand implements IRequest<String> {

    private String screen_size;

    private String resolution_screen;

    private String rom;

    private String cpu;

    private String size;

    private String camera_after;

    private String camera_before;

    private String pin;

    private String charging_port;

    private String os;

    private String bluetooth;

    private Double weight;

    private String chip;

    private String ram;

    private String color;

    private int stock = 0 ;

    @NotNull(message = "Sản phẩm không được để trống")
    private String productId;
}
