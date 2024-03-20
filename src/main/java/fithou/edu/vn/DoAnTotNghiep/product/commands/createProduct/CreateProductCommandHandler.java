package fithou.edu.vn.DoAnTotNghiep.product.commands.createProduct;

import com.github.slugify.Slugify;
import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.category.repository.CategoryRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import fithou.edu.vn.DoAnTotNghiep.product.repository.BrandRepository;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
@AllArgsConstructor
public class CreateProductCommandHandler implements IRequestHandler<CreateProductCommand, String> {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Override
    @Transactional
    public HandleResponse<String> handle(CreateProductCommand command) throws Exception {
        var exitsCategory = categoryRepository.findById(command.getCategoryId());
        if(exitsCategory.isEmpty()){
            return HandleResponse.error("Danh mục sản phẩm không tồn tại");
        }
        var exitsBrand = brandRepository.findById(command.getBrandId());
        if(exitsBrand.isEmpty()){
            return HandleResponse.error("Thương hiệu sản phẩm không tồn tại");
        }
        Slugify slugify = new Slugify();
        String slug = slugify.slugify(command.getName());
        var exitsSlug = productRepository.findBySlug(slug);
        while (exitsSlug.isPresent()) {
            slug = slug + "-" + new Random().nextInt(1000);
            exitsSlug = productRepository.findBySlug(slug);
        }
        var product = new Product() ;
        product.setName(command.getName());
        product.setSlug(slug);
        String descriptionWithoutHtml = command.getDescription().replaceAll("<[^>]*>", "");
        product.setDescription(descriptionWithoutHtml);
        product.setMaSerial(UUID.randomUUID().toString());
        product.setPrice(command.getPrice());
        product.setImageUrl(command.getImageUrl());
        List<String> currentImage = product.getImages();
        if (currentImage == null) {
            currentImage = new ArrayList<>(); // Khởi tạo danh sách hình ảnh nếu cần
        }
        // Thêm hình ảnh mới vào danh sách currentImage
        if (command.getImages() != null) {
            currentImage.addAll(command.getImages());
        }
        product.setImages(currentImage);

        product.setStatus(command.getStatus());

        product.setDiscount(command.getDiscount());

        product.setCategory(exitsCategory.get());

        product.setBrand(exitsBrand.get());

        product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        product.setModifiedDate(new Timestamp(System.currentTimeMillis()));

        productRepository.save(product);

        return HandleResponse.ok(product.getId());
    }
}
