package fithou.edu.vn.DoAnTotNghiep.common.cqrs.impl;

import fithou.edu.vn.DoAnTotNghiep.auth.entity.Role;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequest;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.IRequestHandler;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.ISender;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.reflections.Reflections;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Component
public class SenderImpl implements ISender {

    Map<Class, Class> handlers = new HashMap<>();
    private final ConfigurableApplicationContext context;

    public SenderImpl(ConfigurableApplicationContext context) {
        this.context = context;
        Reflections reflections = new Reflections("fithou.edu.vn.DoAnTotNghiep");
        Set<Class<? extends IRequestHandler>> classes = reflections.getSubTypesOf(IRequestHandler.class);
        for (Class<? extends IRequestHandler> clazz : classes) {
            Type[] interfaces = clazz.getGenericInterfaces();
            if(interfaces.length > 0 && interfaces[0] instanceof ParameterizedType) {
                Type requestType = ((ParameterizedType)interfaces[0]).getActualTypeArguments()[0];
                handlers.put((Class<?>)requestType, clazz);
            }
        }
    }

    public IRequestHandler getHandler(Class<?> clazz) {

        Class handler = handlers.get(clazz);
        if (handler == null) {
            return null;
        }
        return (IRequestHandler) context.getBean(handler);
    }
    @Override
    public <TResponse> HandleResponse<TResponse> send(IRequest<TResponse> request) {
        var handler = getHandler(request.getClass());
        if (handler == null) {
            throw new RuntimeException("No handler found for " + request.getClass().getName());
        }
        try {
            return  (HandleResponse<TResponse>) handler.handle(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
