package br.com.sistema.clientes.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


@Configuration
public class InternacionalizacaoConfig {

	// Criar classe de configuração de internacionalização com MessageSource

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages"); //Define o arquivo de confifuracao
		messageSource.setDefaultEncoding("ISO-8859-1"); //Define encoding que aceita caracteres especiais
		messageSource.setDefaultLocale( Locale.getDefault() ); //Define o local baseado no S.O
		return messageSource;
	}

	// Para integrar o arquivo de internacionalização com o Bean validator nas entidades
	@Bean
	public LocalValidatorFactoryBean validatorFactoryBean() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

}
