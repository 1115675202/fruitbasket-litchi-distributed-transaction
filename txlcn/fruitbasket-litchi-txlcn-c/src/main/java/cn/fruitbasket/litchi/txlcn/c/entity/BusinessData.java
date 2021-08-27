package cn.fruitbasket.litchi.txlcn.c.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author LiuBing
 * @since 2021-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BusinessData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String information;

    /**
     * 用来解决消息幂等性
     */
    private String idempotenceKey;


    public static final String ID = "id";

    public static final String INFORMATION = "information";

    public static final String IDEMPOTENCE_KEY = "idempotence_key";

}
