package org.bs.common.core.utils;

import cn.hutool.core.codec.Base64;
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
        String expiredStr = String.valueOf(expired);
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("expired", expiredStr);
        jsonObject.set("token", token);
        return Base64.encode(jsonObject.toString());
    }

    /**
     * 验证token
     *
     * @param base64Token 凭证
     * @return boolean
     */
    public static boolean verify(String base64Token) {

        if (StringUtil.isEmpty(base64Token)) {
            return false;
        }
        try {
            String token = Base64.decodeStr(base64Token);
            JSONObject jsonObject = JSONUtil.parseObj(token);
            if (ObjectUtil.isEmpty(jsonObject.get("token"))) {
                return false;
            }
            String expired = (String) jsonObject.get("expired");
            if (System.currentTimeMillis() > Long.parseLong(expired)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
