package com.iistd.entity;



import java.io.Serializable;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@Document(indexName = "test",type = "active")
public class Search  implements Serializable{
	@Id
	public String id;

	@Field(index = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    public String title; //标题

    @Field(index = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    public String content; //内容

    public String remark; //备注


    public String createTime;




}
