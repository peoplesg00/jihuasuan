package response;

/**
 * @author: LI
 * @Date: 2020/5/27 15:13
 * @Description:
 */
public enum ResultEnum {
    /**
     * 自定义常用的成功或失败
     */
    LOGIN_SUCCESS(200, "登录成功"),
    LOGIN_FAIL(201, "密码或账户错误"),
    NOT_DATA(201, "无数据"),
    REGISTER_SUCCESS(200, "注册成功"),
    REGISTER_FAIL(201, "注册失败"),
    DATA_FAIL(201, "参数错误"),
    SELECT_SUCCESS(200, "查询成功"),
    EXISTENCE(201, "用户名已使用"),
    UPDATE_SUCCESS(200, "更新成功"),
    UPDATE_FAIL(201, "更新失败"),
    INSERT_FAIL(201, "插入失败"),
    INSERT_SUCCESS(200, "插入成功"),
    USER_NOT_EXISTS(201, "用户不存在"),
    CREATE_IMG(200, "验证码生成成功"),
    OUT(200, "退出成功"),
    REST_PASSWORD_FAIL(201, "密码修改失败"),
    REST_PASSWORD_SUCCESS(200, "密码修改成功"),
    ACCOUNT_NOT_EXISTS(201, "无此号账单"),
    DELETE_SUCCESS(200, "删除成功"),
    DELETE_FAIL(201, "删除失败");


    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
