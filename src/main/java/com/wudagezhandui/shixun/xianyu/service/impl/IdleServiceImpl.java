package com.wudagezhandui.shixun.xianyu.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.dao.IdleMapper;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.IdleQuery;
import com.wudagezhandui.shixun.xianyu.result.ErrorCode;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.FileService;
import com.wudagezhandui.shixun.xianyu.service.IdleService;
import com.wudagezhandui.shixun.xianyu.service.constant.IdleConstant;
import com.wudagezhandui.shixun.xianyu.util.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


/***
 * 用户服务层
 */
@Service("idleService")
public class IdleServiceImpl implements IdleService {

    private static final Logger logger = LoggerFactory.getLogger(IdleServiceImpl.class);

    private final IdleMapper idleMapper;

    private final FileService fileService;

    @Autowired
    public IdleServiceImpl(IdleMapper idleMapper, FileService fileService) {
        this.idleMapper = idleMapper;
        this.fileService = fileService;
    }

    @Override
    public Result<IdleDO> getIdleByName(String name) {
        IdleDO idleDO = idleMapper.getIdleByName(name);
        if(idleDO==null){
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND,"The specified idle for idlename="
                    + name + " does not exist.");
        }
        return Result.success(idleDO);
    }

    /***
     * 保存idle
     * @param idle idle
     * @return Result<IdleDO>
     */
    @Override
    @Transactional
    public Result<IdleDO> saveIdle(IdleDO idle, MultipartFile[] images) {
        /*//物品在表中
        IdleDO temp = idleMapper.getIdle(idle.getId());
        if(temp!=null){
            return Result.fail(ErrorCode.OPERATION_CONFLICT,
                    "Request was denied due to conflict, the idleId already exists.");
        }*/
        //System.out.println(idle.getUser_id());
        idle.setStatus(IdleDO.Status.NORMAL);


        //存储image文件，获取url
        int length = images.length;
        StringBuffer buffer = new StringBuffer();
        //String[] urls = new String[length];
        for(int i = 0;i<length;i++){
            buffer.append(fileService.save(images[i], IdleConstant.PREFIX_IMAGE_FILE_DIRECTORY));
            buffer.append(',');
        }
        buffer.deleteCharAt(buffer.length()-1);
        idle.setImage(buffer.toString());


        int count = idleMapper.saveIdle(idle);
        //保存失败
        if(count<1){
            logger.error("Insert idle fail.");
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Insert idle fail.");
        }

        return getIdle(idle.getId());
    }

    /***
     * 通过id查询闲置物品
     * @param id 物品id
     * @return Result<IdleDO>
     */
    @Override
    public Result<IdleDO> getIdle(Integer id) {
        IdleDO idleDO = idleMapper.getIdle(id);
        if (idleDO == null) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND,
                    "The specified id does not exist.");
        }
        return Result.success(idleDO);
    }

    /***
     * 获取UserDOList通过查询参数query
     *
     * @param query 查询参数
     * @return idleDOList
     */
    @Override
    public Result<PageInfo<IdleDO>> listIdles(IdleQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        Page<IdleDO> idleDOList = (Page<IdleDO>) idleMapper.listIdles(query);
        PageInfo<IdleDO> pageInfo = new PageInfo<>(idleDOList);
        if (idleDOList.size() < 1) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "Not found.");
        }

        return Result.success(pageInfo);
    }

    /***
     * 更新物品信息
     * @param idle 需要更新的信息
     * @return 更新之后的信息
     */
    @Override
    public Result<IdleDO> updateIdle(IdleDO idle, MultipartFile[] images) {
        //更新可以更新的属性
        IdleDO idleDO = new IdleDO();
        idleDO.setTitle(idle.getTitle());
        idleDO.setDetail(idle.getDetail());
        idleDO.setPrice(idle.getPrice());
        idleDO.setImage(idle.getImage());
        idleDO.setStatus(idle.getStatus());

        //存储image文件，获取url
        int length = images.length;
        StringBuffer buffer = new StringBuffer();
        //String[] urls = new String[length];
        for(int i = 0;i<length;i++){
            buffer.append(fileService.save(images[i], IdleConstant.PREFIX_IMAGE_FILE_DIRECTORY));
            buffer.append(',');
        }
        buffer.deleteCharAt(buffer.length()-1);
        idle.setImage(buffer.toString());

        idleDO.setImage(buffer.toString());
        //所有参数为空
        if(BeanUtils.allFieldIsNull(idleDO)){
            return Result.fail(ErrorCode.INVALID_PARAMETER_IS_BLANK,
                    "The required parameter must be not all null.");
        }

        idleDO.setId(idle.getId());
        idleDO.setUserId(idle.getUser_id());

        int count = idleMapper.updateIdle(idleDO);
        if(count<1){
            logger.error("Update idle failed. idleId: {}", idleDO.getId());
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Update idle failed.");
        }

        return getIdle(idleDO.getId());
    }
}
