package fithou.edu.vn.DoAnTotNghiep.common.mapper;

import fithou.edu.vn.DoAnTotNghiep.auth.security.CustomUserDetails;
import fithou.edu.vn.DoAnTotNghiep.common.response.ReceiptItemResponse;
import fithou.edu.vn.DoAnTotNghiep.common.response.ReceiptResponse;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ProductOptionRepository;
import fithou.edu.vn.DoAnTotNghiep.receipt.command.createReceipt.CreateReceiptCommand;
import fithou.edu.vn.DoAnTotNghiep.receipt.command.createReceiptItem.CreateReceiptItemCommand;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.Receipt;
import fithou.edu.vn.DoAnTotNghiep.receipt.entity.ReceiptItem;
import fithou.edu.vn.DoAnTotNghiep.supplier.repository.SuppilerRepository;
import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import fithou.edu.vn.DoAnTotNghiep.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ReceiptMapper {

    @Autowired
    private ModelMapper modelMapper ;

    @Autowired
    private SuppilerRepository suppilerRepository;

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Autowired
    private UserRepository userRepository;

    public ReceiptResponse convertToResponse (Receipt receipt) {
        ReceiptResponse receiptResponse = modelMapper.map(receipt, ReceiptResponse.class);
        receiptResponse.setEmployeeName(receipt.getEmployee().getName());
        receiptResponse.setEmployeeId(receipt.getEmployee().getId());
        receiptResponse.setSuppilerName(receipt.getSupplier().getName());
        receiptResponse.setSupplierId(receipt.getSupplier().getId());
        receiptResponse.setCreatedDate(receipt.getCreatedDate());
        receiptResponse.setModifiedDate(receipt.getModifiedDate());
        receiptResponse.setTotalMoney(receipt.getReceiptItems().stream().mapToInt(receiptItems -> receiptItems.getQuantity() * receiptItems.getPrice()).sum());
        receiptResponse.setReceiptItemResponses(receipt.getReceiptItems().stream().map(receiptItem -> {
            ReceiptItemResponse receiptItemResponse = modelMapper.map(receiptItem, ReceiptItemResponse.class);
            receiptItemResponse.setProductName(receiptItem.getProductOption().getProduct().getName());
            receiptItemResponse.setProductColor(receiptItem.getProductOption().getProduct_color().getName());
            receiptItemResponse.setProductCapacity(receiptItem.getProductOption().getCapacity().getName());
            receiptItemResponse.setProductId(receiptItem.getProductOption().getProduct().getId());
            return receiptItemResponse;
        }).collect(Collectors.toList()));
        return receiptResponse;
    }

    public Receipt convertToEntity (CreateReceiptCommand receiptRequest) {
        Receipt receipt = modelMapper.map(receiptRequest, Receipt.class);
        receipt.setSupplier(suppilerRepository.findById(receiptRequest.getSupplierId()).orElse(null));
        receipt.setNote(receiptRequest.getNote().replaceAll("<[^>]*>", ""));
        receipt.setTotal(receiptRequest.getTotal());
        receipt.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        receipt.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        receipt.setDeleteDate(null);
        receipt.setEmployee(userRepository.findById(receiptRequest.getEmployeeId()).orElse(null));
        return receipt;
    }
    public ReceiptItem convertToEntity (CreateReceiptItemCommand receiptDetailsRequest) {
        ReceiptItem receiptDetails = modelMapper.map(receiptDetailsRequest, ReceiptItem.class);
        receiptDetails.setProductOption(productOptionRepository.findById(receiptDetailsRequest.getProductOptionId()).orElse(null));
        return receiptDetails;
    }
}
