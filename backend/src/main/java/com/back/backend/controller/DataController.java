package com.back.backend.controller;

import com.back.backend.service.data.GetDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DataController {

    @Autowired
    GetDataService getDataService;

    @GetMapping("api/getdata/")
    public Map<String, String> getData() {
        return getDataService.getData();
    }
}
