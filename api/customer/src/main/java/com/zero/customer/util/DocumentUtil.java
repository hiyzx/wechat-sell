package com.zero.customer.util;

import com.baidubce.services.doc.DocClient;
import com.baidubce.services.doc.model.GetDocumentDownloadResponse;
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

    @Resource
    private DocClient docClient;

    public void createDocument(MultipartFile multipartFile) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename().replaceAll("\\s+", "");
        int index = originalFileName.indexOf(".");
        String name = originalFileName.substring(0, index);
        String suffix = originalFileName.substring(index + 1);
        File file = this.convertToFile(multipartFile, name, suffix);
        docClient.createDocument(file, name, suffix, "callback", null, null);
    }

    public String getDownloadUrl(String documentId) {
        GetDocumentDownloadResponse documentDownload = docClient.getDocumentDownload(documentId);
        return documentDownload.getDownloadUrl();
    }

    // 将MultipartFile转换成File类型
    private File convertToFile(MultipartFile file, String name, String suffix) throws IOException {
        File target = File.createTempFile(name, suffix);
        file.transferTo(target);
        return target;
    }
}
