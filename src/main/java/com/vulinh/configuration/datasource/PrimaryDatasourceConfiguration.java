package com.vulinh.configuration.datasource;

import com.vulinh.constant.PropertyConstant;
import com.vulinh.model.primary.PrimaryBaseEntity;
import com.vulinh.repository.primary.PrimaryBaseRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(
    prefix = DataSourceProperty.DATASOURCE_PREFIX + PrimaryDatasourceConfiguration.PERSISTENCE_UNIT,
    name = DataSourceProperty.ENABLED_PROPS,
    havingValue = PropertyConstant.TRUE
)
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackageClasses = PrimaryBaseRepository.class,
    entityManagerFactoryRef = PrimaryDatasourceConfiguration.PERSISTENCE_UNIT + DataSourceProperty.ENTITY_MANAGER,
    transactionManagerRef = PrimaryDatasourceConfiguration.PERSISTENCE_UNIT + DataSourceProperty.TRANSACTION_MANAGER
)
public class PrimaryDatasourceConfiguration extends DataSourceProperty {

    public static final String PERSISTENCE_UNIT = PRIMARY_PERSISTENCE_UNIT;
    public static final Class<?> ENTITY_BASE_CLASS = PrimaryBaseEntity.class;

    @Primary
    @Bean(PERSISTENCE_UNIT + DATASOURCE_PROPERTIES)
    @ConfigurationProperties(DataSourceProperty.DATASOURCE_PREFIX + PERSISTENCE_UNIT)
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(PERSISTENCE_UNIT + DATASOURCE)
    @ConfigurationProperties(DATASOURCE_PREFIX + PERSISTENCE_UNIT + CONFIGURATION_SUFFIX)
    public DataSource primaryDataSource() {
        return initDataSource(primaryDataSourceProperties());
    }

    @Primary
    @Bean(PERSISTENCE_UNIT + ENTITY_MANAGER)
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        return initEntityMangerFactoryBean(
            entityManagerFactoryBuilder, primaryDataSource(), ENTITY_BASE_CLASS, PERSISTENCE_UNIT
        );
    }

    @Primary
    @Bean(PERSISTENCE_UNIT + TRANSACTION_MANAGER)
    public PlatformTransactionManager primaryTransactionManager(
        @Qualifier(PERSISTENCE_UNIT + ENTITY_MANAGER) EntityManagerFactory entityManagerFactory
    ) {
        return initTransactionManager(entityManagerFactory);
    }
}