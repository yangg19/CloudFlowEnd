package com.cnpc.server.controller;

import com.cnpc.server.pojo.RespPageBean;
import com.cnpc.server.pojo.RetDic;
import com.cnpc.server.service.IRetDicService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * TODO
 *
 * @Author: yangg19
 * @version: 1.0.0
 * @Date: 2022年01月13日 10:45:00
 */
@RestController
@RequestMapping("/retrieval/dic/")
public class RetDicController {
    @Autowired
    private IRetDicService retDicService;

    @ApiOperation(value = "查询字典")
    @GetMapping("/")
    public RespPageBean getDicInfo(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    RetDic retDic,
                                    LocalDate[] beginDateScope) {
        return retDicService.getDicByPage(currentPage, size, retDic, beginDateScope);
    }}
