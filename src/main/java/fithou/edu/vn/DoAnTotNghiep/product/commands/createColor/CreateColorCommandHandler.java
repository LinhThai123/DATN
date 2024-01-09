package fithou.edu.vn.DoAnTotNghiep.product.commands.createColor;

import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Brand;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Color;
import fithou.edu.vn.DoAnTotNghiep.product.repository.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class CreateColorCommandHandler implements IRequestHandler<CreateColorCommand, String> {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public HandleResponse<String> handle(CreateColorCommand createColorCommand) throws Exception {
        var existWithName = colorRepository.findByNameIgnoreCase(createColorCommand.getName());
        if (existWithName.isPresent()) {
            return HandleResponse.error("Tên màu đã tồn tại");
        }
        var color = new Color();
        color.setName(createColorCommand.getName());
        color.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        color.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        colorRepository.save(color);
        return HandleResponse.ok("Thêm màu thành công");

    }
}

