/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blogue.springboot.controller;

import blogue.springboot.domain.Blogue;
import blogue.springboot.service.BlogueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author DyoRizqal
 */
@RestController
@RequestMapping("/api/blogue")
public class BlogueController {
    @Autowired
    private BlogueDao blogueDao;
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Blogue findBlogueById(@PathVariable String id)
    {
        return blogueDao.findOne(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Blogue c)
    {
        blogueDao.save(c);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public Page<Blogue> findAll(Pageable pageable)
    {
        return blogueDao.findAll(pageable);
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable String id)
    {
        Blogue c = blogueDao.findOne(id);
        if (c != null) {
            blogueDao.delete(c);
        }
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public void edit(@PathVariable String id, @RequestBody Blogue c)
    {
        Blogue blogue = blogueDao.findOne(id);
        if (blogue != null) {
            c.setId(id);
            blogueDao.save(c);
        }
    }
}
