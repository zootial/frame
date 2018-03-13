package ${controllerPackage};

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
import ${domainPackage}.${domainClassName};
<#if servicePackage??>
import ${servicePackage}.${domainClassName}${serviceSuffix};
<#elseif serviceImplPackage??>
import ${serviceImplPackage}.${domainClassName}${serviceSuffix}${implSuffix};
</#if>

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date ${.now?string("yyyy-MM-dd hh:mm:ss")}
 */
@Controller
@RequestMapping("/${domainClassName?uncap_first}")
public class ${domainClassName}${controllerSuffix} {

    <#if servicePackage??>
    @Autowired
    private ${domainClassName}${serviceSuffix} ${domainClassName?uncap_first}${serviceSuffix};
    <#elseif serviceImplPackage??>
    @Autowired
    private ${domainClassName}${serviceSuffix}${implSuffix} ${domainClassName?uncap_first}${serviceSuffix};
    </#if>

    <#if json>
    /**
     * for JSON
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ${domainClassName} get(@PathVariable String id) {
        ${domainClassName} model = ${domainClassName?uncap_first}${serviceSuffix}.findOne(Long.valueOf(id));
        return model;
    }
    </#if>

    <#if html>
    /**
     * for HTML
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String view(@PathVariable String id, ModelMap map) {
        ${domainClassName} model = ${domainClassName?uncap_first}${serviceSuffix}.findOne(Long.valueOf(id));
        map.put("${domainClassName?uncap_first}", model);
        return "";
    }
    </#if>

    <#if json>
    /**
     * for JSON
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ActionResult create(@RequestBody String content) {
        ${domainClassName} model = JSON.parseObject(content, ${domainClassName}.class);
        ${domainClassName?uncap_first}${serviceSuffix}.save(model);
        return ActionResult.success(model.getId());
    }
    </#if>

    <#if html>
    /**
     * for HTML
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String add(@ModelAttribute("${domainClassName?uncap_first}") ${domainClassName} model) {
        ${domainClassName?uncap_first}${serviceSuffix}.save(model);
        return "";
    }
    </#if>

    <#if json>
    /**
     * for JSON
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ActionResult delete(@PathVariable String id) {
        ${domainClassName?uncap_first}${serviceSuffix}.delete(Long.valueOf(id));
        return ActionResult.success();
    }
    </#if>

    <#if html>
    /**
     * for HTML
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String remove(@PathVariable String id) {
        ${domainClassName?uncap_first}${serviceSuffix}.delete(Long.valueOf(id));
        return "";
    }
    </#if>

    <#if json>
    /**
     * for JSON
     */
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ActionResult update(@RequestBody String content) {
        ${domainClassName} model = JSON.parseObject(content, ${domainClassName}.class);
        ${domainClassName?uncap_first}${serviceSuffix}.save(model);
        return ActionResult.success();
    }
    </#if>

    <#if html>
    /**
     * for HTML
     */
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.TEXT_HTML_VALUE)
    public String modify(@ModelAttribute("${domainClassName?uncap_first}") ${domainClassName} model) {
        ${domainClassName?uncap_first}${serviceSuffix}.save(model);
        return "";
    }
    </#if>
}
