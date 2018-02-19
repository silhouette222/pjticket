package com.ticket.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ticket.utils.MediaUtils;
import com.ticket.utils.UploadFile;

@Controller
public class UploadController {
	@Resource(name = "uploadPath")
	private String uploadPath;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public void uploadForm() {
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> uploadFile(MultipartFile file)
			throws Exception {
		return new ResponseEntity<String>(UploadFile.uploadFile(uploadPath,
				file.getOriginalFilename(), file.getBytes()),
				HttpStatus.CREATED);
	}

	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName)
										throws Exception{
		
		InputStream in=null;
		ResponseEntity<byte[]> entity=null;
		try{
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			MediaType mType=MediaUtils.getMediaType(formatName);
			HttpHeaders headers=new HttpHeaders();
			
			fileName=fileName.replace('/', File.separatorChar);
			
			
			
			if(mType!=null){
				headers.setContentType(mType);
			}else{
				fileName=fileName.substring(fileName.indexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment;filename=\""+
				new String(fileName.getBytes("8859_1"),"utf-8")+"\"");
			}
			String asd=new String(fileName.getBytes("8859_1"),"utf-8");
			in = new FileInputStream(uploadPath+asd);
			entity=new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
					headers,HttpStatus.CREATED);
			
		}catch(IOException e){
			e.printStackTrace();
			entity=new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally{
			in.close();
		}
		
		return entity;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) throws Exception {
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

		MediaType mType = MediaUtils.getMediaType(formatName);

		if (mType != null) {
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			new File(uploadPath
					+ (front + end).replace('/', File.separatorChar)).delete();
		}
		new File(uploadPath + fileName.replace('/', File.separatorChar))
				.delete();

		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/deleteAllFiles", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(
			@RequestParam("files[]") String[] files) throws Exception {
		if (files == null || files.length == 0) {
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		}

		for (String fileName : files) {
			String formatName = fileName
					.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);

			if (mType != null) {
				String front = fileName.substring(0, 12);
				String end = fileName.substring(14);
				new File(uploadPath
						+ (front + end).replace('/', File.separatorChar))
						.delete();
			}

			new File(uploadPath + fileName.replace('/', File.separatorChar))
					.delete();
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
}
