package fithou.edu.vn.DoAnTotNghiep.product.commands.createProductImage;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class createProductImageCommandHandller implements IRequestHandler<CreateProductImageCommand, String> {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public HandleResponse<String> handle(CreateProductImageCommand command) throws Exception {
        Optional<Product> exitsProductOptional = productRepository.findById(command.getId());
        if (exitsProductOptional.isEmpty()) {
            return HandleResponse.error("Không tìm thấy sản phẩm");
        }

        Product exitsProduct = exitsProductOptional.get();

        List<String> currentImage = exitsProduct.getImages();
        if (currentImage == null) {
            currentImage = new ArrayList<>(); // Khởi tạo danh sách hình ảnh nếu cần
        }
        // Thêm hình ảnh mới vào danh sách currentImage
        if (command.getImages() != null) {
            currentImage.addAll(command.getImages());
        }
        exitsProduct.setImages(currentImage);

        productRepository.save(exitsProduct);
        return HandleResponse.ok();
    }

}
