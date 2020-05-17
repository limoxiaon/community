package com.example.demo.mapper;

import com.example.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void insert(Question question);

    @Select("select * from question limit #{page},#{size}")
    List<Question> list(@Param("page") Integer page, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer total();

    @Select("select * from question where creator = #{id} limit #{page},#{size}")
    List<Question> listByUser(@Param("id") Integer id, @Param("page") Integer page, @Param("size") Integer size);

    @Select("select count(*) from question where creator = #{id} ")
    Integer totalUser(@Param("id") Integer id);

    @Select("select * from question where id=#{id}")
    Question getById(@Param("id") Integer id);
}
