package com.traffic.dna.service.impl;

import com.traffic.dna.dto.AddInfoResult;
import com.traffic.dna.dto.InfoDto;
import com.traffic.dna.entity.Info;
import com.traffic.dna.mapper.InfoMapper;
import com.traffic.dna.service.AsyncAddInfoService;
import com.traffic.dna.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InfoServiceImpl implements InfoService, AsyncAddInfoService {
    private final List<Info> INFOS = new ArrayList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Override
    public void addDataBulk(List<InfoDto> list) {
        executorService.execute(() -> {
            list.forEach(this::addSingleData);
        });
    }


    @Override
    public List<AddInfoResult> addDataBulkWithCompletableFuture(List<InfoDto> list) {
        List<AddInfoResult> resultList = new ArrayList<>();
        List<CompletableFuture<AddInfoResult>> completableFutures = new ArrayList<>();
        list.forEach(el -> {
            CompletableFuture<AddInfoResult> completableFuture = null;
            try {
                completableFuture = addAsyncInfo(el);

            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
            completableFutures.add(completableFuture);
        });

        completableFutures.forEach(el -> {
            try {
                resultList.add(el.get());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });
        return resultList;
    }

    @Override
    public void addSingleData(InfoDto infoDto) {
        log.info("Adding infoDto :  {}", infoDto);
        INFOS.add(
                Info.builder()
                        .userId(infoDto.getUserId())
                        .levelId(infoDto.getLevelId())
                        .result(infoDto.getResult())
                        .build()
        );
    }

    @Override
    public List<InfoDto> getTopInfoByUserId(Long userId) {
        log.info("Getting info by user id :  {}", userId);
        return INFOS.stream()
                .filter(o -> o.getUserId().equals(userId))
                .sorted(Comparator.comparing(Info::getResult).reversed())
                .limit(20)
                .map(InfoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InfoDto> getTopInfoByLevelId(Long levelId) {
        log.info("Getting info by level id :  {}", levelId);
        return INFOS.stream()
                .filter(i -> i.getLevelId().equals(levelId))
                .sorted(Comparator.comparing(Info::getResult).reversed())
                .limit(20)
                .map(InfoMapper::toDto)
                .collect(Collectors.toList());
    }


    @Async
    @Override
    public CompletableFuture<AddInfoResult> addAsyncInfo(InfoDto infoDto) {
        boolean result = false;
        try {
            this.addSingleData(infoDto);
            result = true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return CompletableFuture.completedFuture(AddInfoResult.builder()
                .infoDto(infoDto)
                .result(result)
                .build());
    }
}
