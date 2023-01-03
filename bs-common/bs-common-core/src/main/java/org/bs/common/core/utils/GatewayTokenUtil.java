package org.bs.common.core.utils;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.bs.common.core.constant.TokenConstant;

/**
 * 网关token工具类
 *
 * @author :wkh
 */
public class GatewayTokenUtil {

    /**
     * 生成token
     *
     * @return String
     */
    public static String createToken() {
        // token
        String token = UUID.fastUUID().toString();
        // 过期时间
        long expired = System.currentTimeMillis() + TokenConstant.GATE_WAY_TOKEN_MINUTES;
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("token", token);
        jsonObject.set("expired", expired);
        return jsonObject.toString();
    }

    /**
     * 验证token
     *
     * @param token 凭证
     * @return boolean
     */
    public static boolean verify(String token) {

        if (StringUtil.isEmpty(token)) {
            return false;
        }
        try {
            JSONObject jsonObject = JSONUtil.parseObj(token);
            if (ObjectUtil.isEmpty(jsonObject.get("token"))) {
                return false;
            }
            Long expired = (Long) jsonObject.get("expired");
            if (System.currentTimeMillis() > expired) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
