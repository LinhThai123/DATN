package fithou.edu.vn.DoAnTotNghiep.common.cqrs;

public interface IRequestHandler<TRequest, TResponse> {
    HandleResponse<TResponse> handle(TRequest request) throws Exception;
}
