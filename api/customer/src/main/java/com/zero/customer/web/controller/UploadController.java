package com.zero.customer.web.controller;

import com.zero.common.util.JsonHelper;
import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
import com.zero.customer.util.DocumentUtil;
import com.zero.customer.util.FileUtil;
import com.zero.customer.util.HttpClient;
import com.zero.customer.vo.dto.MessageBody;
import com.zero.customer.vo.dto.NotificationDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2017/11/13
 */
@RestController
@RequestMapping("/test")
@Slf4j
@Api(description = "上传接口")
public class UploadController {

    @Resource
    private DocumentUtil documentUtil;
    @Resource
    private HttpClient aliyunHttpClient;

    @PostMapping("/upload.json")
    @ApiOperation("上传")
    public BaseReturnVo upload(MultipartFile file) throws IOException {
        documentUtil.createDocument(file);
        CommonsMultipartFile cf = (CommonsMultipartFile) file;
        DiskFileItem fileItem = (DiskFileItem) cf.getFileItem();
        File source = fileItem.getStoreLocation();
        return BaseReturnVo.success();
    }

    @PostMapping("/callback.json")
    @ApiOperation("回调接口")
    public ReturnVo<String> callback(@RequestBody NotificationDto notificationDto) throws IOException {
        log.info(notificationDto.toString());
        MessageBody messageBody = JsonHelper.readValue(notificationDto.getMessageBody(), MessageBody.class);
        String downloadUrl = documentUtil.getDownloadUrl(messageBody.getDocumentId());
        return ReturnVo.success(downloadUrl);
    }

    @GetMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<File> files = new ArrayList<>(2);
        aliyunHttpClient.download("http://yqyd-res-document.oss-cn-hangzhou.aliyuncs.com/201605211813692.pdf",
                "《稻草人》教学课件1.pdf");
        files.add(new File("《稻草人》教学课件1.pdf"));
        aliyunHttpClient.download("http://img2.yiqiyuedu.cn/d02c30294f3d4f28a085c128732f6528.zip", "test.zip");
        files.add(new File("test.zip"));
        FileUtil.downLoadFiles(files, request, response);
    }

    @GetMapping("/unzip")
    public void unzip() throws Exception {
        File file = new File("E:\\test.zip");
        FileUtil.unZipFiles(file, "E:\\test\\");
    }
}
