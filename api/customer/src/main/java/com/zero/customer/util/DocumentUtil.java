package com.zero.customer.util;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.doc.DocClient;
import com.baidubce.services.doc.model.GetDocumentDownloadResponse;
import com.zero.common.util.RedisHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * @author yezhaoxing
 * @date 2017/11/10
 */
@Component
public class DocumentUtil {

    private static final String ACCESS_KEY_ID = "7914f998dbc54f548300d3db442a1f5a";
    private static final String SECRET_ACCESS_KEY = "68837af26d2144e38109059bbdf8006c";
    @Resource
    private RedisHelper<String, String> redisHelper;

    private DocClient getDocClient() {
        // 初始化一个DocClient
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        return new DocClient(config);
    }

    public void createDocument(MultipartFile multipartFile) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename().replaceAll("\\s+", "");
        int index = originalFileName.indexOf(".");
        String name = originalFileName.substring(0, index);
        String suffix = originalFileName.substring(index + 1);
        File file = this.convertToFile(multipartFile, name, suffix);
        getDocClient().createDocument(file, name, suffix, "callback", null, null);
    }

    public String getDownloadUrl(String documentId) {
        GetDocumentDownloadResponse documentDownload = getDocClient().getDocumentDownload(documentId);
        return documentDownload.getDownloadUrl();
    }

    // 将MultipartFile转换成File类型
    private File convertToFile(MultipartFile file, String name, String suffix) throws IOException {
        File target = File.createTempFile(name, suffix);
        file.transferTo(target);
        return target;
    }
}
