package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountID},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where id=#{creator}")
    User findById(@Param("creator") Integer creator);

    @Select("select * from user where account_id=#{accountId}")
    User findByAccountId(@Param("accountId")  String accountId);

    @Update("update user SET name=#{name},token=#{token},gmt_modified=#{gmtModified},gmt_create=#{gmtCreate}")
    void update(User dBUser);
}
