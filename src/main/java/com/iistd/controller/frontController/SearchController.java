package com.iistd.controller.frontController;


import com.iistd.entity.Search;
import com.iistd.serveiceImpl.SearchService;
import com.iistd.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/sp/user")
public class SearchController {

	@Autowired
	private SearchService searchService;

	/**
	* @Title: UserController.java
	*
	* @author wzz
	* @date keyvalues 查询的对象分类
	 */
	@RequestMapping(value = "/searchList",method = RequestMethod.GET)
	public ModelAndView searchByKeyWords(String keywords, String page) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("homepage/search/searchList");
		if(keywords == null){
			keywords = "测试";
		}

		Pager<Search> pager = searchService.findByPage(keywords,page);
		if(pager != null){
			mv.addObject("standardBaseList", pager.getPageContent());
			mv.addObject("pageTotal", pager.getPageTotal());
			mv.addObject("page", pager.getCurrentPage());
			mv.addObject("recordTotal", pager.getRecordTotal());
			mv.addObject("keywords",keywords);
		}else{
			mv.addObject("message","没有查到相关信息呦~换个词查吧");
		}



		return mv;
	}
}
