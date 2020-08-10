package it.lucastudio.project.madProject.persistence;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PersistenceConfig {
	
	@Value(value = "${spring.datasource.url}")
	String url;
	
	@Value(value = "${spring.datasource.username}")
	String username;

	@Value(value = "${spring.datasource.password}")
	String password;
	
	
	@Value(value = "${spring.datasource.driverClassName}")
	String driverClassName;
	
	

	
	
	
	
	@Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }

}
