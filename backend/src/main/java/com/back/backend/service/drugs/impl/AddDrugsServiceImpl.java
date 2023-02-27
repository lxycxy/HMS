package com.back.backend.service.drugs.impl;

import com.back.backend.Mapper.DrugsMapper;
import com.back.backend.pojo.Drugs;
import com.back.backend.service.drugs.AddDrugsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddDrugsServiceImpl implements AddDrugsService {

    @Autowired
    DrugsMapper drugsMapper;

    @Override
    public Map<String, String> addDrugs(Map<String, String> map) {
        String name,supplyUnit, productUnit;
        Double price, purchasePrice;
        Long num;

        Map<String, String> resp = new HashMap<>();

        try {
            name = map.get("name");
            supplyUnit = map.get("supplyUnit");
            productUnit = map.get("productUnit");
            num = Long.parseLong(map.get("num"));
            price = Double.parseDouble(map.get("price"));
            purchasePrice = Double.parseDouble(map.get("purchasePrice"));

            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date introduceDate = sdf.parse(map.get("introduceDate"));
            Date productDate = sdf.parse(map.get("productDate"));
            Date qualityPeriod = sdf.parse(map.get("qualityPeriod"));

            drugsMapper.addDrugs(new Drugs(null,name,price,purchasePrice,num,introduceDate,productDate,qualityPeriod,supplyUnit,productUnit));
        }catch (Exception e){
            resp.put("info", "error");
            return resp;
        }

        resp.put("info", "success");
        return resp;
    }
}
