package com.cnpc.server.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.cnpc.server.pojo.*;
import com.cnpc.server.service.IRetDicExplainService;
import com.cnpc.server.service.IRetDicService;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

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

    @Autowired
    private IRetDicExplainService retDicExplainService;

    @ApiOperation(value = "查询字典")
    @GetMapping("/")
    public RespPageBean getDicInfo(@RequestParam(defaultValue = "1") Integer currentPage,
                                   @RequestParam(defaultValue = "10") Integer size,
                                   RetDic retDic,
                                   LocalDate[] beginDateScope) {
        return retDicService.getDicByPage(currentPage, size, retDic, beginDateScope);
    }

    @ApiOperation(value = "导出字典数据")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void exportDic(HttpServletResponse response) {
        List<RetDic> list = retDicService.list();// 导出所有
        // HSSF 03版，兼容性好一点； 还有一个 07 版的
        ExportParams params = new ExportParams("字典信息", "标准字典信息", ExcelType.HSSF);
        // 导出工具类
        Workbook workbook = ExcelExportUtil.exportExcel(params, RetDic.class, list);
        ServletOutputStream out = null;
        try {
            // 流形式导出
            response.setHeader("content-type", "application/octet-stream");
            // 防止中文乱码
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("标准字典信息.xls", "UTF-8"));
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation(value = "导入字典数据")
    @PostMapping("/import")
    public RespBean importDic(MultipartFile file) {
        ImportParams params = new ImportParams();
        // 去掉标题行
        params.setTitleRows(1);
        try {
            List<RetDic> list = ExcelImportUtil.importExcel(file.getInputStream(), RetDic.class, params);

            if (retDicService.saveBatch(list)) {
                return RespBean.success("导入成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("导入失败！");
    }

    @ApiOperation(value = "查询字典补充说明")
    @GetMapping("/explain")
    public RetDicExplain getDicExplainInfo(String dicType, String dicValue) {
        return retDicExplainService.getDicExplainInfo(dicType, dicValue);
    }

    @ApiOperation(value = "新增字典补充说明")
    @PostMapping("/")
    public RespBean addDicExplainInfo(@RequestBody RetDicExplain retDicExplain) {
        return retDicExplainService.addDicExplainInfo(retDicExplain);
    }

    @ApiOperation(value = "更新字典补充说明")
    @PutMapping("/")
    public RespBean updateDicExplainInfo(@RequestBody RetDicExplain retDicExplain) {
        return retDicExplainService.updateDicExplainInfo(retDicExplain);
    }
}

