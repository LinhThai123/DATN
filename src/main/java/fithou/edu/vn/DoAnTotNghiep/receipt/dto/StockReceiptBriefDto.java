package fithou.edu.vn.DoAnTotNghiep.receipt.dto;

import fithou.edu.vn.DoAnTotNghiep.common.dto.AuditableDto;
import fithou.edu.vn.DoAnTotNghiep.supplier.dto.SupplierDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockReceiptBriefDto extends AuditableDto {
    private String stockReceiptId;
    private int total = 0;
    private String note;
    private String supplierId;
    private SupplierDto supplier;
}
