package fithou.edu.vn.DoAnTotNghiep.product.commands.createProductImage;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductImageCommand implements IRequest<String> {
    private String id ;
    private List<String> images;
}
