package com.traffic.dna.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Info {

    private Long userId;
    private Long levelId;
    private Integer result;
}
