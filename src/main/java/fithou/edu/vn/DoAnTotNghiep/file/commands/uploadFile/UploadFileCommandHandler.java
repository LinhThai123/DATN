package fithou.edu.vn.DoAnTotNghiep.file.commands.uploadFile;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.file.IFileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UploadFileCommandHandler implements IRequestHandler<UploadFileCommand, String> {

    @Autowired
    private IFileService fileService ;
    @Override
    public HandleResponse<String> handle(UploadFileCommand uploadFileCommand) throws Exception {
        try{
            return HandleResponse.ok(fileService.uploadFile(uploadFileCommand.getFile()));
        } catch (Exception e) {
            return HandleResponse.error(e.getMessage());
        }
    }
}
