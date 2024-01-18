package fithou.edu.vn.DoAnTotNghiep.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private String id;
    private String name;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
}
