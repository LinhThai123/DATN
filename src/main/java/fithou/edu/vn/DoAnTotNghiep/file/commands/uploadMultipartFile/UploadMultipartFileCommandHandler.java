package fithou.edu.vn.DoAnTotNghiep.file.commands.uploadMultipartFile;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.file.IFileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class UploadMultipartFileCommandHandler implements IRequestHandler<UploadMultipartFileCommand, Collection<String>> {
    @Autowired
    private IFileService fileService;
    @Override
    public HandleResponse<Collection<String>> handle(UploadMultipartFileCommand uploadMultipartFileCommand) throws Exception {
        try{
            List<CompletableFuture<String>> futures = Arrays.stream(uploadMultipartFileCommand.getFiles())
                    .map(file -> CompletableFuture.supplyAsync(() -> fileService.uploadFile(file)))
                    .toList();
            CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();
            var result = futures.stream().map(CompletableFuture::join).toList();
            return HandleResponse.ok(result);
        } catch (Exception e) {
            return HandleResponse.ok(Collections.singleton(e.getMessage()));
        }
    }
}
