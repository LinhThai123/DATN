package fithou.edu.vn.DoAnTotNghiep.receipt.service.impl;

import fithou.edu.vn.DoAnTotNghiep.common.mapper.ReceiptMapper;
import fithou.edu.vn.DoAnTotNghiep.config.Contant;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Product;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.Receipt;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.ReceiptItem;
import fithou.edu.vn.DoAnTotNghiep.receipt.repository.ReceiptItemRepository;
import fithou.edu.vn.DoAnTotNghiep.receipt.repository.ReceiptRepository;
import fithou.edu.vn.DoAnTotNghiep.receipt.service.ReceiptService;
import jakarta.persistence.EntityNotFoundException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ReceiptItemRepository receiptItemRepository;

    @Autowired
    private ReceiptMapper receiptMapper;
    @Override
    public Page<Receipt> adminGetListReceipt(String employeeName, String supplierName, int page) {
        page--;
        if (page <= 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, Contant.LIMIT_BLOG , Sort.by("createdDate").descending());
        return receiptRepository.findByEmployeeNameAndSupplierName(employeeName, supplierName, pageable);
    }

    @Override
    public List<Receipt> getListReceipt() {
        return receiptRepository.findAll();
    }

    @Override
    public Receipt getReceiptById(String id) {
        Optional<Receipt> receipt = receiptRepository.findById(id) ;
        if (!receipt.isPresent()) {
            throw new RuntimeException("Phiếu nhập hàng không tồn tại");
        }
        return receipt.get();
    }

    @Override
    public Set<ProductOption> getProductOptionByReceiptId(String receiptId) {
        Receipt receipt = receiptRepository.findById(receiptId).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy phiếu nhập hàng"));
        Set<ProductOption> productOptions = new HashSet<>();
        for (ReceiptItem item : receipt.getReceiptItems()) {
            productOptions.add(item.getProductOption());
        }
        return productOptions;
    }

    @Override
    public List<ReceiptItem> getReceiptItemByReceiptId(String receiptId) throws NotFoundException {
        return receiptItemRepository.getReceiptItemByReceiptId(receiptId);
    }
}
