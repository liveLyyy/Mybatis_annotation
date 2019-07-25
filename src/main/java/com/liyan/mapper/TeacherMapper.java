package com.liyan.mapper;

import com.liyan.pojo.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TeacherMapper {
    @Select("select * from teacher")
    List<Teacher> findAll();

    @Insert("insert into teacher values(default,#{name})")
    int insertTeacher(Teacher teacher);

    @Update("update teacher set name=#{name} where id=#{id} ")
    int updateTeacher(Teacher teacher);

    @Delete("delete from teacher where id=#{id}")
    int delectTeacher(int id);

    @Results(value = {@Result(id = true, column = "id", property = "id"),
                        @Result(column = "name", property = "name"),
                        @Result(column = "id", property = "list",many = @Many(select = "com.liyan.mapper.StudentMapper.findById"))})
    @Select("select * from teacher")
    List<Teacher> findTeacherStudent();
}
