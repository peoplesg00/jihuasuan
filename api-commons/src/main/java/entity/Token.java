package entity;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: LI
 * @Date: 2020/6/17 14:12
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token<T> implements Serializable {
    private static final long serialVersionUID = 2771432235341631752L;
    private String id;
    private User user;
    private int offset;
    private int limit;
    private Account account;
    private List<T> list;
    private PageInfo<T> pageInfo;

    public Token(String id, User user) {
        this.id = id;
        this.user = user;
    }

    public Token(String id, List<T> list) {
        this.id = id;
        this.list = list;
    }

    public Token(String id, Account account) {
        this.id = id;
        this.account = account;
    }

    public Token(String id, PageInfo<T> pageInfo) {
        this.id = id;
        this.pageInfo = pageInfo;
    }
}
