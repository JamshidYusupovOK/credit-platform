package org.integer.creditservice.config;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String CREDIT_EXCHANGE = "credit.exchange";
    public static final String SCORING_QUEUE = "scoring.queue";
    public static final String STATUS_QUEUE = "status.queue";
    public static final String CREDIT_RESULT_QUEUE = "credit.result.queue";


    @Bean
    public FanoutExchange creditExchange() {
        return new FanoutExchange(CREDIT_EXCHANGE);
    }

    @Bean
    public Queue scoringQueue() {
        return new Queue(SCORING_QUEUE, true);
    }

    @Bean
    public Queue statusQueue() {
        return new Queue(STATUS_QUEUE, true);
    }

    @Bean
    public Queue creditResultQueue() {
        return new Queue(CREDIT_RESULT_QUEUE, true);
    }

    @Bean
    public Binding scoringBinding() {
        return BindingBuilder.bind(scoringQueue()).to(creditExchange());
    }

    @Bean
    public Binding statusBinding() {
        return BindingBuilder.bind(statusQueue()).to(creditExchange());
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
