package com.vulinh.configuration.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

public abstract class DataSourceProperty {

    public static final String DATASOURCE_PREFIX = "datasource.";
    public static final String CONFIGURATION_SUFFIX = ".configuration";

    public static final String ENABLED_PROPS = "enabled";

    public static final String DATASOURCE_PROPERTIES = "DatasourceProperties";
    public static final String DATASOURCE = "Datasource";
    public static final String ENTITY_MANAGER = "EntityManager";
    public static final String TRANSACTION_MANAGER = "TransactionManager";

    public static final String PRIMARY_PERSISTENCE_UNIT = "primary";
    public static final String SECONDARY_PERSISTENCE_UNIT = "secondary";

    protected DataSource initDataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder()
                                   .type(HikariDataSource.class)
                                   .build();
    }

    protected LocalContainerEntityManagerFactoryBean initEntityMangerFactoryBean(
        EntityManagerFactoryBuilder entityManagerFactoryBuilder,
        DataSource dataSource,
        Class<?> baseClassToScan,
        String persistentUnit
    ) {
        return entityManagerFactoryBuilder.dataSource(dataSource)
                                          .packages(baseClassToScan)
                                          .persistenceUnit(persistentUnit)
                                          .build();
    }

    protected PlatformTransactionManager initTransactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}