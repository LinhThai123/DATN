package fithou.edu.vn.DoAnTotNghiep.category.dto;

import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private String id;
    private String name;
    private CategoryDto parent;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

}
