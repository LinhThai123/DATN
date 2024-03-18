package fithou.edu.vn.DoAnTotNghiep.product.dto;

import fithou.edu.vn.DoAnTotNghiep.category.dto.CategoryDto;
import fithou.edu.vn.DoAnTotNghiep.common.dto.AuditableDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductBriefDto extends AuditableDto {
    private String productId;

    private String name;

    private String slug;

    private double price;

    private int discount;

    private String imageUrl;

    private CategoryDto category ;

    private LocalDateTime deleteDate;

    private int totalSold;

    public String getVietnamesePrice() {
        return String.format("%,dđ", (int) price);
    }
    public int getFinalPrice() {
        return (int) (price * (100.0 - discount) / 100);
    }
}
