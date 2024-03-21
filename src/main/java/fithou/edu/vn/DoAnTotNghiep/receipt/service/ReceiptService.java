package fithou.edu.vn.DoAnTotNghiep.receipt.service;

import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.Receipt;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.ReceiptItem;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface ReceiptService {
    Page<Receipt> adminGetListReceipt(String employeeName, String supplierName, int page);
    public List<Receipt> getListReceipt () ;
    public Receipt getReceiptById (String id) ;
    public Set<ProductOption> getProductOptionByReceiptId (String receiptId);
    public List<ReceiptItem> getReceiptItemByReceiptId (String receiptId) throws NotFoundException;
}
