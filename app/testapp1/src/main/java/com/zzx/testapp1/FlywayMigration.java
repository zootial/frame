package com.zzx.testapp1;

import org.flywaydb.core.Flyway;

public class FlywayMigration {
    public static void main(String[] args) {
        // Create the Flyway instance
        Flyway flyway = new Flyway();

        // Point it to the database
        flyway.setDataSource("jdbc:mysql://localhost:3306/auth?useUnicode=true&characterEncoding=UTF-8", "root", "zzx.128");

//        flyway.setBaselineOnMigrate(true);
        // Start the migration
        flyway.migrate();
    }
}
