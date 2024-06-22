package com.htek.configuration;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.mapper.tools.AeroMapper;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import javax.annotation.PostConstruct;

@Singleton
public class AeromapperConfiguration {
    @Inject
    AerospikeConfiguration aerospikeConfig;
    AeroMapper mapper = null;

    @PostConstruct
    public void aerospikeClient() {
        ClientPolicy policy = new ClientPolicy();
        policy.readPolicyDefault.socketTimeout = 50;
        policy.readPolicyDefault.totalTimeout = 110;
        policy.readPolicyDefault.sleepBetweenRetries = 10;
        policy.writePolicyDefault.socketTimeout = 200;
        policy.writePolicyDefault.totalTimeout = 450;
        policy.writePolicyDefault.sleepBetweenRetries = 50;

        AerospikeClient client = new AerospikeClient(policy, aerospikeConfig.getHost(), aerospikeConfig.getPort());
        mapper = new AeroMapper.Builder(client).build();
    }

    public AeroMapper getMapper(){
        return this.mapper;
    }
}
