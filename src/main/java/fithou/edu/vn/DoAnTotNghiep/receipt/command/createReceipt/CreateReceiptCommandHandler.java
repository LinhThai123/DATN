package fithou.edu.vn.DoAnTotNghiep.receipt.command.createReceipt;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.common.mapper.ReceiptMapper;
import fithou.edu.vn.DoAnTotNghiep.common.response.ReceiptResponse;
import fithou.edu.vn.DoAnTotNghiep.product.entity.ProductOption;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductOptionRepository;
import fithou.edu.vn.DoAnTotNghiep.receipt.command.createReceiptItem.CreateReceiptItemCommand;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.Receipt;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.ReceiptItem;
import fithou.edu.vn.DoAnTotNghiep.receipt.repository.ReceiptItemRepository;
import fithou.edu.vn.DoAnTotNghiep.receipt.repository.ReceiptRepository;
import fithou.edu.vn.DoAnTotNghiep.supplier.entity.Supplier;
import fithou.edu.vn.DoAnTotNghiep.supplier.repository.SuppilerRepository;
import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import fithou.edu.vn.DoAnTotNghiep.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class CreateReceiptCommandHandler implements IRequestHandler<CreateReceiptCommand, ReceiptResponse> {

    @Autowired
    private SuppilerRepository suppilerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ReceiptMapper receiptMapper;

    @Autowired
    private ReceiptItemRepository receiptItemRepository;
    @Override
    @Transactional
    public HandleResponse<ReceiptResponse> handle(CreateReceiptCommand createReceiptCommand) throws Exception {

        Optional<Supplier> exitsSupplier = suppilerRepository.findById(createReceiptCommand.getSupplierId());

        if(exitsSupplier.isEmpty()) {
            throw new RuntimeException("Không tìm thấy nhà cung cấp");
        }

        Optional<User> exitsEmployee = userRepository.findById(createReceiptCommand.getEmployeeId());
        if (exitsEmployee.isEmpty()) {
            throw new RuntimeException("Không tìm thấy nhân viên lập phiếu nhập kho");
        }

        Receipt receipt = receiptMapper.convertToEntity(createReceiptCommand);
        Receipt saveReceipt = receiptRepository.save(receipt) ;

        List<ReceiptItem> receiptDetailsList = createReceiptCommand.getCreateReceiptItemCommands().stream()
                .map(receiptDetailsRequest -> mapToReceiptItem(receiptDetailsRequest, receipt))
                .collect(Collectors.toList());

        Set<String> productOptionIds = receiptDetailsList.stream()
                        .map(productOption -> productOption.getProductOption().getId())
                                .collect(Collectors.toSet());
        if (productOptionIds.size() != receiptDetailsList.size()){
            throw new RuntimeException("Sản phẩm chi tiết này đã tồn tại");
        }
        receiptItemRepository.saveAll(receiptDetailsList);
        saveReceipt.setReceiptItems(receiptDetailsList);
        return receiptMapper.convertToResponse(saveReceipt);
    }

    private ReceiptItem mapToReceiptItem (CreateReceiptItemCommand command , Receipt receipt) {
        ReceiptItem receiptItem = receiptMapper.convertToEntity(command);
        Optional<ProductOption> productOption = Optional.ofNullable(productOptionRepository.findById(command.getProductOptionId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết sản phẩm")));


        int newQuantity = productOption.get().getQuantity() + command.getQuantity();
        productOption.get().setQuantity(newQuantity);
        productOptionRepository.save(productOption.get());

        receiptItem.setProductOption(productOption.get());
        receiptItem.setQuantity(command.getQuantity());
        receiptItem.setPrice(command.getPrice());
        receiptItem.setReceipt(receipt);
        receiptItem.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        receiptItem.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        return receiptItem;
    }
}
