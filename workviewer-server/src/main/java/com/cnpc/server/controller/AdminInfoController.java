package com.cnpc.server.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.cnpc.server.pojo.*;
import com.cnpc.server.service.*;

import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  个人中心
 * </p>
 *
 * @author yangg19
 * @since 2021-11-29
 */
@RestController
@RequestMapping("/admin")
public class AdminInfoController {

    @Autowired
    private IAdminInfoService adminInfoService;
    @Autowired
    private IPoliticsStatusService politicsStatusService;
    @Autowired
    private IJoblevelService joblevelService;
    @Autowired
    private INationService nationService;
    @Autowired
    private IPositionService positionService;

    @ApiOperation(value = "查询所有员工（分页）")
    @GetMapping("/")
    public RespPageBean getAdminByPage(@RequestParam(defaultValue = "1") Integer currentPage,
                                       @RequestParam(defaultValue = "10") Integer size,
                                       AdminInfo adminInfo) {
        return adminInfoService.getAdminByPage(currentPage, size, adminInfo);
    }

    @ApiOperation(value = "根据当前用户获取用户信息")
    @GetMapping("/adminInfo")
    public AdminInfo getAdminInfoById() {
        return adminInfoService.getAdminInfoById();
    }

    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("/politicsStatus")
    public List<PoliticsStatus> getAllPoliticsStatus() {
        return politicsStatusService.list();
    }

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/joblevels")
    public List<Joblevel> getAllJoblevels() {
        return joblevelService.list();
    }

    @ApiOperation(value = "获取所有民族")
    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.list();
    }

    @ApiOperation(value = "获取所有职位")
    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionService.list();
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/")
    public RespBean addEmp(@RequestBody AdminInfo adminInfo) {
        return adminInfoService.addAdminInfo(adminInfo);
    }

    @ApiOperation(value = "更新员工")
    @PutMapping("/")
    public RespBean updateEmp(@RequestBody AdminInfo adminInfo) {
        if (adminInfoService.updateById(adminInfo)) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除员工")
    @DeleteMapping("/{id}")
    public RespBean deleteEmp(@PathVariable Integer id) {
        if (adminInfoService.removeById(id)) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "导出员工数据")
    @GetMapping(value = "/export",produces = "application/octet-stream")
    public void exportAdminInfo(HttpServletResponse response){
        List<AdminInfo> list = adminInfoService.getAdminInfo(null);
        ExportParams params = new ExportParams("员工表","员工表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params,AdminInfo.class,list);
        ServletOutputStream outputStream = null;
        try {
            //流形式
            response.setHeader("content-type","application/octet-stream");
            //中文乱码
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode("员工表.xls","UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null != outputStream){
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation(value = "导入员工数据")
    @PostMapping("/import")
    public RespBean importAdminInfo(MultipartFile file) {
        ImportParams params = new ImportParams();
        // 去掉标题行
        params.setTitleRows(1);
        List<Nation> nationList = nationService.list();
        List<PoliticsStatus> politicsStatusesList = politicsStatusService.list();
        List<Joblevel> joblevelsList = joblevelService.list();
        List<Position> positionsList = positionService.list();
        try {
            List<AdminInfo> list = ExcelImportUtil.importExcel(file.getInputStream(), AdminInfo.class, params);
            list.forEach(adminInfo -> {
                // indexOf 在字符串中寻找参数字符串第一次出现的位置并返回该位置。
                // 民族 id
                // 获取 Nation 的 name, 通过 name 获取对应的下标，通过下标获取整完的对象，通过对象获取 id,
                adminInfo.setNationId(nationList.get(nationList.indexOf(new Nation(adminInfo.getNation().getName()))).getId());
                // 政治 id
                adminInfo.setPoliticId(politicsStatusesList.get(politicsStatusesList.indexOf(new PoliticsStatus(adminInfo.getPoliticsStatus().getName()))).getId());
                // 职称 id
                adminInfo.setJobLevelId(joblevelsList.get(joblevelsList.indexOf(new Joblevel(adminInfo.getJoblevel().getName()))).getId());
                // 职位 id
                adminInfo.setPosId(positionsList.get(positionsList.indexOf(new Position(adminInfo.getPosition().getName()))).getId());
            });
            if (adminInfoService.saveBatch(list)) {
                return RespBean.success("导入成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("导入失败！");
    }

    @ApiOperation(value = "导入头像")
    @PostMapping("/uploadAvatar")
    public RespBean uploadPic(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {
        //目前这里是写死的本地硬盘路径
        String path = "C:/Users/Administrator/WebstormProjects/cloudflow/src/img/avatar";
        System.out.println("path:" + path);
        System.out.println("path:" + path);
        System.out.println("path:" + path);
        System.out.println("path:" + path);
        System.out.println("path:" + path);
        System.out.println("path:" + path);
        System.out.println("path:" + path);

        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR)) + String.valueOf((currTime.get(Calendar.MONTH) + 1));
        //获取文件名后缀
        String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
        if (suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png")/* || suffix.equals(".gif")*/) {
            fileName = UUID.randomUUID().toString() + suffix;
            File targetFile = new File(path, fileName);
            if (!targetFile.getParentFile().exists()) {    //注意，判断父级路径是否存在
                targetFile.getParentFile().mkdirs();
            }
            long size = 0;
            //保存
            try {
                file.transferTo(targetFile);
                size = file.getSize();
            } catch (Exception e) {
                e.printStackTrace();
                return RespBean.error("上传失败！");
            }
            System.out.println(fileName);
            System.out.println(fileName);
            System.out.println(fileName);
            System.out.println(fileName);
            System.out.println(fileName);
            System.out.println(fileName);

            return adminInfoService.updateAvatar(fileName);
        } else {
            return RespBean.error("图片格式有误，请上传.jpg、.png、.jpeg格式的文件");
        }
    }
}
