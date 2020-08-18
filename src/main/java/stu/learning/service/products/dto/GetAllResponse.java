package stu.learning.service.products.dto;

import java.util.List;

public class GetAllResponse<T> {
    public GetAllResponse(List<T> items)
    {
        this.items = items;
    }

    private List<T> items;

    public List<T> getItems() {
        return items;
    }
}
