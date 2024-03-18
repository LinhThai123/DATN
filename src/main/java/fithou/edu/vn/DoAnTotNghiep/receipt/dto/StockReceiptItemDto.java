package fithou.edu.vn.DoAnTotNghiep.receipt.dto;

import fithou.edu.vn.DoAnTotNghiep.product.dto.ProductOptionDetailDto;

public class StockReceiptItemDto {
    private String stockReceiptId;
    private String productOptionId;
    private int quantity;
    private int price = 0;
    private ProductOptionDetailDto productOption;
}
