package usedao.dao;

import com.tyy.common.Student;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class StudentDao extends AbstractDao<Student> {

    public StudentDao(){
        super(Student.class);
    }

//    public StudentDao(Class<Student> entityClass) {
//        super(entityClass);
//    }
    // 已经有了:
    // User getById(long)
    // List<User> getAll(int)
    // void deleteById(long)

}
