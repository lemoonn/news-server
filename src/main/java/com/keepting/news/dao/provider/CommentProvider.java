package com.keepting.news.dao.provider;

import com.keepting.news.model.Comment;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by keepspy on 2018/4/14.
 */
public class CommentProvider {
    String table="comment";

    public String getById(int id){
        SQL sql=new SQL();
        sql.SELECT("*").FROM(table)
        .WHERE("id = #{id}");
        return sql.toString();
    }

    public String getByParam(String param, Object value){
        SQL sql=new SQL();
        sql.SELECT("*").FROM(table)
                .WHERE("#{param} = #{value}");

        return sql.toString();
    }

    public String getListByMap(Map<String,Object> map){
        SQL sql=new SQL();
        sql.SELECT("*").FROM(table);
        map.forEach((key,value)->{
            sql.WHERE("#{key} = #{value}");
        });

        return sql.toString();
    }

    public String insert(Comment comment){
        SQL sql=new SQL();
        sql.INSERT_INTO(table)
                .VALUES("content","#{content}")
                .VALUES("user_id","#{user_id}")
                .VALUES("article_id","#{article_id}")
                .VALUES("is_report","0");
        return  sql.toString();
    }

    public String update(Comment comment){
        return new SQL().UPDATE(table)
                .SET("likes=#{likes}")
                .SET("is_report=#{is_report}")
                .SET("report=#{report}")
                .WHERE("id=#{id}").toString();
    }

}