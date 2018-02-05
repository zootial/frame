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

import com.zzx.authorization.domain.TeamPermission;
import com.zzx.authorization.bo.TeamPermissionBo;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-02-01 12:14:31
 */
@Controller
@RequestMapping("/teamPermission")
public class TeamPermissionController {

	@Autowired
	private TeamPermissionBo teamPermissionBo;
	
	/**
	 * for JSON
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{id}",
			produces="application/json")
	@ResponseBody
	public TeamPermission get(@PathVariable String id) {
		return null;
	}
	
	/**
	 * for HTML
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{id}",
			produces="text/html")
	public String view(@PathVariable String id, ModelMap map) {
		TeamPermission model = null;
		map.put("teamPermission", model);
		return "";
	}
	
	/**
	 * for JSON
	 */
	//@RequestMapping(method=RequestMethod.POST, value="/add",
	//		produces="application/json", consumes="application/json")
    //@ResponseBody
	//public JsonResult add(@RequestBody String content) {
	//	TeamPermission model = JSONObject.parseObject(content, TeamPermission.class);
	//	teamPermissionBo.add(model);
	//	return JsonResult.success(model);
	//}
	
	/**
	 * for HTML
	 */
	@RequestMapping(method=RequestMethod.POST, value="/add",
			produces="text/html")
	public String add(@ModelAttribute("teamPermission") TeamPermission model) {
		//teamPermissionBo.add(model);
		return "";
	}
	
	/**
	 * for JSON
	 */
	//@RequestMapping(method=RequestMethod.POST, value="/{id}",
	//		produces="application/json")
	//@ResponseBody
	//public JsonResult remove(@PathVariable String id) {
	//	int count = teamPermissionBo.removeById(Long.valueOf(id));
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
		//teamPermissionBo.removeById(Long.valueOf(id));
		return "";
	}

	/**
	 * for JSON
	 */
	//@RequestMapping(method=RequestMethod.POST, value="/modify",
	//		produces="application/json", consumes="application/json")
	//@ResponseBody
	//public JsonResult modify(@RequestBody String content) {
	//	TeamPermission model = JSONObject.parseObject(content, TeamPermission.class);
	//	int count = teamPermissionBo.modify(model);
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
	public String modify(@ModelAttribute("teamPermission") TeamPermission model) {
		//teamPermissionBo.modify(model);
		return "";
	}
}
