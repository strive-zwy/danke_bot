package com.danke.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.stereotype.Component;

/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/9/28 19:57
 * @Description : 数据库初始化 flyway
 */
@Component
public class FlywayConfig implements FlywayMigrationStrategy {

    @Override
    public void migrate(Flyway flyway) {
        FluentConfiguration configure = Flyway.configure();
        configure.baselineOnMigrate(true);
        flyway.migrate();
    }
}
