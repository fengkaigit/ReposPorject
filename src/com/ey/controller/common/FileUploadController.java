package com.ey.controller.common;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ey.consts.SystemConst;
import com.ey.util.RequestUtils;

@Controller
public class FileUploadController {
       
	@RequestMapping(value = "/upload", method = RequestMethod.POST)   
    public ModelAndView upload(@RequestParam("file")  MultipartFile uploadFile, HttpServletRequest request) throws IOException {   
        try {   
        	uploadFile.transferTo(new File(RequestUtils.getContextDirectory(SystemConst.CONTEXT_ATTACHEDIR, request) + uploadFile.getOriginalFilename())); // 保存上传的文件   
        } catch (IllegalStateException e) {   
            e.printStackTrace();   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
        request.setAttribute("files", loadFiles(request));   
        return new ModelAndView("saveok","message","上传成功");   
    }  
	// 多文件上传   
    @RequestMapping(value = "/uploads", method = RequestMethod.POST)   
    public ModelAndView fileUpload(HttpServletRequest request,   
            HttpServletResponse response)   
            throws Exception {   
  
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();   
        String dirPath = RequestUtils.getContextDirectory(SystemConst.CONTEXT_ATTACHEDIR, request);
        String fileName = null;   
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {   
            MultipartFile mf = entity.getValue();   
            fileName = mf.getOriginalFilename();   
            mf.transferTo(new File(dirPath + fileName)); 
        }   
        request.setAttribute("files", loadFiles(request));   
        return new ModelAndView("success");   
    }   
  
    // @ModelAttribute("files")//此属性用于初始类时调用,但上传文件后不能时时反应上传文件个数,不适合动态数据   
    public List<String> loadFiles(HttpServletRequest request) {   
        List<String> files = new ArrayList<String>();   
        String ctxPath = request.getSession().getServletContext().getRealPath(   
                "/")   
                + "\\" + "images\\";   
        File file = new File(ctxPath);   
        if (file.exists()) {   
            File[] fs = file.listFiles();   
            String fname = null;   
            for (File f : fs) {   
                fname = f.getName();   
                if (f.isFile()) {   
                    files.add(fname);   
                }   
            }   
        }   
        return files;   
    }   
  
    @RequestMapping("/download/{fileName}")   
    public ModelAndView download(@PathVariable("fileName")   
    String fileName, HttpServletRequest request, HttpServletResponse response)   
            throws Exception {   
  
        response.setContentType("text/html;charset=utf-8");   
        request.setCharacterEncoding("UTF-8");   
        BufferedInputStream bis = null;   
        BufferedOutputStream bos = null;   
  
        String dirPath = RequestUtils.getContextDirectory(SystemConst.CONTEXT_ATTACHEDIR, request);
   
        String downLoadPath = dirPath + fileName;   
        try {   
            long fileLength = new File(downLoadPath).length();   
            response.setContentType("application/x-msdownload;");   
            response.setHeader("Content-disposition", "attachment; filename="  
                    + new String(fileName.getBytes("utf-8"), "ISO8859-1"));   
            response.setHeader("Content-Length", String.valueOf(fileLength));   
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));   
            bos = new BufferedOutputStream(response.getOutputStream());   
            byte[] buff = new byte[2048];   
            int bytesRead;   
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {   
                bos.write(buff, 0, bytesRead);   
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            if (bis != null)   
                bis.close();   
            if (bos != null)   
                bos.close();   
        }   
        return null;   
    }  
}
