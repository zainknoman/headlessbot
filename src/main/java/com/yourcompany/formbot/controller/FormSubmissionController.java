package com.yourcompany.formbot.controller;

import com.yourcompany.formbot.dto.FormData;
import com.yourcompany.formbot.dto.SubmissionResult;
import com.yourcompany.formbot.service.FormSubmissionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forms")
public class FormSubmissionController {
    private final FormSubmissionService service;
    public FormSubmissionController(FormSubmissionService service) { this.service = service; }
    @PostMapping("/submit")
    public ResponseEntity<SubmissionResult> submit(@Valid @RequestBody FormData data) {
        SubmissionResult result = service.submit(data);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.internalServerError().body(result);
    }
}
