package fithou.edu.vn.DoAnTotNghiep.receipt.service.impl;

import fithou.edu.vn.DoAnTotNghiep.config.Contant;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.Receipt;
import fithou.edu.vn.DoAnTotNghiep.receipt.repository.ReceiptRepository;
import fithou.edu.vn.DoAnTotNghiep.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;
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
}
