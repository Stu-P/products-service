package stu.learning.service.products.services;

import stu.learning.service.products.events.IEvent;

public interface IEventPublisher {
    void Publish(IEvent event);
}
