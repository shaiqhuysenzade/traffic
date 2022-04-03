# README #

# Running

To run this app use :
```run.sh```

# API :

    1.@PutMapping  :
      /single             - adding only one InfoDto
      /bulk               - adding List<InfoDto>(used ExecutorService)
      /bulk/completable   - adding List<InfoDto>(used CompletableFuture)
    
    2.@GetMapping :
      /by-user-id         - get by userId 20 List<InfoDto> by top results
      /by-level-id        - get by levelId 20 List<InfoDto> by top result
   

