package response;

/**
 * @author: LI
 * @Date: 2020/5/27 16:09
 * @Description:
 */
public class HandlerException extends RuntimeException {
    private static final long serialVersionUID = 2318887093838578294L;
    private Integer code;


    public HandlerException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
