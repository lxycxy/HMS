package com.back.backend.service.drugs.impl;

import com.back.backend.Mapper.DrugsMapper;
import com.back.backend.pojo.Drugs;
import com.back.backend.service.drugs.GetDrugsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetDrugsServiceImpl implements GetDrugsService {

    @Autowired
    DrugsMapper drugsMapper;

    @Override
    public List<Drugs> getDrugs() {
        return  drugsMapper.getAll();
    }
}
