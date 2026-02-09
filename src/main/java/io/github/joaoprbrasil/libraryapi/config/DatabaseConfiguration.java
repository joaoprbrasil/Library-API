package io.github.joaoprbrasil.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    //@Bean
    //    public DataSource dataSource(){
    //        DriverManagerDataSource ds = new DriverManagerDataSource();
    //        ds.setUrl(url);
    //        ds.setUsername(username);
    //        ds.setPassword(password);
    //        ds.setDriverClassName(driver);
    //        return ds;
    //    }

    // Hikari datasource serve para aplicações de larga escala, permite criar multiplas conexões
    @Bean
    public DataSource hikariDataSource(){
        HikariConfig config = new HikariConfig();
        System.out.println(username + " " + password + " "  + driver + " " + url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);

        // Pool de conexões - máximo de conexões liberadas
        config.setMaximumPoolSize(10);

        // Mínimo de conexões liberadas - tamanho inicial do pool
        config.setMinimumIdle(1);

        config.setPoolName("librarydb-pool");
        config.setMaxLifetime(10 * 60 * 1000); // 10 minutos
        config.setConnectionTimeout(10 * 60 * 1000); // timeout para conseguir uma conexão
        config.setConnectionTestQuery("select 1"); // query de teste

        return new HikariDataSource(config);
    }
}
