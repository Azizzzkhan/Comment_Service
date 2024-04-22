package com.example.commentservice.configuration;
import com.example.commentservice.parser.TextXPath;
import com.jcabi.xml.XML;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaSenderConfiguration {
    private final XML setting;
    @Value("${spring.kafka.bootstrap-servers}")
    private String server;

    @Bean
    public SenderOptions<String, Object> senderOptions() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, new TextXPath(this.setting, "//keySerializer").toString());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, new TextXPath(this.setting, "//valueSerializer").toString());

        return SenderOptions.create(props);
    }

    @Bean
    public KafkaSender<String, Object> sender() {
        return KafkaSender.create(senderOptions());
    }
}
