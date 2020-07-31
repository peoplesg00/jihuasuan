package utils;

import javax.servlet.http.Cookie;
import java.util.UUID;

/**
 * @author: LI
 * @Date: 2020/6/17 14:44
 * @Description:
 */
public class CookieUtils {
    public static void check(String name) {
        Cookie cookie = new Cookie(name, UUID.randomUUID().toString());
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
    }

    public static String get(String name) {
        return null;
    }
}
