Mybatis注解
===========
1、注解：为了简化配置文件<br>
2、Mybatis的注解简化mapper.xml文件<br>
>>2.1、如果涉及动态sql依然使用mapper.xml<br>

3、mapper.xml和注解可以共存<br>
4、使用注解时mybatis.xml中<mappers>的使用<br>
>>4.1、<package/><br>
>>4.2、<mapper class=""/><br>

5、代码实现<br>
```java
public interface TeacherMapper {
    @Select("select * from teacher")
    List<Teacher> findAll();

    @Insert("insert into teacher values(default,#{name})")
    int insertTeacher(Teacher teacher);

    @Update("update teacher set name=#{name} where id=#{id} ")
    int updateTeacher(Teacher teacher);

    @Delete("delete from teacher where id=#{id}")
    int delectTeacher(int id);

    //使用注解实现resultMap功能
    //先查询全部，然后在StudentMapper中根据tid查询
    //@Results:相当于<resultMap>；@Result相当于<id>或<result>；@Result(id = true}相当于<id>；@Many：相当于<collection>;@One：相当于<assicoation>
    @Results(value = {@Result(id = true, column = "id", property = "id"),
                        @Result(column = "name", property = "name"),
                        @Result(column = "id", property = "list",many = @Many(select = "com.liyan.mapper.StudentMapper.findById"))})
    @Select("select * from teacher")
    List<Teacher> findTeacherStudent();
}
```

Mybatis运行原理
==============
1、运行中涉及的类<br>
>>1.1、Resources:Mybatis中IO流的工具,加载配置文件<br>
>>1.2、SqlSessionFactoryBuilder()：构建器，创建SqlSessionFactory接口的实现类<br>
>>1.3、XMLConfigBuilder:Mybatis全局配置文件内容构建器类，负责读取流内容并转换成java文件<br>
>>1.4、Configuration:封装了全局配置文件所有的配置信息,全局配置文件内容存放在Configuration中<br>
>>1.5、DefaultSqlSessionFactory:是SqlSessionFactory接口的实现类<br>
>>1.6、Transaction事务类：每一个SqlSession会带有一个Transaction对象<br>
>>1.7、TransactionFactory事务工厂:负责生产Transaction<br>
>>1.8、Executor:Mybatis执行器,负责执行SQL命令，相当JDBC中statement对象（PreparedStatement或CallableStatement）,默认执行器SimpleExcotor,批量操作BatchExcutor，通过openSession（参数控制）<br>
>>1.9、DefaultSqlSession：是SqlSession的实现类<br>
>>1.10、ExceptionFactorty：Mybatis中的异常工厂<br>

