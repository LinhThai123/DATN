package fithou.edu.vn.DoAnTotNghiep.product.commands.createProduct;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateProductCommand implements IRequest<String> {

    @NotEmpty(message = "Tên sản phẩm không được để trống")
    private String name;

    @NotEmpty(message = "Mô tả sản phẩm không được để trống")
    private String description;

    @NotNull(message = "Giá bán của sản phẩm không được để trống")
    @Min(value = 1000, message = "Giá sản phẩm lớn hơn 1000")
    private int price;

    private int status;

    @Min(value = 0, message = "Giảm giá sản phẩm phải lớn hơn 0")
    @Max(value = 100, message = "Giảm giá sản phẩm phải nhỏ hơn 100")
    @Builder.Default
    private int discount = 0;

//    @NotEmpty(message = "Hình ảnh của sản phẩm không không được để trống")
//    @URL(message = "Ảnh sản phẩm không hợp lệ")
    private String imageUrl;

    private List<String> images;

    @NotNull(message = "Danh mục sản phẩm không được để trống")
    private String categoryId;

    @NotEmpty(message = "Thương hiệu sản phẩm không được để trống")
    private String brandId;

    private int stock = 0 ;
}
