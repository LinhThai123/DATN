package fithou.edu.vn.DoAnTotNghiep.file.endpoint;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import fithou.edu.vn.DoAnTotNghiep.file.commands.uploadFile.UploadFileCommand;
import fithou.edu.vn.DoAnTotNghiep.file.commands.uploadMultipartFile.UploadMultipartFileCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@RestController
@RequestMapping("api/file")
@AllArgsConstructor
public class FileController {
    private final ISender sender ;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile (@RequestBody MultipartFile file) {
        var command = new UploadFileCommand(file);
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }

    @PostMapping("/uploads")
    public ResponseEntity<Collection<String>> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        var command = new UploadMultipartFileCommand(files);
        var result = sender.send(command);
        return ResponseEntity.ok(result.orThrow());
    }
}
