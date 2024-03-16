package fithou.edu.vn.DoAnTotNghiep.product.commands.createSpecification;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSpecificationCommand implements IRequest<String> {

    private String screen_size;

    private String resolution_screen;

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

    @NotNull(message = "Sản phẩm không được để trống")
    private String productId;

}
