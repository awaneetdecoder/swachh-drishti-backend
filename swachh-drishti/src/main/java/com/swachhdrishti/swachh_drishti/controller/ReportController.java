package com.swachhdrishti.swachh_drishti.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping(consumes= "multipart/form-data")
    public ResponseEntity<ReportResponse> submitReport(
            @RequestPart("image") MultipartFile image,
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longtitude,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value ="description", required = false) String description,
            @AuthenticationPrincipal User currentUser){

        ReportResponse response= reportService.submitReport(
                image, latitude, longtitude, address, description, currentUser
        );
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/myreports")
    public ResponseEntity<List<ReportRespose>> getMyReports(
            @AuthenticationPricipal User currentUser){
        List<ReportRespose> reports = reportService.getReportsByUser(currentUser);
        return ResponseEntity.ok(reports);
    }

}
