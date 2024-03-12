package fithou.edu.vn.DoAnTotNghiep.receipt.command.createReceipt;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductOptionRepository;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.Receipt;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.ReceiptItem;
import fithou.edu.vn.DoAnTotNghiep.receipt.repository.ReceiptItemRepository;
import fithou.edu.vn.DoAnTotNghiep.receipt.repository.ReceiptRepository;
import fithou.edu.vn.DoAnTotNghiep.supplier.repository.SuppilerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class CreateReceiptCommandHandler implements IRequestHandler<CreateReceiptCommand, String> {

    @Autowired
    private SuppilerRepository suppilerRepository;

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ReceiptItemRepository receiptItemRepository;
    @Override
    @Transactional(rollbackFor = {Exception.class, ResponseStatusException.class})
    public HandleResponse<String> handle(CreateReceiptCommand createReceiptCommand) throws Exception {
        var existSupplier = suppilerRepository.findById(createReceiptCommand.getSupplierId());
        if (existSupplier.isEmpty()) {
            return HandleResponse.error("Không tìm thấy nhà cung cấp");
        }
        var stockReceipt = Receipt.builder()
                .note(createReceiptCommand.getNote())
                .total(0)
                .supplier(existSupplier.get())
                .build();
        receiptRepository.save(stockReceipt);
        int total = 0 ;
        for (CreateReceiptCommand.CreateItemCommand item : createReceiptCommand.getStockReceiptItems()) {
            var productOption = productOptionRepository.findById(item.productOptionId);
            if (productOption.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Không tìm thấy sản phẩm với id " + item.productOptionId);
            }
            if (productOption.get().getProduct().getPrice() < item.price) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Giá nhập không được lớn hơn giá bán: sản phẩm" + productOption.get().getProduct().getName());
            }

            var stockReceiptItem = ReceiptItem.builder()
                    .stockReceiptId(stockReceipt.getId())
                    .quantity(item.quantity)
                    .productOptionId(item.productOptionId)
                    .price(item.price)
                    .build();
            productOption.get().setStock(productOption.get().getStock() + item.quantity);
            productOptionRepository.save(productOption.get());
            receiptItemRepository.save(stockReceiptItem);
            total += item.quantity * item.price;
        }
        stockReceipt.setTotal(total);
        receiptRepository.save(stockReceipt);
        return HandleResponse.ok(stockReceipt.getId());
    }
}
