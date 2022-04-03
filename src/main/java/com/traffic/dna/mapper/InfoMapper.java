package com.traffic.dna.mapper;

import com.traffic.dna.dto.InfoDto;
import com.traffic.dna.entity.Info;

public interface InfoMapper {

    static InfoDto toDto(Info info) {
        return InfoDto.builder()
                .userId(info.getUserId())
                .levelId(info.getLevelId())
                .result(info.getResult())
                .build();
    }
}
