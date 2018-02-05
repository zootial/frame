package com.zzx.authorization.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzx.authorization.domain.UserPermission;
import com.zzx.authorization.bo.UserPermissionBo;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-02-01 12:14:31
 */
@Controller
@RequestMapping("/userPermission")
public class UserPermissionController {

	@Autowired
	private UserPermissionBo userPermissionBo;
	
	/**
	 * for JSON
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{id}",
			produces="application/json")
	@ResponseBody
	public UserPermission get(@PathVariable String id) {
		return null;
	}
	
	/**
	 * for HTML
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{id}",
			produces="text/html")
	public String view(@PathVariable String id, ModelMap map) {
		UserPermission model = null;
		map.put("userPermission", model);
		return "";
	}
	
	/**
	 * for JSON
	 */
	//@RequestMapping(method=RequestMethod.POST, value="/add",
	//		produces="application/json", consumes="application/json")
    //@ResponseBody
	//public JsonResult add(@RequestBody String content) {
	//	UserPermission model = JSONObject.parseObject(content, UserPermission.class);
	//	userPermissionBo.add(model);
	//	return JsonResult.success(model);
	//}
	
	/**
	 * for HTML
	 */
	@RequestMapping(method=RequestMethod.POST, value="/add",
			produces="text/html")
	public String add(@ModelAttribute("userPermission") UserPermission model) {
		//userPermissionBo.add(model);
		return "";
	}
	
	/**
	 * for JSON
	 */
	//@RequestMapping(method=RequestMethod.POST, value="/{id}",
	//		produces="application/json")
	//@ResponseBody
	//public JsonResult remove(@PathVariable String id) {
	//	int count = userPermissionBo.removeById(Long.valueOf(id));
	//	if(count > 0){
	//		return JsonResult.success(count);
	//	} else {
	//		return JsonResult.fail(count);
	//	}
	//}
	
	/**
	 * for HTML
	 */
	@RequestMapping(method=RequestMethod.POST, value="/{id}",
			produces="text/html")
	public String del(@PathVariable String id) {
		//userPermissionBo.removeById(Long.valueOf(id));
		return "";
	}

	/**
	 * for JSON
	 */
	//@RequestMapping(method=RequestMethod.POST, value="/modify",
	//		produces="application/json", consumes="application/json")
	//@ResponseBody
	//public JsonResult modify(@RequestBody String content) {
	//	UserPermission model = JSONObject.parseObject(content, UserPermission.class);
	//	int count = userPermissionBo.modify(model);
	//	if(count > 0){
	//		return JsonResult.success(count);
	//	} else {
	//		return JsonResult.fail(count);
	//	}
	//}
	
	/**
	 * for HTML
	 */
	@RequestMapping(method=RequestMethod.POST, value="/modify",
			produces="text/html")
	public String modify(@ModelAttribute("userPermission") UserPermission model) {
		//userPermissionBo.modify(model);
		return "";
	}
}
