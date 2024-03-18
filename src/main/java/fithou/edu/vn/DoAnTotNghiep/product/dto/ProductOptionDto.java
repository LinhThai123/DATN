package fithou.edu.vn.DoAnTotNghiep.product.dto;

import fithou.edu.vn.DoAnTotNghiep.common.dto.AuditableDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ProductOptionDto extends AuditableDto {

    private String productOptionId;

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

    private LocalDateTime deletedDate = null;
}
