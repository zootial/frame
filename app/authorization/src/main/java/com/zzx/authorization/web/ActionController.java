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

import com.zzx.authorization.domain.Action;
import com.zzx.authorization.bo.ActionBo;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-02-01 12:14:31
 */
@Controller
@RequestMapping("/action")
public class ActionController {

	@Autowired
	private ActionBo actionBo;
	
	/**
	 * for JSON
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{id}",
			produces="application/json")
	@ResponseBody
	public Action get(@PathVariable String id) {
		return null;
	}
	
	/**
	 * for HTML
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{id}",
			produces="text/html")
	public String view(@PathVariable String id, ModelMap map) {
		Action model = null;
		map.put("action", model);
		return "";
	}
	
	/**
	 * for JSON
	 */
	//@RequestMapping(method=RequestMethod.POST, value="/add",
	//		produces="application/json", consumes="application/json")
    //@ResponseBody
	//public JsonResult add(@RequestBody String content) {
	//	Action model = JSONObject.parseObject(content, Action.class);
	//	actionBo.add(model);
	//	return JsonResult.success(model);
	//}
	
	/**
	 * for HTML
	 */
	@RequestMapping(method=RequestMethod.POST, value="/add",
			produces="text/html")
	public String add(@ModelAttribute("action") Action model) {
		//actionBo.add(model);
		return "";
	}
	
	/**
	 * for JSON
	 */
	//@RequestMapping(method=RequestMethod.POST, value="/{id}",
	//		produces="application/json")
	//@ResponseBody
	//public JsonResult remove(@PathVariable String id) {
	//	int count = actionBo.removeById(Long.valueOf(id));
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
		//actionBo.removeById(Long.valueOf(id));
		return "";
	}

	/**
	 * for JSON
	 */
	//@RequestMapping(method=RequestMethod.POST, value="/modify",
	//		produces="application/json", consumes="application/json")
	//@ResponseBody
	//public JsonResult modify(@RequestBody String content) {
	//	Action model = JSONObject.parseObject(content, Action.class);
	//	int count = actionBo.modify(model);
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
	public String modify(@ModelAttribute("action") Action model) {
		//actionBo.modify(model);
		return "";
	}
}
