package fithou.edu.vn.DoAnTotNghiep.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AuditableDto {

    private java.time.LocalDateTime createdDate;

    public String getCreatedDateDisplay() {
        return createdDate == null ? "" : createdDate.format(java.time.format.DateTimeFormatter.ofPattern("hh:mm dd/MM/yyyy"));
    }
}
