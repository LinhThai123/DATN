package fithou.edu.vn.DoAnTotNghiep.file;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    String uploadFile (MultipartFile file);
    void deleteFile (String url);
}
