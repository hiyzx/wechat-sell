package com.zero.message.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yezhaoxing
 * @date 2019/6/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("发送信息请求对象")
public class SendMsgRequest {

	@ApiModelProperty("用户uid")
	private Integer uid;

	@ApiModelProperty("短信请求模板")
	private String msgTemplateKey;

	@ApiModelProperty("标题")
	private String title;

	@ApiModelProperty("内容")
	private String content;

	@ApiModelProperty("备注")
	private String remark;
}
