package com.traffic.dna.service;

import com.traffic.dna.dto.AddInfoResult;
import com.traffic.dna.dto.InfoDto;

import java.util.concurrent.CompletableFuture;

public interface AsyncAddInfoService {
    CompletableFuture<AddInfoResult> addAsyncInfo(InfoDto infoDto);
}
