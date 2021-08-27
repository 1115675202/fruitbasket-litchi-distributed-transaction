package cn.fruitbasket.litchi.txlcn.a.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("business_data")
public class BusinessData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
