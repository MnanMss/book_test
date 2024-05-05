package cn.mila.book_test_master.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户 DTO
 *
 * @author mila
 * @date 2024/5/5 下午11:17
 */
@Data
@Builder
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;
}
