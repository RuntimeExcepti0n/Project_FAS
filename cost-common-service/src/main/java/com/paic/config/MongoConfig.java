package com.paic.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author RuntimeExcepti0n
 * @date 2020/2/23 16:17
 */
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
@Data
@EnableMongoRepositories(basePackages = "com.paic.mongodb")
public class MongoConfig {
    private String host;
    private String database;
    private Integer port;
    private String userName;
    private String passWord;
    private Integer minConnectionsPerHost;
    private Integer maxConnectionsPerhost;
    private Integer maxWaitTime;
    private Boolean sslEnable;
    private Boolean sslInvalidHostNameAllowed;

    @Bean
    public MongoDbFactory mongoDbFactory(){
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.sslEnabled(sslEnable);
        builder.sslInvalidHostNameAllowed(sslInvalidHostNameAllowed);
        builder.maxWaitTime(maxWaitTime);
        builder.minConnectionsPerHost(minConnectionsPerHost);
        builder.connectionsPerHost(maxConnectionsPerhost);

        MongoClientOptions mongoClientOptions = builder.build();

        ServerAddress serverAddress = new ServerAddress(host,port);

        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(userName,database,passWord.toCharArray());

        //创建客户端和Factory
        MongoClient mongoClient = new MongoClient(serverAddress,mongoCredential,mongoClientOptions);
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient,database);

        return mongoDbFactory;

    }


}
