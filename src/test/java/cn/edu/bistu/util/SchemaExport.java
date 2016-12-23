package cn.edu.bistu.util;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.schema.TargetType;
import org.junit.Test;

import java.util.EnumSet;

/**
 * Created by hongyu on 2016/12/23.
 */
public class SchemaExport {

    /**
     * Turn "book.hbm.xml" to mysql table
     */
    @Test
    public void testSchemaExport() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
        org.hibernate.tool.hbm2ddl.SchemaExport schemaExport = new org.hibernate.tool.hbm2ddl.SchemaExport();
        schemaExport.create(EnumSet.of(TargetType.DATABASE), metadata);
    }
}
