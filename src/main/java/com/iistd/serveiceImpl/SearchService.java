package com.iistd.serveiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.iistd.util.Pager;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.iistd.entity.Search;

@Service
public class SearchService{

	@Autowired
	RestClient restClient;

	@Autowired
	RestHighLevelClient client;

	@Value("${wangzz.elasticsearch.searchIndex}")
	private String searchIndex;

	@Value("${wangzz.elasticsearch.searchType}")
	private String searchType;

	/**
	 * @param keywords 搜索关键字
	 * @param page 当前页码
	 * @return
	 */
	public Pager<Search> findByPage(String keywords, String page) {
		try{
			List<Search> list = SearchData(keywords);
			Pager<Search> pager = new Pager<>(list,1, 10);
			if(page != null ) {
				pager = new Pager<>(list,Integer.parseInt(page),10);
			}
			return pager;
		}catch (Exception e){
			System.out.println(e);
			return null;
		}

	}


	public List<Search> SearchData(String keywords) throws IOException {
		//搜索请求对象
		SearchRequest searchRequest = new SearchRequest(searchIndex);
		//指定类型
		searchRequest.types(searchType);
		//搜索源构建对象
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		//boolQuery搜索方式
		//先定义一个MultiMatchQuery
		MultiMatchQueryBuilder multiMatchQueryBuilder =
				QueryBuilders.multiMatchQuery(keywords, "title", "content")
						.minimumShouldMatch("50%")
						.field("title", 10); //增加权重

		//定义一个boolQuery
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		boolQueryBuilder.must(multiMatchQueryBuilder);

		//判断当前type是否为0（全部），不是全部则添加进查询语句中
		searchSourceBuilder.query(boolQueryBuilder);
		//设置源字段过虑,第一个参数结果集包括哪些字段，第二个参数表示结果集不包括哪些字段
		//searchSourceBuilder.fetchSource(new String[]{"name","studymodel","price","timestamp"},new String[]{});

		//设置高亮
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		highlightBuilder.preTags("<tag>"); //设置起始标签
		highlightBuilder.postTags("</tag>"); //结束标签
		highlightBuilder.fields().add(new HighlightBuilder.Field("title"));
		highlightBuilder.fields().add(new HighlightBuilder.Field("content"));
		searchSourceBuilder.highlighter(highlightBuilder).size(10000);//设置总记录数

		//向搜索请求对象中设置搜索源
		searchRequest.source(searchSourceBuilder).scroll();
		//执行搜索,向ES发起http请求
		SearchResponse searchResponse = client.search(searchRequest);
		//搜索结果
		SearchHits hits = searchResponse.getHits();
		//得到匹配度高的文档
		SearchHit[] searchHits = hits.getHits();
		//日期格式化对象
		List<Search> list = new ArrayList<>();
		for(SearchHit hit:searchHits){
			Search search = new Search();
			//文档的主键
			String id = hit.getId();
			//源文档内容
			Map<String, Object> sourceAsMap = hit.getSourceAsMap();
			//源文档的name字段内容
			String title = (String) sourceAsMap.get("title"); //title
			String content = (String) sourceAsMap.get("content"); //content

			//取出高亮字段
			Map<String, HighlightField> highlightFields = hit.getHighlightFields();
			if(highlightFields!=null){
				//取出name高亮字段
				HighlightField titleLight = highlightFields.get("title");
				if(titleLight!=null){
					Text[] fragments = titleLight.getFragments();
					StringBuffer stringBuffer = new StringBuffer();
					for(Text text:fragments){
						stringBuffer.append(text);
					}
					title = stringBuffer.toString();
				}
				//取出name高亮字段
				HighlightField contentLight = highlightFields.get("content");
				if(contentLight!=null){
					Text[] fragments = contentLight.getFragments();
					StringBuffer stringBuffer = new StringBuffer();
					for(Text text:fragments){
						stringBuffer.append(text);
					}
					content = stringBuffer.toString();
				}
			}
			search.setTitle(title);
			search.setContent(content);
			search.setRemark((String)sourceAsMap.get("remark"));
			search.setCreateTime((String)sourceAsMap.get("createTime"));

			list.add(search);
		}
		return list;
	}



}
