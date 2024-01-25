package fithou.edu.vn.DoAnTotNghiep.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedDto {

    private Object items;

    private int totalPages;

    private int currentPage;
}
