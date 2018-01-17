package com.zero.customer.web.controller;

import com.zero.common.util.NumberUtil;
import com.zero.customer.util.FileUtil;
import com.zero.customer.util.HttpClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yezhaoxing
 * @date 2017/12/14
 */
@RestController
public class TestController {

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

    @GetMapping("/calc")
    public List<TempVo> returnList(@RequestParam Integer source, @RequestParam Integer limit) {
        while (true) {
            List<TempVo> calc = calc(source, limit);
            if (calc.get(2).getA() > 10) {
                return calc;
            }
        }
    }

    private List<TempVo> calc(Integer source, Integer limit) {
        Integer total = source;
        List<TempVo> rtn = new ArrayList<>(10);
        int sum = 0;
        Random random = new Random();
        for (int i = 1; i < 4; i++) {
            int num = 0;
            if (i < 3) {
                num = random.nextInt(1000) + limit;
            }
            if (i != 3) {
                total -= num;
            }
            if (i == 3) {
                num = total - sum;
            }
            if (i != 3) {
                rtn.add(new TempVo(num, NumberUtil.mul(NumberUtil.div(num, source), 100)));
            } else {
                rtn.add(new TempVo(total, NumberUtil.mul(NumberUtil.div(total, source), 100)));
            }
        }
        return rtn;
    }
}
