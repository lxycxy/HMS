package com.back.backend.controller;

import com.back.backend.pojo.Drugs;
import com.back.backend.service.drugs.AddDrugsService;
import com.back.backend.service.drugs.DeleteDrugsService;
import com.back.backend.service.drugs.GetDrugsService;
import com.back.backend.service.drugs.UpdateDrugsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DrugsController {

    @Autowired
    GetDrugsService getDrugsService;

    @Autowired
    DeleteDrugsService deleteDrugsService;

    @Autowired
    UpdateDrugsService updateDrugsService;

    @Autowired
    AddDrugsService addDrugsService;

    @PostMapping("/api/getDrugs/")
    public List<Drugs> getDrugs(){
        return getDrugsService.getDrugs();
    }

    @PostMapping("/api/deleteDrugs/")
    public Map<String,String> deleteDrugs(@RequestParam Map<String, String> map){
        Map<String, String> resp = new HashMap<>();
        Integer id;
        try {
            id = Integer.parseInt(map.get("id"));
        } catch (Exception e) {
            resp.put("info", "error");
            return resp;
        }
        return deleteDrugsService.deleteDrugs(id);
    }

    @PostMapping("/api/updateDrugs/")
    public Map<String,String> updateDrugs(@RequestParam Map<String,String> map){
        return updateDrugsService.updateDrugs(map);
    }

    @PostMapping("/api/addDrugs/")
    public Map<String,String> addDrugs(@RequestParam Map<String,String> map){
        return addDrugsService.addDrugs(map);
    }
}
