package com.traffic.dna;

import com.traffic.dna.dto.InfoDto;
import com.traffic.dna.service.InfoService;
import com.traffic.dna.service.impl.InfoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.traffic.dna.util.StringUtils.beautify;

@SpringBootTest
@Execution(ExecutionMode.SAME_THREAD)
class TrafficApplicationTests {

    private final InfoService infoService = new InfoServiceImpl();

    @Test
    public void testAddSingleInfo() {
        infoService.addSingleData(mockSingleInfoData());
        Assertions.assertEquals(beautify(givenSingleMockData()), beautify(infoService.getTopInfoByUserId(1L)));
    }

    @Test
    public void testAddBulkWithComparableFutureInfoByUserId() {
        infoService.addDataBulkWithCompletableFuture(mockBulkInfoData());
        Assertions.assertEquals(beautify(givenBulkInfoDataByUserId()), beautify(infoService.getTopInfoByUserId(1L)));
    }

    @Test
    public void testAddBulkWithComparableFutureInfoLevelId() {
        infoService.addDataBulkWithCompletableFuture(mockBulkInfoData());
        Assertions.assertEquals(beautify(givenBulkInfoDataByLevelId()), beautify(infoService.getTopInfoByLevelId(1L)));
    }


    private List<InfoDto> givenSingleMockData() {
        List<InfoDto> list = new ArrayList<>();
        list.add(mockSingleInfoData());
        return list;
    }

    private InfoDto mockSingleInfoData() {
        return InfoDto.builder()
                .userId(1L)
                .levelId(1L)
                .result(2)
                .build();
    }

    private List<InfoDto> givenBulkInfoDataByUserId() {
        return Arrays.asList(
                InfoDto.builder()
                        .userId(1L)
                        .levelId(2L)
                        .result(3)
                        .build(),
                InfoDto.builder()
                        .userId(1L)
                        .levelId(1L)
                        .result(2)
                        .build()
        );
    }

    private List<InfoDto> givenBulkInfoDataByLevelId() {
        return Arrays.asList(InfoDto.builder()
                        .userId(1L)
                        .levelId(1L)
                        .result(2)
                        .build(),
                InfoDto.builder()
                        .userId(2L)
                        .levelId(1L)
                        .result(1)
                        .build()
        );
    }

    private List<InfoDto> mockBulkInfoData() {
        return Arrays.asList(InfoDto.builder()
                        .userId(1L)
                        .levelId(1L)
                        .result(2)
                        .build(),
                InfoDto.builder()
                        .userId(1L)
                        .levelId(2L)
                        .result(3)
                        .build(),
                InfoDto.builder()
                        .userId(2L)
                        .levelId(1L)
                        .result(1)
                        .build()
        );
    }
}
