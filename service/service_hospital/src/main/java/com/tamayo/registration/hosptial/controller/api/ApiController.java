package com.tamayo.registration.hosptial.controller.api;

import com.tamayo.registration.common.helper.HttpRequestHelper;
import com.tamayo.registration.common.result.Result;
import com.tamayo.registration.hosptial.service.HospitalService;
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

    //上传医院接口
    @PostMapping("saveHospital")
    public Result saveHosp(HttpServletRequest request){
        //获取医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //调用service
        hospitalService.save(paramMap);
        return Result.ok();
    }
}
