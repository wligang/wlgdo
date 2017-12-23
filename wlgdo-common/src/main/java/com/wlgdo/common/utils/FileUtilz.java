package com.wlgdo.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 文件处理工具
 * 
 * @author wlg
 *
 */
public class FileUtilz extends FileUtils {
	static final Logger log = LoggerFactory.getLogger(FileUtils.class);

	public static final String FILE_BASE_PATH = PropertiesUtils.getVal("data_path");

	/**
	 * 上传文件
	 * 
	 * @author wlgdo[wlgchun@163.com] 2016年12月11日
	 * @param file
	 * @param targetPath
	 *            目标地址
	 * @param fileName
	 *            文件名称
	 * @return String
	 */
	public static String upload(MultipartFile file, String targetPath, String fileName) {
		try {
			if (StringUtils.isBlank(fileName)) {
				fileName = file.getOriginalFilename();
			}
			File tempFile = new File(targetPath, String.valueOf(fileName) + ".png");
			if (!tempFile.getParentFile().exists()) {
				tempFile.getParentFile().mkdir();
			}
			if (!tempFile.exists()) {
				tempFile.createNewFile();
			}
			file.transferTo(tempFile);
			return tempFile.getName();
		} catch (IOException e) {
			log.error("上传图片异常{}", e);
		}
		return null;
	}

	/**
	 * 下载图片到web前端
	 * 
	 * @author wlgdo[wlgchun@163.com] 2016年12月11日
	 * @param response
	 * @param fileName
	 * @param filePath
	 *            void
	 */
	public static void dowLoad(HttpServletResponse response, String fileName, String filePath) {
		try {
			response.setContentType("text/html;charset=utf8");
			response.getWriter().write("<img src='" + filePath + "'/>");
		} catch (IOException e) {
			log.error("下载图片异常{}", e);
		}
	}

	/**
	 * 对64位图片编码转化成图片文件
	 * 
	 * @author wlgdo[wlgchun@163.com] 2016年12月11日
	 * @param imgStr
	 * @return boolean
	 */
	public static boolean Base64ToImg(String imgStr, String targetImgPath) {
		// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		OutputStream out = null;
		try {
			// Base64解码
			String img = imgStr;// .replace("data:image/jpeg;base64,", "").replace("data:image/png;base64,",
								// "");
			byte[] b = decoder.decodeBuffer(img);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			out = new FileOutputStream(FILE_BASE_PATH + targetImgPath + ".png");
			out.write(b);
			out.flush();
			out.close();
			log.info("头像文件保存成功{}", targetImgPath);
			return true;
		} catch (Exception e) {
			log.error("头像文件保存失败{}", e);
			return false;
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 文件转化成64位字节码
	 * 
	 * @author wlgdo[wlgchun@163.com] 2016年12月11日
	 * @return String
	 */
	public static String ImageToBase64(String resouresPath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(resouresPath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			log.error("文件转64位字节码异常：{}", e);
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	public static void imageToBase64(String imgSrcPath, ServletResponse response) throws IOException {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		// 获取输出流
		String JPG = "image/png;charset=GB2312";
		File file = new File(imgSrcPath);
		OutputStream outputStream = response.getOutputStream();
		FileInputStream fileInputStream = new FileInputStream(file);
		// 读数据
		byte[] data = new byte[fileInputStream.available()];
		fileInputStream.read(data);
		fileInputStream.close();
		// 回写
		response.setContentType(JPG);
		outputStream.write(data);
		outputStream.flush();
		outputStream.close();
	}

	/**
	 * 创建文件对象
	 * 
	 * @author wlgdo[wlgchun@163.com] 2016年12月11日
	 * @param fileName
	 * @param filePaht
	 * @return File
	 */
	public static File getFile(String fileName, String filePaht) {
		return new File(filePaht, fileName);
	}

	public static void main(String[] args) {
		String imgStr = "image/jpeg;base64,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...";
		Base64ToImg(imgStr, "f://data/123123");

	}

}
