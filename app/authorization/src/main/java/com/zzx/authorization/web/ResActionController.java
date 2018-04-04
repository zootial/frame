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
import com.zzx.authorization.domain.ResAction;
import com.zzx.authorization.bo.ResActionBo;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-03-30 03:08:06
 */
@Controller
@RequestMapping("/resAction")
public class ResActionController {

    @Autowired
    private ResActionBo resActionBo;

    /**
     * for JSON
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResAction get(@PathVariable String id) {
        ResAction model = resActionBo.findOne(Long.valueOf(id));
        return model;
    }

    /**
     * for HTML
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String view(@PathVariable String id, ModelMap map) {
        ResAction model = resActionBo.findOne(Long.valueOf(id));
        map.put("resAction", model);
        return "";
    }

    /**
     * for JSON
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ActionResult create(@RequestBody String content) {
        ResAction model = JSON.parseObject(content, ResAction.class);
        resActionBo.save(model);
        return ActionResult.success(model.getId());
    }

    /**
     * for HTML
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String add(@ModelAttribute("resAction") ResAction model) {
        resActionBo.save(model);
        return "";
    }

    /**
     * for JSON
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ActionResult delete(@PathVariable String id) {
        resActionBo.delete(Long.valueOf(id));
        return ActionResult.success();
    }

    /**
     * for HTML
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String remove(@PathVariable String id) {
        resActionBo.delete(Long.valueOf(id));
        return "";
    }

    /**
     * for JSON
     */
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ActionResult update(@RequestBody String content) {
        ResAction model = JSON.parseObject(content, ResAction.class);
        resActionBo.save(model);
        return ActionResult.success();
    }

    /**
     * for HTML
     */
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.TEXT_HTML_VALUE)
    public String modify(@ModelAttribute("resAction") ResAction model) {
        resActionBo.save(model);
        return "";
    }
}
