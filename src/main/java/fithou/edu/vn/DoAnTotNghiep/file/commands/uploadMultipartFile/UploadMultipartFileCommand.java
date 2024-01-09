package fithou.edu.vn.DoAnTotNghiep.file.commands.uploadMultipartFile;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UploadMultipartFileCommand implements IRequest<Collection<String>> {

    private MultipartFile[] files;

}
