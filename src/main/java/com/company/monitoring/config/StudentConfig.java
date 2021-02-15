package com.company.monitoring.config;

import com.company.monitoring.model.Student;
import com.company.monitoring.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student john = new Student("John","Doe");
            Student marc = new Student("Marc","Dupont");
            studentRepository.saveAll(List.of(john, marc));
        };
    }

    @Bean
    public HttpTraceRepository httpTraceRepository()
    {
        InMemoryHttpTraceRepository inMemoryHttpTraceRepository = new InMemoryHttpTraceRepository();
        inMemoryHttpTraceRepository.setCapacity(20);
        return inMemoryHttpTraceRepository;
    }
}
