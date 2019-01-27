package com.bsilva.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cassandra")
public class CassandraConfig {
    private String ip;
    private int port;
    private String keyspace;
    private String dc;

    @Bean
    public Cluster cassanddraCluster() {
        return Cluster.builder().withPort(port).addContactPoint(ip).build();
    }

    @Bean
    public Session session() {
        return cassanddraCluster().connect(keyspace);
    }

    @Bean
    public MappingManager mappingManager(){
        return new MappingManager(session());
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getKeyspace() {
        return keyspace;
    }

    public void setKeyspace(String keyspace) {
        this.keyspace = keyspace;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
