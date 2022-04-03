package com.traffic.dna.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddInfoResult {
    private boolean result;
    private InfoDto infoDto;
}
