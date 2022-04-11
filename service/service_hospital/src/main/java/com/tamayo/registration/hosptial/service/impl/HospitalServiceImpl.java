package com.tamayo.registration.hosptial.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tamayo.registration.hosptial.repository.HospitalRepository;
import com.tamayo.registration.hosptial.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tamayo.hospital.model.hosp.Hospital;

import java.util.Date;
import java.util.Map;

@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        //map转成对象
        String mapString = JSONObject.toJSONString(paramMap);
        Hospital hospital = JSONObject.parseObject(mapString, Hospital.class);


        //判断数据是否存在
        String hoscode = hospital.getHoscode();
        Hospital hospitalExist = hospitalRepository.getHospitalByHoscode(hoscode);

        //如果存在，进行修改
        if (hospitalExist != null) {
            hospital.setStatus(hospitalExist.getStatus());
            hospital.setCreateTime(hospitalExist.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        } else {//如果不存在，进行添加
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }

    }
}
