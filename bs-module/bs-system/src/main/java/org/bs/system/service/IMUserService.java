package org.bs.system.service;

import org.bs.api.system.repository.entity.MUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wkh
 * @since 2023-01-02
 */
public interface IMUserService extends IService<MUserEntity> {

    /**
     * get user information by username
     *
     * @param username username
     * @return MUserEntity
     */
    MUserEntity getByUsername(String username);

}
