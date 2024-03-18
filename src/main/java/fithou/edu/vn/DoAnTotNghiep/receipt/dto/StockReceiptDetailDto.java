package fithou.edu.vn.DoAnTotNghiep.receipt.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StockReceiptDetailDto extends StockReceiptBriefDto{

    private List<StockReceiptItemDto> stockReceiptItems;
}
