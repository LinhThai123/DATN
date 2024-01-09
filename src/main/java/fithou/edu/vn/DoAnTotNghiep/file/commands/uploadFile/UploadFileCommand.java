package fithou.edu.vn.DoAnTotNghiep.file.commands.uploadFile;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UploadFileCommand implements IRequest<String> {

    private MultipartFile file;

}
