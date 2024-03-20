package fithou.edu.vn.DoAnTotNghiep.product.commands.updateProduct;

import fithou.edu.vn.DoAnTotNghiep.category.repository.CategoryRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Brand;
import fithou.edu.vn.DoAnTotNghiep.product.repository.BrandRepository;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UpdateProductCommandHandler implements IRequestHandler<UpdateProductCommand, String> {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Override
    @Transactional
    public HandleResponse<String> handle(UpdateProductCommand updateProductCommand) throws Exception {
        var existProduct = productRepository.findById(updateProductCommand.getId());
        if (existProduct.isEmpty()) {
            return HandleResponse.error("Sản phẩm không tồn tại");
        }
        if (updateProductCommand.getCategoryId() != existProduct.get().getCategory().getId()) {
            var existCategory = categoryRepository.findById(updateProductCommand.getCategoryId());
            if (existCategory.isEmpty()) {
                return HandleResponse.error("Danh mục sản phẩm không tồn tại");
            }
            existProduct.get().setCategory(existCategory.get());
        }
        if (updateProductCommand.getBrandId() != existProduct.get().getBrand().getId()) {
            var exitsBrand = brandRepository.findById(updateProductCommand.getBrandId());
            if(exitsBrand.isEmpty()){
                return HandleResponse.error("Thương hiệu không tồn tại");
            }
            existProduct.get().setBrand(exitsBrand.get());
        }
        existProduct.get().setName(updateProductCommand.getName());
        existProduct.get().setDescription(updateProductCommand.getDescription().replaceAll("<[^>]*>", ""));
        existProduct.get().setPrice(updateProductCommand.getPrice());
        existProduct.get().setDiscount(updateProductCommand.getDiscount());
        existProduct.get().setImageUrl(updateProductCommand.getImageUrl());
        List<String> currentImage = existProduct.get().getImages();
        if (currentImage == null) {
            currentImage = new ArrayList<>() ;
        }
        if (updateProductCommand.getImages() != null && !updateProductCommand.getImages().isEmpty()) {
            currentImage.clear();
            currentImage.addAll(updateProductCommand.getImages());
        }
        existProduct.get().setImages(currentImage);
        existProduct.get().setStatus(updateProductCommand.getStatus());
        productRepository.save(existProduct.get());
        return HandleResponse.ok(existProduct.get().getId());
    }
}
