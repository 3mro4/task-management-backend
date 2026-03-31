package com.taskmanagement.backend.controller;

import com.taskmanagement.backend.dto.dashboard.DashboardSummaryDto;
import com.taskmanagement.backend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    public static final String BASE_URL = "/api/v1/dashboard";
    public static final String BASE_URL_WITH_ID = BASE_URL + "/{id}";

    @GetMapping(BASE_URL)
    public DashboardSummaryDto getSummary() {
        return dashboardService.getSummary();
    }
}