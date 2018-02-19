package com.ticket.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class UploadFile {
	public static String uploadFile(String uploadPath, String originalName,
			byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString().replace("-", "") + "_" + originalName;
		String savedPath = calcPath(uploadPath);

		File target = new File(uploadPath + savedPath, savedName); // 파일생성

		FileCopyUtils.copy(fileData, target);// 파일저장

		String formatName = originalName.substring(originalName
				.lastIndexOf(".") + 1);

		String uploadFileName = null;

		// 업로드 파일의 이미지 여부 확인
		if (MediaUtils.getMediaType(formatName) != null) {
			// 썸네일 형태로 보여주기
			uploadFileName = makeThumbnail(uploadPath, savedPath, savedName);
		} else {
			// 텍스트 형태로 보여주기
			uploadFileName = makeIcon(uploadPath, savedPath, savedName);
		}

		return uploadFileName;
	}

	public static String makeIcon(String uploadPath, String path,
			String fileName) throws Exception {

		String iconName = uploadPath + path + File.separator + fileName;

		return iconName.substring(uploadPath.length()).replace(
				File.separatorChar, '/');
	}

	public static String makeThumbnail(String uploadPath, String path,
			String fileName) throws Exception {

		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path,
				fileName));

		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC,
				Scalr.Mode.FIT_TO_HEIGHT, 100);
		String thumbnailName = uploadPath + path + File.separator + "s_"
				+ fileName;

		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

		ImageIO.write(destImg, formatName.toUpperCase(), newFile);

		return thumbnailName.substring(uploadPath.length()).replace(
				File.separatorChar, '/');
	}

	public static String calcPath(String uploadPath) throws Exception {

		Calendar cal = Calendar.getInstance();

		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator
				+ new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator
				+ new DecimalFormat("00").format(cal.get(Calendar.DATE));

		makeDir(uploadPath, yearPath, monthPath, datePath);

		return datePath;
	}

	public static void makeDir(String uploadPath, String... paths) {
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}
}
