package fithou.edu.vn.DoAnTotNghiep.category.dto;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import fithou.edu.vn.DoAnTotNghiep.common.dto.AuditableDto;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto extends AuditableDto {

    private String categoryId;

    private String name;

}
