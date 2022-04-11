package com.tamayo.registration.hosptial.controller.api;

import com.tamayo.registration.common.exception.HospitalException;
import com.tamayo.registration.common.helper.HttpRequestHelper;
import com.tamayo.registration.common.result.Result;
import com.tamayo.registration.common.result.ResultCodeEnum;
import com.tamayo.registration.common.utils.MD5;
import com.tamayo.registration.hosptial.service.HospitalService;
import com.tamayo.registration.hosptial.service.HospitalSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/hosp")
public class ApiController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalSetService hospitalSetService;

    //上传医院接口
    @PostMapping("saveHospital")
    public Result saveHosp(HttpServletRequest request) {
        //获取医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        String hospSign = (String) paramMap.get("sign");
        String hoscode = (String) paramMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);
        String MD5signKey = MD5.encrypt(signKey);
        if (!MD5signKey.equals(hospSign)) {
            throw new HospitalException(ResultCodeEnum.SIGN_ERROR);
        }

        String logoData = (String) paramMap.get("logoData");
        logoData.replaceAll(" ","+");
        paramMap.put("logoData",logoData);


        //调用service
        hospitalService.save(paramMap);
        return Result.ok();
    }
}
