package com.htek.model;

import com.aerospike.mapper.annotations.AerospikeBin;
import com.aerospike.mapper.annotations.AerospikeKey;
import com.aerospike.mapper.annotations.AerospikeRecord;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@AerospikeRecord(namespace = "test",set = "refresh_token")
@Introspected
public class RefreshToken {
    @AerospikeKey
    private String PK;
    @AerospikeBin
    private String refreshToken;
    @AerospikeBin
    private String custEmail;

}
