package fithou.edu.vn.DoAnTotNghiep.common.cqrs;

import fithou.edu.vn.DoAnTotNghiep.auth.entity.Role;

public interface ISender {
    <TResponse> HandleResponse<TResponse> send (IRequest<TResponse> request);
}