package entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author: LI
 * @Date: 2020/7/7 20:58
 * @Description:
 */
public class Spit implements Serializable {
    private static final long serialVersionUID = 3673886668775137797L;
    @Id
    private String _id;
    private String name;
    private int age;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
