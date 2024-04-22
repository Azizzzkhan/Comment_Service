package com.example.commentservice.configuration;
import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaBeanConfiguration {
    private static final String PATH_FILE = "/kafka/producer.xml";

    @Bean
    @SneakyThrows
    public XML producerXML() {
        return new XMLDocument(getClass().getResourceAsStream(PATH_FILE).readAllBytes());
    }
}
