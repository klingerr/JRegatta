package de.klinger.adw.util;

//import org.dozer.Mapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    
    @Bean
    public Mapper getMapper() {
        return new DozerBeanMapper();
    }
    
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
    
}
