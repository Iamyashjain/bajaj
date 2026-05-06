package com.chitkara.bfhl.controller;

import com.chitkara.bfhl.model.ApiResponse;
import com.chitkara.bfhl.model.BfhlRequest;
import com.chitkara.bfhl.service.AIService;
import com.chitkara.bfhl.service.MathService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BfhlController {

    @Value("${official.email}")
    private String officialEmail;

    private final MathService mathService;
    private final AIService aiService;

    public BfhlController(MathService mathService, AIService aiService) {
        this.mathService = mathService;
        this.aiService = aiService;
    }

    
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Void>> health() {
        return ResponseEntity.ok(new ApiResponse<>(true, officialEmail, null));
    }

    
    @PostMapping("/bfhl")
    public ResponseEntity<ApiResponse<?>> processBfhl(@RequestBody BfhlRequest request) {
        try {
            int keyCount = countNonNullKeys(request);
            
            if (keyCount == 0) {
                return ResponseEntity
                        .badRequest()
                        .body(ApiResponse.error(officialEmail, "No valid key provided. Expected one of: fibonacci, prime, lcm, hcf, AI"));
            }
            
            if (keyCount > 1) {
                return ResponseEntity
                        .badRequest()
                        .body(ApiResponse.error(officialEmail, "Multiple keys provided. Only one key is allowed per request"));
            }

            if (request.getFibonacci() != null) {
                List<Integer> result = mathService.generateFibonacci(request.getFibonacci());
                return ResponseEntity.ok(ApiResponse.success(officialEmail, result));
            }

            if (request.getPrime() != null) {
                List<Integer> result = mathService.filterPrimes(request.getPrime());
                return ResponseEntity.ok(ApiResponse.success(officialEmail, result));
            }

            if (request.getLcm() != null) {
                int result = mathService.calculateLCM(request.getLcm());
                return ResponseEntity.ok(ApiResponse.success(officialEmail, result));
            }

            if (request.getHcf() != null) {
                int result = mathService.calculateHCF(request.getHcf());
                return ResponseEntity.ok(ApiResponse.success(officialEmail, result));
            }

            if (request.getAI() != null) {
                String result = aiService.getAIResponse(request.getAI());
                return ResponseEntity.ok(ApiResponse.success(officialEmail, result));
            }

            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error(officialEmail, "Invalid request"));

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error(officialEmail, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(officialEmail, "Internal server error: " + e.getMessage()));
        }
    }

    
    private int countNonNullKeys(BfhlRequest request) {
        int count = 0;
        if (request.getFibonacci() != null) count++;
        if (request.getPrime() != null) count++;
        if (request.getLcm() != null) count++;
        if (request.getHcf() != null) count++;
        if (request.getAI() != null) count++;
        return count;
    }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(officialEmail, "Unexpected error: " + e.getMessage()));
    }
}
