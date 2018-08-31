package com.zero.admin.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.core.type.TypeReference;
import com.zero.admin.vo.http.response.*;
import com.zero.common.util.JsonHelper;
import com.zero.common.util.StringHelper;
import com.zero.product.po.ProductCategory;
import com.zero.product.po.ProductInfo;
import com.zero.product.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @author yezhaoxing
 * @date 2017/12/21
 */
@Component
@Slf4j
public class JuHeUtil {

    private static final String KEY = "847f56f18414a491a571540757c514b5";

    @Resource
    private HttpClient juHeHttpClient;
    @Reference
    private ProductCategoryService productCategoryService;

    public List<ProductCategory> getCategories() {
        TypeReference<JuHeReturnVo<List<JuHeParentCategoryResponseVo>>> TYPE_REFERENCE = new TypeReference<JuHeReturnVo<List<JuHeParentCategoryResponseVo>>>() {
        };
        Map<String, String> params = new HashMap<>(5);
        params.put("key", KEY);
        String response = juHeHttpClient.get("/cook/category", params);
        JuHeReturnVo<List<JuHeParentCategoryResponseVo>> returnVo = JsonHelper.readValue(response, TYPE_REFERENCE);
        List<JuHeParentCategoryResponseVo> result = returnVo.getResult();
        int index = 5;
        List<ProductCategory> productCategories = new ArrayList<>();
        Date now = new Date();

        for (JuHeParentCategoryResponseVo parentCategory : result) {
            for (JuHeChildCategoryResponseVo childCategory : parentCategory.getList()) {
                ProductCategory tmp = new ProductCategory();
                tmp.setName(childCategory.getName());
                tmp.setShowIndex(index);
                tmp.setCreateTime(now);
                tmp.setUpdateTime(now);
                tmp.setIsDelete(true);
                productCategories.add(tmp);
                log.info("add name {} to list", childCategory.getName());
                index++;
            }
        }
        return productCategories;
    }

    public List<ProductInfo> getProducts(Integer categoryId) {
        TypeReference<JuHeReturnVo<JuHeProductDateResponseVo>> TYPE_REFERENCE = new TypeReference<JuHeReturnVo<JuHeProductDateResponseVo>>() {
        };
        ProductCategory productCategory = productCategoryService.findById(categoryId);
        Map<String, String> params = new HashMap<>(5);
        params.put("key", KEY);
        params.put("menu", productCategory.getName());
        String response = juHeHttpClient.get("/cook/query.php", params);
        JuHeReturnVo<JuHeProductDateResponseVo> returnVo = JsonHelper.readValue(response, TYPE_REFERENCE);
        List<JuHeProductInfoResponseVo> result = returnVo.getResult().getData();
        List<ProductInfo> rtn = new ArrayList<>(result.size());
        for (JuHeProductInfoResponseVo productInfoResponseVo : result) {
            ProductInfo tmp = new ProductInfo();
            tmp.setUid(StringHelper.generateProductKey());
            tmp.setName(productInfoResponseVo.getTitle());
            tmp.setPrice(10D);
            tmp.setSellCount(0);
            tmp.setInfo(productInfoResponseVo.getTags());
            tmp.setDescription(productInfoResponseVo.getImtro());
            List<String> albums = productInfoResponseVo.getAlbums();
            if (!albums.isEmpty()) {
                tmp.setIcon(albums.get(0));
                tmp.setImage(albums.get(0));
            }
            tmp.setCategoryId(categoryId);
            tmp.setTotalScore(0);
            tmp.setAverageScore(0D);
            tmp.setCommentCount(0);
            tmp.setCreateTime(new Date());
            tmp.setUpdateTime(new Date());
            tmp.setIsDelete(false);
            rtn.add(tmp);
        }
        return rtn;
    }

    public void generateQrCode(String text) throws IOException {
        Map<String, String> params = new HashMap<>(5);
        params.put("key", "db3904d0afbf6224b90940248dd083f1");
        params.put("text", text);
        String response = juHeHttpClient.get("/qrcode/api", params);
        TypeReference<JuHeReturnVo<QrCodeVo>> TYPE_REFERENCE = new TypeReference<JuHeReturnVo<QrCodeVo>>() {
        };
        JuHeReturnVo<QrCodeVo> returnVo = JsonHelper.readValue(response, TYPE_REFERENCE);
        ImageBase64Utils.base64ToImageFile(returnVo.getResult().getBase64_image(), "E:\\hello.png");
    }
}
