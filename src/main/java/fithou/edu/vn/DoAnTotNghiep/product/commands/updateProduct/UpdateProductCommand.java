package fithou.edu.vn.DoAnTotNghiep.product.commands.updateProduct;

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
public class UpdateProductCommand implements IRequest<String> {

    private String id;

    @NotEmpty(message = "Tên sản phẩm không được để trống")
    @Size(min = 5, max = 100, message = "Tên sản phẩm phải tu 5 đến 100 ký tự")
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

    @NotEmpty(message = "Hình ảnh của sản phẩm không không được để trống")
    @URL(message = "Ảnh sản phẩm không hợp lệ")
    private String imageUrl;

    @NotEmpty(message = "Danh mục sản phẩm không được để trống")
    private String categoryId;

    @NotEmpty(message = "Thương hiệu sản phẩm không được để trống")
    private String brandId;

    private List<String> images;
}
