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

import com.zzx.authorization.domain.ResAction;
import com.zzx.authorization.bo.ResActionBo;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date 2018-02-01 12:14:31
 */
@Controller
@RequestMapping("/resAction")
public class ResActionController {

	@Autowired
	private ResActionBo resActionBo;
	
	/**
	 * for JSON
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{id}",
			produces="application/json")
	@ResponseBody
	public ResAction get(@PathVariable String id) {
		return null;
	}
	
	/**
	 * for HTML
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{id}",
			produces="text/html")
	public String view(@PathVariable String id, ModelMap map) {
		ResAction model = null;
		map.put("resAction", model);
		return "";
	}
	
	/**
	 * for JSON
	 */
	//@RequestMapping(method=RequestMethod.POST, value="/add",
	//		produces="application/json", consumes="application/json")
    //@ResponseBody
	//public JsonResult add(@RequestBody String content) {
	//	ResAction model = JSONObject.parseObject(content, ResAction.class);
	//	resActionBo.add(model);
	//	return JsonResult.success(model);
	//}
	
	/**
	 * for HTML
	 */
	@RequestMapping(method=RequestMethod.POST, value="/add",
			produces="text/html")
	public String add(@ModelAttribute("resAction") ResAction model) {
		//resActionBo.add(model);
		return "";
	}
	
	/**
	 * for JSON
	 */
	//@RequestMapping(method=RequestMethod.POST, value="/{id}",
	//		produces="application/json")
	//@ResponseBody
	//public JsonResult remove(@PathVariable String id) {
	//	int count = resActionBo.removeById(Long.valueOf(id));
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
		//resActionBo.removeById(Long.valueOf(id));
		return "";
	}

	/**
	 * for JSON
	 */
	//@RequestMapping(method=RequestMethod.POST, value="/modify",
	//		produces="application/json", consumes="application/json")
	//@ResponseBody
	//public JsonResult modify(@RequestBody String content) {
	//	ResAction model = JSONObject.parseObject(content, ResAction.class);
	//	int count = resActionBo.modify(model);
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
	public String modify(@ModelAttribute("resAction") ResAction model) {
		//resActionBo.modify(model);
		return "";
	}
}
