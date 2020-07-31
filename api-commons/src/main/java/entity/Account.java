package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Account)实体类
 *
 * @author makejava
 * @since 2020-06-21 15:34:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    private static final long serialVersionUID = 4313712613490014223L;
    /**
     * id
     */
    private String id;
    /**
     * 日期
     */
    private Object create_time;
    /**
     * 摘要
     */
    private String briefly;
    /**
     * 类型
     */
    private String type;
    /**
     * 收入
     */
    private Double income;
    /**
     * 支出
     */
    private Double expenditure;
    /**
     * 持有者
     */
    private String founder;

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", create_time=" + create_time +
                ", briefly='" + briefly + '\'' +
                ", type='" + type + '\'' +
                ", income=" + income +
                ", expenditure=" + expenditure +
                ", founder='" + founder + '\'' +
                "}\n";
    }
}