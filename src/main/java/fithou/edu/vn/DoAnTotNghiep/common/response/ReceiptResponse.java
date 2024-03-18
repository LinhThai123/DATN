package fithou.edu.vn.DoAnTotNghiep.common.response;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptResponse extends HandleResponse<ReceiptResponse> {
    private String id ;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private String employeeId ;
    private String employeeName;
    private String supplierId;
    private String suppilerName;
    private Integer totalMoney;
    private List<ReceiptItemResponse> receiptItemResponses;

}
