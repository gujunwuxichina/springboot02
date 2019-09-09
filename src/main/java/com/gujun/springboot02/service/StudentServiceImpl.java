package com.gujun.springboot02.service;

import com.gujun.springboot02.dao.StudentMapper;
import com.gujun.springboot02.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /*
        sprint-redis缓存详见ssm02;
        
     */
    @Override
    @Cacheable(value = "cacheNoLimit",key = "'students'")
    public List<Student> getAll() {
        return studentMapper.getAll();
    }

    @Override
    public Student getById(Integer sId) {
        return studentMapper.getById(sId);
    }

    @Override
    public int saveOne(Student student) {
        return studentMapper.saveOne(student);
    }

    @Override
    public int update(Student student) {
        return studentMapper.update(student);
    }

    @Override
    public int deleteById(Integer sId) {
        return studentMapper.deleteById(sId);
    }

    /*
        Spring事务（声明式事务）：@Transactional可以放在接口或实现类的方法上，建议放实现类；
        Spring事务管理器：
        不同的ORM会有不同的事务管理器实现类；
        在SpringBoot中，依赖于mybatis-spring-boot-starter后会自动创建事务管理器实现类对象，一般不需要自己配置；
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updates(Student student, Integer sId) {
        studentMapper.deleteById(sId);
        studentMapper.update(student);  //这里测试事务故意报错，抛出运行时异常；
    }

}
