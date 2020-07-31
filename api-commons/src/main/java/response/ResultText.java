package response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: LI
 * @Date: 2020/5/27 15:18
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultText<T> {

    private Integer code;
    private String msg;
    private T data;

    public ResultText(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultText(String message) {
        this.msg = message;
    }

    public ResultText(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public ResultText(ResultEnum resultEnum, String msg) {
        this.code = resultEnum.getCode();
        this.msg = msg;
    }

    public ResultText(ResultEnum resultEnum, Object data) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.data = (T) data;
    }

    public static ResultText success(Integer code, String msg, Object data) {
        return new ResultText(code, msg, data);
    }

    public static ResultText success(ResultEnum resultEnum, Object data) {
        return new ResultText(resultEnum, data);
    }

    public static ResultText success(ResultEnum resultEnum) {
        return new ResultText(resultEnum);
    }

    public static ResultText error(Integer code, String msg) {
        return new ResultText(code, msg);
    }

    public static ResultText error(Integer code, String message, Object data) {
        return new ResultText(code, message, data);
    }

    public static ResultText error(String message) {
        return new ResultText(message);
    }

    public static ResultText error(ResultEnum resultEnum) {
        return new ResultText(resultEnum);
    }

    public static ResultText error(ResultEnum resultEnum, String msg) {
        return new ResultText(resultEnum, msg);
    }

}
