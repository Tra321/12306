package com.atra.userservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author Cline
 */
@RestControllerAdvice // 标记这是一个全局异常处理组件，它会捕获所有 @RestController 抛出的异常
public class GlobalExceptionHandler {

    /**
     * 处理 IllegalStateException 异常
     * <p>
     * 当我们的服务层（如 UserServiceImpl）抛出 IllegalStateException 时
     * （例如，当用户名已存在时），这个方法会被调用。
     *
     * @param ex 捕获到的 IllegalStateException 异常
     * @return 一个带有 409 Conflict 状态码和错误信息的 ResponseEntity
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
        // 返回一个 HTTP 409 (Conflict) 响应，响应体中包含异常信息
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * 处理 IllegalArgumentException 异常
     * <p>
     * 当登录时用户名或密码错误，会抛出此异常。
     *
     * @param ex 捕获到的 IllegalArgumentException 异常
     * @return 一个带有 401 Unauthorized 状态码和错误信息的 ResponseEntity
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // 返回一个 HTTP 401 (Unauthorized) 响应
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * 处理 UsernameNotFoundException 异常
     *
     * @param ex 捕获到的 UsernameNotFoundException 异常
     * @return 一个带有 404 Not Found 状态码和错误信息的 ResponseEntity
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
