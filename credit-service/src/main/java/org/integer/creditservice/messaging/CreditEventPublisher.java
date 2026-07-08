package org.integer.creditservice.messaging;

import lombok.RequiredArgsConstructor;
import org.integer.creditservice.config.RabbitMQConfig;
import org.integer.creditservice.event.CreditApplicationCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publishCreditApplicationCreated(CreditApplicationCreatedEvent event) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.CREDIT_EXCHANGE,"", event);
    }
}
