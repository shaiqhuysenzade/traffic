package com.traffic.dna.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InfoDto {
    private Long userId;
    private Long levelId;
    private Integer result;
}
