package com.ecommerce.main.controllers;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @RequestMapping(value="/upload/{subdir}",method=RequestMethod.POST)
	public @ResponseBody String uploadFile(@RequestParam("file") MultipartFile file,@PathVariable String subdir) {
	/*
    	String str= file.getOriginalFilename();
    	String []parts = str.split("\\.");
    	System.out.println("Str : "+str+"  parts"+parts);
    	String url= URLPaths.IMG_PATH+subdir+"/"+"hello"+parts[1];*/
    	
    	//path of the directory..
    	String url= URLPaths.IMG_PATH+subdir+"/"+file.getOriginalFilename();
    
    	File convertFile=new File(url);
		System.out.println(url);
		convertFile.getParentFile().mkdirs();
		try {
			convertFile.createNewFile();
			FileOutputStream fout =new FileOutputStream(convertFile);
			fout.write(file.getBytes());
			fout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "IO Error";
		}
		
		return url;
		
	}
    
}