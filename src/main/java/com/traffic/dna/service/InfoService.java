package com.traffic.dna.service;

import com.traffic.dna.dto.AddInfoResult;
import com.traffic.dna.dto.InfoDto;

import java.util.List;

public interface InfoService {
    void addDataBulk(List<InfoDto> list) throws InterruptedException;

    List<AddInfoResult> addDataBulkWithCompletableFuture(List<InfoDto> list);

    void addSingleData(InfoDto infoDto);

    List<InfoDto> getTopInfoByUserId(Long userId);

    List<InfoDto> getTopInfoByLevelId(Long levelId);
}
