package com.wlgdo.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wlgdo.lottery.domain.OrgInfo;

import net.sf.json.JSONObject;

public class WeixinUtils {

	public static String TEMPLATE_MSG_API = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	/**
	 * 微门户请求检查
	 */
	static Logger log = LoggerFactory.getLogger(WeixinUtils.class);

	/**
	 * 接收GET请求并判断微信发送消息真实性
	 * 
	 * @param request
	 * @param response
	 * @param OrgInfo
	 * @return
	 */
	public static void access(ServletRequest request, ServletResponse response, OrgInfo orgInfo) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String token = orgInfo.getToken();
		String result = PaUtil.access(token, signature, timestamp, nonce, echostr);
		if (result.equals(echostr)) {
			responseBody(response, result);
		} else {
			log.error("验证公众号未通过,返回结果result:" + result);
			responseBody(response, "");
		}
	}

	/**
	 * 接收POST消息请求
	 * 
	 * @param request
	 * @param response
	 * @param OrgInfo
	 */
	public static void message(ServletRequest request, ServletResponse response, OrgInfo orgInfo) {
		try {
			// 转换解析XML
			Map<String, Object> xmlMap = PaUtil.tranXml(request.getReader());

			String xmlStr = xmlMap.get("xmlStr").toString();
			log.info("获取到的xms消息：{}", xmlStr);
			String toUserName = xmlMap.get("toUserName").toString();
			// 发送人OpenId
			String fromUserName = xmlMap.get("fromUserName").toString();
			Integer intMsgType = Integer.valueOf(xmlMap.get("intMsgType").toString());
			String msgType = xmlMap.get("msgType").toString();
			log.info("微信推送来的的消息是：{}", xmlMap);
			Date date = new Date();
			String result = "";
			switch (intMsgType) {
			case 0: // 文本
				String content = xmlMap.get("content").toString();
				result = handleText(orgInfo, toUserName, fromUserName, date, content);
				log.info("获取到文本消息：{}", content);
				break;
			case 1:// 图片
				log.info("获取到图片消息：{}");
				break;
			case 2: // 语音 识别后,适配于文本关键字回复 voice 是高级接口,认证服务号才有.这里下载后,服务端需要要把amr格式转成mp3
				log.info("语音消息:{}");
				break;
			case 3: // 视频
				log.info("视频消息:{}");
				break;
			case 4:
				// 地理位置 高级接口
				log.info("地理位置消息:{}");
				break;
			case 5:
				log.info("消息5:{}");
				break;
			case 6:// 音乐
				log.info("音乐消息5:{}");
				break;
			case 7:
				break;
			case 8: // 关注
				result = handleSubscribe(orgInfo, toUserName, fromUserName, date);
				// 自动生成一条粉丝记录,之后通过粉丝自动生成一条客户档案信息
				log.info("用户关注了：{},{}", toUserName, fromUserName);
				break;
			case 9: // 取消关注
				result = handleUnsubscribe(orgInfo, toUserName, fromUserName, date);
				// todo3:自动把该粉丝的状态置为 已经取消关注
				log.info("用户取消关注了:{},{}", toUserName, fromUserName);
				break;
			case 10: // 扫描
				String sceneId = StringUtil.toString(xmlMap.get("eventKey"));
				String ticket = StringUtil.toString(xmlMap.get("ticket"));
				result = handleScanSubscribe(orgInfo, toUserName, fromUserName, date, sceneId, ticket);
				log.info("用户 扫描:sceneId:{},ticket{},{},{}", sceneId, ticket, toUserName, fromUserName);
				break;
			case 11: // 扫描关注
				String sceneId11 = StringUtil.toString(xmlMap.get("eventKey"));
				if (!StringUtils.isEmpty(sceneId11)) {
					sceneId11 = sceneId11.split("_")[1];
				}
				String ticket11 = StringUtil.toString(xmlMap.get("ticket"));
				log.info("用户 扫描:sceneId:{},ticket11{},{},{}", sceneId11, ticket11, toUserName, fromUserName);
				result = handleScanSubscribe(orgInfo, toUserName, fromUserName, date, sceneId11, ticket11);
				break;
			case 12: // 上报地理位置
				result = handleLatitude(orgInfo, toUserName, fromUserName, date, "");
				log.info("上报地理位置");
				break;
			case 13: // 菜单点击
				String eventKey13 = xmlMap.get("eventKey").toString();
				result = handleClick(orgInfo, toUserName, fromUserName, date, eventKey13);
				result = PaUtil.responseText(toUserName, fromUserName, (date).getTime(), "感谢您的关注，我们会持续完善功能，请持续关注:www.wlgdo.com");
				log.info(" 菜单点击:{}", eventKey13);
				break;
			case 14:// 菜单跳转
				log.info("菜单跳转 eventKey13:{}");
				break;
			default: // 默认
				result = PaUtil.responseText(toUserName, fromUserName, (date).getTime(), "Unknow msg type: " + msgType);
				log.info("未知的事件类型");
				break;
			}
			result = PaUtil.responseText(toUserName, fromUserName, (date).getTime(), "感谢您的关注，我们会持续完善功能，请持续关注:wlgdo");
			if (!StringUtil.isEmpty(result)) {
				log.info("将要返回微信的消息内容：{}", result);
			}

			responseBody(response, result);
		} catch (Exception e) {
			log.error("事件处理异常了：{}", e);
		}
	}

	// 上报地理位置，响应的结果
	public static String handleLatitude(OrgInfo OrgInfo, String toUserName, String fromUserName, Date date, String latitude) {

		return "";
	}

	public static String handleText(OrgInfo OrgInfo, String toUserName, String fromUserName, Date date, String content) {
		return "";
	}

	public static String handleSubscribe(OrgInfo OrgInfo, String toUserName, String fromUserName, Date date) {
		return "";
	}

	public static String handleUnsubscribe(OrgInfo OrgInfo, String toUserName, String fromUserName, Date date) {
		return "";
	}

	public static String handleScanSubscribe(OrgInfo OrgInfo, String toUserName, String fromUserName, Date date, String sceneId, String ticket) {
		return "";
	}

	public static String handleClick(OrgInfo OrgInfo, String toUserName, String fromUserName, Date date, String eventKey) {

		return "";
	}

	/**
	 * 向微信返回响应消息
	 * 
	 * @param response
	 * @param str
	 */
	public static void responseBody(ServletResponse response, String responseMsg) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.print(responseMsg);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public static void sendTempletMsg(String openid, String templateId, JSONObject data, String url, String accessToken) {
		String TEMPLATE_MSG_API = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN",
				"wpOm3OJUb3mNQmCSEkTUjjff_PWj0LBCU6OTDd2HVwyqrZY863j7ocg7YjLRhrqQii2A39GufRmNnI6Ib1J0gWB927nlyH-DaEjx_dLLGokBABdAEAPOP");
		JSONObject json = new JSONObject();
		json.put("touser", openid);
		json.put("template_id", templateId);
		json.put("topcolor", "#FF0000");
		json.put("url", url);// "https://www.wlgdo.com/lunck/h/awards/award.html#0"

		/*
		 * JSONObject orgName = new JSONObject(); orgName.put("value", "创软科技");
		 * orgName.put("color", "#173177"); data.put("orgName", orgName);
		 * 
		 * JSONObject awardName = new JSONObject(); awardName.put("value", "双12全民参与大奖");
		 * awardName.put("color", "#173177");
		 * 
		 * data.put("orgName", orgName); data.put("awardName", awardName);
		 */
		json.put("data", data);
		// doPost(TEMPLATE_MSG_API, json.toString());
	}
}
