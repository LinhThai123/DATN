package fithou.edu.vn.DoAnTotNghiep.blog.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BlogDto implements Serializable {

    private String id;

    private String title;

    private String description;

    private String content;

    private Integer status;

    private String imageUrl;
}