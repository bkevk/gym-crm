package com.bk.gym.feign;

import com.bk.gym.dto.WorkloadUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "trainer-workload-service",
        url = "${trainer.workload.service.url:}"
)
public interface TrainerWorkloadFeignClient {

    @PostMapping("/api/workload")
    ResponseEntity<Void> updateWorkload(
            @RequestBody WorkloadUpdateRequest request,
            @RequestHeader("Authorization") String bearerToken,
            @RequestHeader("Transaction-Id") String transactionId
    );
}
