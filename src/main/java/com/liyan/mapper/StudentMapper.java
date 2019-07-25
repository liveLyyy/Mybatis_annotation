package com.liyan.mapper;

import com.liyan.pojo.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {

    @Select("select * from student where tid=#{0}")
    List<Student> findById(int tid);
}
