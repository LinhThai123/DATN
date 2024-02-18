package fithou.edu.vn.DoAnTotNghiep.product.commands.deleteColor;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class DeleteColorCommandHandler implements IRequestHandler<DeleteColorCommand, String> {
    @Autowired
    private ColorRepository colorRepository;
    @Override
    @Transactional
    public HandleResponse<String> handle(DeleteColorCommand deleteColorCommand) throws Exception {
        var color = colorRepository.findById(deleteColorCommand.getId());
        if (color.isEmpty()) {
            return HandleResponse.error("Không tìm thấy màu sắc ", HttpStatus.NOT_FOUND);
        }
        colorRepository.delete(color.get());
        return HandleResponse.ok("Xóa màu sắc thành công");
    }
}
