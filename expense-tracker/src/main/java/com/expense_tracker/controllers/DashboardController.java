package com.expense_tracker.controllers;


import com.expense_tracker.dtos.DashboardDataDTO;
import com.expense_tracker.dtos.Response;
import com.expense_tracker.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/dashboard")
@RestController
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<Response<DashboardDataDTO>> getDashboardData(){

        DashboardDataDTO data = dashboardService.getCurrentUserDashboardData();

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "dashboard data",
                        data
                )
        );

    }

}
