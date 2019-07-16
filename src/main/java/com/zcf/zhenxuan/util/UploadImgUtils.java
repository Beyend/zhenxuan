package com.zcf.zhenxuan.util;


import java.io.File;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class UploadImgUtils {
	private static final Logger logger = LoggerFactory
			.getLogger(UploadImgUtils.class);

	//private  static   String  url="http://192.168.31.163:8083/allroom/";
	private  static   String  url="http://47.99.94.250:80/allroom/";

	/**
	 * 上传图片
	 * 
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public static String uploadFiles(MultipartFile[] files) throws Exception {

		String path = "";
		String urls = "";
		try {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					MultipartFile mf = files[i];
					if (!mf.isEmpty()) {
						// 生成uuid作为文件名称
						String uuid = UUID.randomUUID().toString()
								.replaceAll("-", "");
						// 获得文件类型（可以判断如果不是图片，禁止上传）
						String contentType = mf.getContentType();
						logger.info("= = = > 图片类型：{}", contentType);
						// 获得文件后缀名称
						String imageAfterName = contentType
								.substring(contentType.indexOf("/") + 1);
						path =  uuid + "." + imageAfterName;
						File newFile = new File("C:/allroom/" + path);
						logger.info("= = = > 图片创建位置：{}", "C:/allroom/" + path);
						// 如果文件夹不存在则创建
//						if (!newFile.exists() && !newFile.isDirectory()) {
//							newFile.mkdirs();
//						}
						mf.transferTo(newFile);
						if (i == files.length - 1) {
							urls += url + path;
						} else {
							urls += url  + path + ",";
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("#### 上传图片异常");
		}
		logger.info("= =  > 生成图片URL：{}", urls);
		return urls;
	}

}
