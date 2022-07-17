package com.lee.controller;

import com.lee.domain.ResponseResult;
import com.lee.domain.entity.Article;
import com.lee.enums.AppHttpCodeEnum;
import com.lee.service.EsArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "EsArticleController", description = "搜索文章管理")
@RequestMapping("/esArticle")
public class EsArticleController {

    @Autowired
    private EsArticleService esArticleService;

    @ApiOperation(value = "导入所有数据库中文章到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    @ResponseBody
    public int importAllList() {
        return esArticleService.importAll();
    }

    @ApiOperation(value = "根据id删除ES中文章")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void delete(@PathVariable Long id) {
        esArticleService.delete(id);
    }

    @ApiOperation(value = "根据id批量删除ES中文章")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public void delete(@RequestParam("ids") List<Long> ids) {
        esArticleService.delete(ids);
    }

    @ApiOperation(value = "根据id创建es中文章")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult create(@PathVariable Long id) {
        Article article = esArticleService.create(id);
        if (article != null) {
            return ResponseResult.okResult(article);
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.ES_CREATE_FAIL);
        }
    }

}
