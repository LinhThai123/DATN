package fithou.edu.vn.DoAnTotNghiep.receipt.service;

import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.Receipt;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReceiptService {
    Page<Receipt> adminGetListReceipt(String employeeName, String supplierName, int page);

    public List<Receipt> getListReceipt () ;
}
