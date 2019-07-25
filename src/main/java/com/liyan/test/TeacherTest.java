package com.liyan.test;

import com.liyan.mapper.TeacherMapper;
import com.liyan.pojo.Teacher;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


import java.io.InputStream;
import java.util.List;

public class TeacherTest {

    @Test
    public void find() throws Exception {
        InputStream config = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        SqlSession sqlSession = factory.openSession();

        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        List<Teacher> list=teacherMapper.findTeacherStudent();
        for (Teacher teacher:list){
            System.out.println(teacher.getList());
        }

    }
}
