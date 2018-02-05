package ${controllerPackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping(method=RequestMethod.GET, value="/{id}",
			produces="application/json")
	@ResponseBody
	public ${domainClassName} get(@PathVariable String id) {
		return null;
	}
	</#if>
	
	<#if html>
	/**
	 * for HTML
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{id}",
			produces="text/html")
	public String view(@PathVariable String id, ModelMap map) {
		${domainClassName} model = null;
		map.put("${domainClassName?uncap_first}", model);
		return "";
	}
	</#if>
	
	<#if json>
	/**
	 * for JSON
	 */
	//@RequestMapping(method=RequestMethod.POST, value="/add",
	//		produces="application/json", consumes="application/json")
    //@ResponseBody
	//public JsonResult add(@RequestBody String content) {
	//	${domainClassName} model = JSONObject.parseObject(content, ${domainClassName}.class);
	//	${domainClassName?uncap_first}${serviceSuffix}.add(model);
	//	return JsonResult.success(model);
	//}
	</#if>
	
	<#if html>
	/**
	 * for HTML
	 */
	@RequestMapping(method=RequestMethod.POST, value="/add",
			produces="text/html")
	public String add(@ModelAttribute("${domainClassName?uncap_first}") ${domainClassName} model) {
		//${domainClassName?uncap_first}${serviceSuffix}.add(model);
		return "";
	}
	</#if>
	
	<#if json>
	/**
	 * for JSON
	 */
	//@RequestMapping(method=RequestMethod.POST, value="/{id}",
	//		produces="application/json")
	//@ResponseBody
	//public JsonResult remove(@PathVariable String id) {
	//	int count = ${domainClassName?uncap_first}${serviceSuffix}.removeById(Long.valueOf(id));
	//	if(count > 0){
	//		return JsonResult.success(count);
	//	} else {
	//		return JsonResult.fail(count);
	//	}
	//}
	</#if>
	
	<#if html>
	/**
	 * for HTML
	 */
	@RequestMapping(method=RequestMethod.POST, value="/{id}",
			produces="text/html")
	public String del(@PathVariable String id) {
		//${domainClassName?uncap_first}${serviceSuffix}.removeById(Long.valueOf(id));
		return "";
	}
	</#if>

	<#if json>
	/**
	 * for JSON
	 */
	//@RequestMapping(method=RequestMethod.POST, value="/modify",
	//		produces="application/json", consumes="application/json")
	//@ResponseBody
	//public JsonResult modify(@RequestBody String content) {
	//	${domainClassName} model = JSONObject.parseObject(content, ${domainClassName}.class);
	//	int count = ${domainClassName?uncap_first}${serviceSuffix}.modify(model);
	//	if(count > 0){
	//		return JsonResult.success(count);
	//	} else {
	//		return JsonResult.fail(count);
	//	}
	//}
	</#if>
	
	<#if html>
	/**
	 * for HTML
	 */
	@RequestMapping(method=RequestMethod.POST, value="/modify",
			produces="text/html")
	public String modify(@ModelAttribute("${domainClassName?uncap_first}") ${domainClassName} model) {
		//${domainClassName?uncap_first}${serviceSuffix}.modify(model);
		return "";
	}
	</#if>
}
