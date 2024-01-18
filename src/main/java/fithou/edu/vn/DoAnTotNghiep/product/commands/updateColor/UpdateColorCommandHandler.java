package fithou.edu.vn.DoAnTotNghiep.product.commands.updateColor;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateColorCommandHandler implements IRequestHandler<UpdateColorCommand, String> {
    @Autowired
    private ColorRepository colorRepository;
    @Override
    public HandleResponse<String> handle(UpdateColorCommand updateColorCommand) throws Exception {
        var exitsColor = colorRepository.findById(updateColorCommand.getId());
        if (exitsColor.isEmpty()) {
            return HandleResponse.error("Không tìm thấy màu", HttpStatus.NOT_FOUND);
        }
        boolean isUpdateName = exitsColor.get().getName().equals(updateColorCommand.getName());
        if (isUpdateName) {
            var existWithName = colorRepository.findByNameIgnoreCase(updateColorCommand.getName());
            if (existWithName.isPresent()) {
                return HandleResponse.error("Tên màu sắc đã tồn tại");
            }
            exitsColor.get().setName(updateColorCommand.getName());
        }
        colorRepository.save(exitsColor.get());
        return HandleResponse.ok("Sửa màu sắc thành công");
    }
}
