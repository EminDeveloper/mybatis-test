package net.albali.mybatistest.mapper;

import net.albali.mybatistest.entity.RoleEntity;
import net.albali.mybatistest.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Mapper
public interface MyBatisMapper {

    @Select("select *\n" +
            "from OPERATION_GUI_USER_TABLE\n" +
            "where USERNAME = #{username}")
    @Results(value = {
            @Result(property = "username", column = "username"),
            @Result(property = "status", column = "status"),
            @Result(property = "password", column = "password"),
            @Result(property = "created", javaType = LocalDateTime.class, column = "created"),
            @Result(property = "updated", javaType = LocalDateTime.class, column = "updated"),
            @Result(property = "role", javaType = List.class, column = "username",
                    many = @Many(select = "findUserRolesByUserName"))
    })
    UserEntity findUserEntityByUsername(@Param("username") String username);

    @Select("select ROLE.ID, ROLE.CREATED, ROLE.UPDATED, ROLE.NAME \n" +
            "from WP.OPERATION_GUI_USER_TABLE USERS\n" +
            "         join OPERATION_GUI_USER_ROLE_ASSIGNMENT RGUR on USERS.ID = RGUR.USER_ID\n" +
            "         join OPERATION_GUI_USER_ROLE ROLE on RGUR.ROLE_ID = ROLE.ID " +
            "where USERNAME= #{username}")
    List<RoleEntity> findUserRolesByUserName(String username);


    /*
    declare
    NEW_USER OPERATION_GUI_USER_TABLE%rowtype;
begin
    NEW_USER := INSERT_NEW_USER('TEST_F_2', 'test_pass2', 'ACTIVE', 'ROLE_USER');
    DBMS_OUTPUT.PUT_LINE(NEW_USER.USERNAME);
end;

INSERT_NEW_USER(" +
            "#{username, mode=IN, jdbcType=VARCHAR}, " +
            "#{password, mode=IN, jdbcType=VARCHAR}," +
            "#{status, mode=IN, jdbcType=VARCHAR}," +
            "#{roleName, mode=IN, jdbcType=VARCHAR}" +
            ")
     */
    @Insert("begin\n" +
            "    WP.INSERT_NEW_USER(\" +\n" +
            "            \"#{username, mode=IN, jdbcType=VARCHAR}, \" +\n" +
            "            \"#{password, mode=IN, jdbcType=VARCHAR},\" +\n" +
            "            \"#{status, mode=IN, jdbcType=VARCHAR},\" +\n" +
            "            \"#{roleName, mode=IN, jdbcType=VARCHAR}\" +\n" +
            "            \")\n" +
            "end;")
    @Options(statementType = StatementType.CALLABLE)
    void insertUser(UserEntity entity);

    @Select("select * from OPERATION_GUI_USER_ROLE where NAME=#{name}")
    RoleEntity findRoleEntityByName(String name);

    @Update("call CHANGE_USER_ROLE(#{username, mode=IN, jdbcType=VARCHAR}, #{roleName, mode=IN, jdbcType=VARCHAR});\n")
    void changeUserRole(String username, String roleName);

    @Select("select *\n" +
            "from OPERATION_GUI_USER_TABLE")
    @Results(value = {
            @Result(property = "username", column = "username"),
            @Result(property = "status", column = "status"),
            @Result(property = "created", javaType = LocalDateTime.class, column = "created"),
            @Result(property = "updated", javaType = LocalDateTime.class, column = "updated"),
            @Result(property = "role", javaType = List.class, column = "username",
                    many = @Many(select = "findUserRolesByUserName"))
    })
    List<UserEntity> findAllUsers();
}
