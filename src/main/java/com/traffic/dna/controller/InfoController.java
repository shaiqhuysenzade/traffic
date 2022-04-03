package com.traffic.dna.controller;

import com.traffic.dna.dto.AddInfoResult;
import com.traffic.dna.dto.InfoDto;
import com.traffic.dna.service.InfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/info")
@RestController
@AllArgsConstructor
public class InfoController {
    private final InfoService infoService;

    @PutMapping("/single")
    public void addSingleInfo(@RequestBody InfoDto infoDto) {
        infoService.addSingleData(infoDto);
    }

    @PutMapping("/bulk")
    public void addInfoBulk(@RequestBody List<InfoDto> infoDto) throws InterruptedException {
        infoService.addDataBulk(infoDto);
    }

    @PutMapping("/bulk/completable")
    public List<AddInfoResult> addInfoBulkWithCompletable(@RequestBody List<InfoDto> infoDto) {
        return infoService.addDataBulkWithCompletableFuture(infoDto);
    }

    @GetMapping("by-user-id")
    public List<InfoDto> getTopInfoByUserId(@RequestParam Long userId) {
        return infoService.getTopInfoByUserId(userId);
    }

    @GetMapping("by-level-id")
    public List<InfoDto> getTopInfoByLevelId(@RequestParam Long levelId) {
        return infoService.getTopInfoByLevelId(levelId);
    }
}
