package com.tamayo.registration.cmn.controller;

import com.tamayo.registration.cmn.service.DictService;
import com.tamayo.registration.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tamayo.hospital.model.cmn.Dict;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "数据字典管理")
@RestController
@RequestMapping("/admin/cmn/dict")
@CrossOrigin
public class DictController {

    @Autowired
    private DictService dictService;


    // 导入数据字典
    @PostMapping("importData")
    public Result importData(MultipartFile file){
        dictService.importDictData(file);
        return Result.ok();
    }

    // 导出数据字典
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response){
        dictService.exportDictData(response);
    }

    // 根据id查询子数据
    @ApiOperation(value = "根据id查询子数据")
    @GetMapping("findChildData/{id}")
    private Result findChildData(@PathVariable Long id){
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }
}
