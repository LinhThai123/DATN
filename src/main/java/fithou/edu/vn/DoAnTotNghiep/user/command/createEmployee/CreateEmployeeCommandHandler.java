package fithou.edu.vn.DoAnTotNghiep.user.command.createEmployee;

import fithou.edu.vn.DoAnTotNghiep.auth.repository.RoleRepository;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.user.entity.User;
import fithou.edu.vn.DoAnTotNghiep.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@AllArgsConstructor
@Service
public class CreateEmployeeCommandHandler implements IRequestHandler<CreateEmployeeCommand, String> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public HandleResponse<String> handle(CreateEmployeeCommand createEmployeeCommand) throws Exception {
        var exitsEmail = userRepository.findByEmail(createEmployeeCommand.getEmail());
        if (exitsEmail.isPresent()) {
            return HandleResponse.error("Email đã tồn tại");
        }
        var exitsPhone = userRepository.findByNumberPhone(createEmployeeCommand.getNumberPhone());
        if (exitsPhone.isPresent()) {
            return HandleResponse.error("Số điện thoại đã tồn tại");
        }
        // Chuyển đổi ngày sinh thành tuổi
        Date birthDay = createEmployeeCommand.getBirthDay();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(birthDay);
        Calendar currentDate = Calendar.getInstance();
        int age = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        if (currentDate.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH)
                || (currentDate.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH)
                && currentDate.get(Calendar.DAY_OF_MONTH) < birthDate.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        // Kiểm tra nếu tuổi nhỏ hơn 18
        if (age < 18) {
            return HandleResponse.error("Tuổi phải lớn hơn hoặc bằng 18");
        }

        User employee = new User() ;
        employee.setName(createEmployeeCommand.getName());
        employee.setEmail(createEmployeeCommand.getEmail());
        employee.setNumberPhone(createEmployeeCommand.getNumberPhone());
        employee.setAddress(createEmployeeCommand.getAddress());
        employee.setBirthDay(birthDay);
        employee.setStatus(createEmployeeCommand.getStatus());
        employee.setAvartar(createEmployeeCommand.getAvartar());
        employee.setAccountName(createEmployeeCommand.getAccountName());
        employee.setGender(createEmployeeCommand.getGender());
        employee.setPassword(passwordEncoder.encode(createEmployeeCommand.getPassword()));
        employee.setRoles(List.of(roleRepository.findByName("ROLE_EMPLOYEE").orElseThrow()));
        employee.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        employee.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        userRepository.save(employee);
        return HandleResponse.ok(employee.getId());
    }
}
