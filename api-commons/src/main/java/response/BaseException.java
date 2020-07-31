package response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: LI
 * @Date: 2020/5/27 15:38
 * @Description:
 */
@ControllerAdvice
@Slf4j
@Component
public class BaseException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultText handler(Exception e) {
        if (e instanceof HandlerException) {
            HandlerException handlerException = (HandlerException) e;
            return ResultText.error(handlerException.getCode(), handlerException.getMessage());
        } else {
            log.info("系统异常{}", e);
            return ResultText.error("未知错误");
        }
    }
}
