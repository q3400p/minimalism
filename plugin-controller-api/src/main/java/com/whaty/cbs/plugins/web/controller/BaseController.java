package com.whaty.cbs.plugins.web.controller;

import com.whaty.cbs.plugins.web.response.JsonResponseReturn;
import com.whaty.cbs.plugins.web.response.ResponseReturn;
import com.whaty.cbs.plugins.web.session.Session;
import com.whaty.cbs.plugins.web.session.WebSession;
import com.windf.core.Constant;
import com.windf.core.entity.BaseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 控制层的父类，用于提供api，基于spring MVC
 * @param <T>
 */
public abstract class BaseController<T extends BaseEntity> {

    /**
     * 获取request
     * @return
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取response
     * @return
     */
    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取ResponseReturn {@link ResponseReturn}
     * 用于返回数据
     * @return
     */
    public ResponseReturn response() {
        return new JsonResponseReturn();
    }

    /**
     * 获取session
     * @return
     */
    public Session getSession() {
        return new WebSession(this.getRequest().getSession());
    }

    /**
     * 获取文件
     * @param multipartFile
     * @return
     */
    public File getFile(MultipartFile multipartFile) {
        // TODO 通用文件上传修改
        return null;
    }

    /**
     * 文件下载
     * @param file
     */
    public void download(File file, String fileName) {
        HttpServletRequest request = this.getRequest();
        HttpServletResponse response = this.getResponse();

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding(Constant.DEFAULT_ENCODING);

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            long fileLength = file.length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(Constant.DEFAULT_ENCODING), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(file));
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
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }
}
