package com.htek.model;

import com.aerospike.mapper.annotations.AerospikeBin;
import com.aerospike.mapper.annotations.AerospikeKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountNumberIncrement {

    @AerospikeKey
    private final String PK = "cust_acc_num_auto_increment";
    @AerospikeBin
    private long accNum = 0;
}
