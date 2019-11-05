package com.zero.message.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.zero.common.vo.BaseReturnVo;
import com.zero.common.vo.ReturnVo;
import com.zero.message.dto.req.SendMsgRequest;
import com.zero.message.dto.resp.FeiGeListResponseVo;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * @author yezhaoxing
 * @date 2019/6/14
 */
@FeignClient(name = "message-server")
@RequestMapping(value = "/feige")
public interface MessageServerClient {

    @GetMapping(value = "/list")
    ReturnVo<FeiGeListResponseVo> list();

    @PostMapping(value = "/sendMsg")
    BaseReturnVo sendMsgAlone(@RequestBody SendMsgRequest sendMsgRequest) throws IOException;
}
