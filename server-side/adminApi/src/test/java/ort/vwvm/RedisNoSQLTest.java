package ort.vwvm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vwvm.AdminApiApplication;
import org.vwvm.store.beans.redisBean.Address;
import org.vwvm.store.beans.redisBean.Family;
import org.vwvm.store.beans.redisBean.Person;
import org.vwvm.store.mappers.redisMapper.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = AdminApiApplication.class)
public class RedisNoSQLTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void savePerson() {
        Person person = new Person("张", "有才");
        Person person2 = new Person("James", "Harden");
        // 创建并添加住址信息
        Address address = new Address("北京", "China");
        person.setAddress(address);
        // 创建并添加家庭成员
        List<Family> list = new ArrayList<>();
        Family dad = new Family("父亲", "张良");
        Family mom = new Family("母亲", "李香君");
        list.add(dad);
        list.add(mom);
        person.setFamilyList(list);
        // 向Redis数据库添加数据
        Person save = personRepository.save(person);
        Person save2 = personRepository.save(person2);
        System.out.println(save);
        System.out.println(save2);
    }

    @Test
    public void selectPerson() {
        List<Person> list = personRepository.findByAddress_City("北京");
        System.out.println(list);
    }

    @Test
    public void updatePerson() {
        Person person = personRepository.findByFirstnameAndLastname("张", "有才").get(0);
                person.setLastname("小明");
        Person update = personRepository.save(person);
        System.out.println(update);
    }

    @Test
    public void deletePerson() {
        Person person = personRepository.findByFirstnameAndLastname("张", "小明").get(0);
                personRepository.delete(person);
    }
}
