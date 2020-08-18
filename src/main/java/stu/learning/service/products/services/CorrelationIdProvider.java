package stu.learning.service.products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class CorrelationIdProvider implements ICorrelationProvider {

    @Autowired
    HttpServletRequest request;

    @Override
    public UUID getId() {
        String id = request.getHeader("correlation-Id");

        try {
            return id==null ?
                    UUID.randomUUID():
                    UUID.fromString(id);
        }catch (Exception e)
        {
            return UUID.randomUUID();
        }
    }
}
