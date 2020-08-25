package com.wudagezhandui.shixun.xianyu.service;

import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.IdleQuery;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserQuery;
import com.wudagezhandui.shixun.xianyu.result.Result;
import org.springframework.web.multipart.MultipartFile;


/***
 * 闲置物品service
 */

public interface IdleService {

    Result<IdleDO> saveIdle(IdleDO idle);

    Result<IdleDO> getIdle(Integer id);

    Result<IdleDO> getIdleByName(String name);

    Result<PageInfo<IdleDO>> listIdles(IdleQuery query);

    Result<IdleDO> updateIdle(IdleDO idle);

    //Result<IdleDO> updateAvatar(Integer id, MultipartFile avatar);
}
