package com.tamayo.registration.hosptial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tamayo.hospital.model.hosp.Hospital;


@Repository
public interface HospitalRepository extends MongoRepository<Hospital, String> {
    //判断是否存在数据
    Hospital getHospitalByHoscode(String hoscode);
}
