package response;

import lombok.SneakyThrows;
import sun.misc.BASE64Encoder;

/**
 * @author: LI
 * @Date: 2020/5/28 16:41
 * @Description: 定义加密方法
 */
public class Encrypt {
    @SneakyThrows
    public static String EncryptToPassword(String password) {
        return new BASE64Encoder().encode(password.getBytes("utf-8"));
    }
//
//    public static Object MyRollbackFor() {
//        return ResultText.error(ResultEnum.REGISTER_FAIL.getCode(), ResultEnum.REGISTER_FAIL.getMsg());
//    }
}
