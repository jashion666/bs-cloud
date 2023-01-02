package org.bs.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.bs.api.system.repository.entity.MUserEntity;
import org.bs.system.mapper.MUserMapper;
import org.bs.system.service.IMUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wkh
 * @since 2023-01-02
 */
@Service
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUserEntity> implements IMUserService {

    @Override
    public MUserEntity getByUsername(String username) {
        QueryWrapper<MUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("deleted_at", 0).last("limit 1");
        return getOne(queryWrapper);
    }
}
