package com.back.backend.service.drugs.impl;

import com.back.backend.Mapper.DrugsMapper;
import com.back.backend.service.drugs.DeleteDrugsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DeleteDrugsServiceImpl implements DeleteDrugsService {
    @Autowired
    DrugsMapper drugsMapper;

    @Override
    public Map<String, String> deleteDrugs(Integer id) {
        Map<String, String> resp = new HashMap<>();
        try {
            drugsMapper.deleteDrugs(id);
        } catch (Exception e) {
            resp.put("info", "error");
            return resp;
        }
        resp.put("info", "success");
        return resp;
    }
}
