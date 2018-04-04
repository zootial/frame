package com.zzx.authorization.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.zzx.common.entity.ActionResult;
import com.zzx.authorization.domain.User;
import com.zzx.authorization.bo.UserBo;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-03-30 03:08:06
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBo userBo;

    /**
     * for JSON
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User get(@PathVariable String id) {
        User model = userBo.findOne(Long.valueOf(id));
        return model;
    }

    /**
     * for HTML
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String view(@PathVariable String id, ModelMap map) {
        User model = userBo.findOne(Long.valueOf(id));
        map.put("user", model);
        return "";
    }

    /**
     * for JSON
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ActionResult create(@RequestBody String content) {
        User model = JSON.parseObject(content, User.class);
        userBo.save(model);
        return ActionResult.success(model.getId());
    }

    /**
     * for HTML
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String add(@ModelAttribute("user") User model) {
        userBo.save(model);
        return "";
    }

    /**
     * for JSON
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ActionResult delete(@PathVariable String id) {
        userBo.delete(Long.valueOf(id));
        return ActionResult.success();
    }

    /**
     * for HTML
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String remove(@PathVariable String id) {
        userBo.delete(Long.valueOf(id));
        return "";
    }

    /**
     * for JSON
     */
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ActionResult update(@RequestBody String content) {
        User model = JSON.parseObject(content, User.class);
        userBo.save(model);
        return ActionResult.success();
    }

    /**
     * for HTML
     */
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.TEXT_HTML_VALUE)
    public String modify(@ModelAttribute("user") User model) {
        userBo.save(model);
        return "";
    }
}
