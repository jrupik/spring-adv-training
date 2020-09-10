package pl.training.shop.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Configuration
public class ProducerConfiguration {

    @Bean
    public ProducerFactory<String, String> producerFactory(@Value("${training.kafka.server}") String server) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, server);
        properties.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        var kafkaTemplate = new KafkaTemplate<>(producerFactory);
        /*kafkaTemplate.setProducerListener(new ProducerListener<>() {
                    @Override
                    public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {

                    }

                    @Override
                    public void onError(ProducerRecord<String, String> producerRecord, Exception exception) {

                    }
                });*/
        return kafkaTemplate;
    }

    @Bean
    public NewTopic mainTopic(@Value("training.kafka.mainTopic") String mainTopic) {
        return TopicBuilder.name(mainTopic).build();
    }

}
