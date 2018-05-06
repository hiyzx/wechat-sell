package com.zero.customer.web.controller;

import com.zero.customer.util.FileUtil;
import com.zero.customer.util.HttpClient;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
    private HttpClient aliyunHttpClient;

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
