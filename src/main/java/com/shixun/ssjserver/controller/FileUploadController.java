package com.shixun.ssjserver.controller;

import com.shixun.ssjserver.dao.ArticleDao;
import com.shixun.ssjserver.dao.UserDao;
import com.shixun.ssjserver.domain.ArticleEntity;
import com.shixun.ssjserver.domain.UserEntity;
import com.shixun.ssjserver.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/file/upload")
public class FileUploadController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleDao articleDao;

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseBody
    public Map uploadImage(HttpServletRequest req, @RequestParam("file") MultipartFile file)
    {
        Map<String, Object> map = new HashMap<>();
        try {
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            String path = ResourceUtils.getURL("classpath:static").getPath() + "/img/";
            System.out.println(path);
            FileUtil.fileupload(file.getBytes(), path, fileName);
            map.put("filename", file.getOriginalFilename());
            map.put("path", "/img/" + fileName);
        } catch (IOException e){e.printStackTrace();}
        return map;
    }

    @RequestMapping(value = "/article", method = RequestMethod.POST)
    @ResponseBody
    public Map uploadArticle(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("userid") Integer userid,
            @RequestParam("isupload") Integer isupload,
            @RequestParam("isshared") Integer isshared,
            @RequestParam("savetime") String savetime,
            @RequestParam("version") Integer version,
            @RequestParam("code") String code
            )
    {
        Map<String, Object> map = new HashMap<>();
        Optional<UserEntity> userEntityOptional = userDao.findById(userid);
        if(!userEntityOptional.isPresent())
        {
            map.put("state", "fail");
            map.put("message", "该用户不存在");
        }
        UserEntity user = userEntityOptional.get();
        ArticleEntity articleEntity = new ArticleEntity(title, content, userid, isupload, isshared, savetime, version, code);
        articleDao.save(articleEntity);
        map.put("state", "success");
        map.put("message", "上传成功");
        return map;
    }

    @RequestMapping(value = "/article/update", method = RequestMethod.POST)
    @ResponseBody
    public Map updateArticle(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("userid") Integer userid,
            @RequestParam("isupload") Integer isupload,
            @RequestParam("isshared") Integer isshared,
            @RequestParam("savetime") String savetime,
            @RequestParam("version") Integer version,
            @RequestParam("code") String code
    )
    {
        Map<String, Object> map = new HashMap<>();
        ArticleEntity article = articleDao.findByCode(code).get();
        article.setCode(content);
        article.setVersion(version);
        articleDao.save(article);
        map.put("state", "success");
        map.put("message", "更新成功");
        return map;
    }

    @RequestMapping(value = "/article/delete", method = RequestMethod.POST)
    public Map deleteArticle(@RequestParam("code") String code)
    {
        Map<String, Object> map = new HashMap<>();
        Optional<ArticleEntity> articleEntityOptional = articleDao.findByCode(code);
        if(articleEntityOptional.isPresent())
        {
            articleDao.delete(articleEntityOptional.get());
            map.put("state", "success");
            map.put("message", "删除成功");
        }
        map.put("state", "fail");
        map.put("message", "删除失败");
        return map;
    }

    @RequestMapping(value = "/article/download", method = RequestMethod.GET)
    @ResponseBody
    public Map downloadArticle(@RequestParam("userid") Integer userid)
    {
        List<ArticleEntity> articleList = articleDao.findAllByUserid(userid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("articlelist", articleList);
        return map;
    }

    @RequestMapping(value = "/article/square")
    @ResponseBody
    public Map square()
    {
        List<ArticleEntity> articleEntityList = articleDao.findAllByIsShared(1);
        HashMap<String, Object> map = new HashMap<>();
        map.put("articlelist", articleEntityList);
        return map;
    }

}
