package com.lee.controller;

import com.lee.domain.vo.LinkVo;
import com.lee.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("link")
public class LinkController {

    @Autowired
    LinkService linkService;

    @GetMapping("getAllLink")
    public List<LinkVo> getAllLink() {
        return linkService.getAllLink();
    }

}
