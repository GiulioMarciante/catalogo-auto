package com.autoxy.catalogo_auto.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configurazione per il bean {@link ModelMapper}.
 * Fornisce un'istanza di {@link ModelMapper} per il mapping tra oggetti.
 */
@Configuration
public class ModelMapperConfig {
    /**
     * Crea un'istanza di {@link ModelMapper}.
     *
     * @return Nuova istanza di {@link ModelMapper}
     */
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
