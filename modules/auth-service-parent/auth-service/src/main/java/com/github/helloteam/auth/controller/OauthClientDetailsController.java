package com.github.helloteam.auth.controller;

import com.github.pagehelper.PageInfo;
import com.github.helloteam.auth.api.module.OauthClientDetails;
import com.github.helloteam.auth.service.OauthClientDetailsService;
import com.github.helloteam.common.core.constant.CommonConstant;
import com.github.helloteam.common.core.model.ResponseBean;
import com.github.helloteam.common.core.utils.PageUtil;
import com.github.helloteam.common.core.web.BaseController;
import com.github.helloteam.common.log.annotation.Log;
import com.github.helloteam.common.security.annotations.AdminAuthorization;
import com.github.helloteam.common.security.utils.SysUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Oauth2客户端信息管理Controller类
 *
 * @author zdz
 * @date 2022/04/14 11:48
 */
@Slf4j
@AllArgsConstructor
@Api("Oauth2客户端信息管理")
@RestController
@RequestMapping("/v1/client")
public class OauthClientDetailsController extends BaseController {

    /**
     * Oauth客户端Service实例
     */
    private final OauthClientDetailsService oauthClientDetailsService;

    /**
     * 密码编码器
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 根据ID获取客户端实体
     *
     * @param id 客户端ID
     * @return 响应的客户端Bean
     */
    @ApiOperation(value = "获取客户端信息", notes = "根据客户端id获取客户端详细信息")
    @ApiImplicitParam(name = "id", value = "客户端ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/{id}")
    public ResponseBean<OauthClientDetails> oauthClient(@PathVariable Long id) {
        OauthClientDetails oauthClientDetails = null;
        if (id != null) {
            oauthClientDetails = oauthClientDetailsService.get(id);
        }
        return new ResponseBean<>(oauthClientDetails);
    }

    /**
     * 分页查询
     *
     * @param pageNum            pageNum
     * @param pageSize           pageSize
     * @param sort               sort
     * @param order              order
     * @param oauthClientDetails oauthClientDetails
     * @return PageInfo
     */
    @GetMapping("clientList")
    @ApiOperation(value = "获取客户端列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
            @ApiImplicitParam(name = "attachment", value = "客户端信息", dataType = "OauthClient")
    })
    public PageInfo<OauthClientDetails> oauthClientList(
            @RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) String pageNum,
            @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) String pageSize,
            @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
            @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
            OauthClientDetails oauthClientDetails) {
        return oauthClientDetailsService.findPage(PageUtil.pageInfo(pageNum, pageSize, sort, order), oauthClientDetails);
    }

    /**
     * 查询客户端列表
     *
     * @param oauthClientDetails 客户端实体
     * @return 响应的客户端Bean
     */
    @GetMapping("clients")
    @ApiOperation(value = "查询客户端列表", notes = "查询客户端列表")
    @ApiImplicitParam(name = "oauthClient", value = "客户端实体oauthClient", required = true, dataType = "OauthClientDetails")
    public ResponseBean<List<OauthClientDetails>> findOauthClientList(@RequestBody OauthClientDetails oauthClientDetails) {
        return new ResponseBean<>(oauthClientDetailsService.findList(oauthClientDetails));
    }

    /**
     * 创建客户端
     *
     * @param oauthClientDetails 客户端实体
     * @return 包含请求处理结果的响应Bean
     */
    @PostMapping
    @AdminAuthorization
    @ApiOperation(value = "创建客户端", notes = "创建客户端")
    @ApiImplicitParam(name = "oauthClientDetails", value = "客户端实体oauthClientDetails", required = true, dataType = "OauthClientDetails")
    @Log("新增客户端")
    public ResponseBean<Boolean> oauthClient(@RequestBody OauthClientDetails oauthClientDetails) {
        oauthClientDetails.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
        // 加密密钥
        oauthClientDetails.setClientSecret(bCryptPasswordEncoder.encode(oauthClientDetails.getClientSecretPlainText()));
        return new ResponseBean<>(oauthClientDetailsService.insert(oauthClientDetails) > 0);
    }

    /**
     * 修改客户端
     *
     * @param oauthClientDetails 客户端实体
     * @return 包含请求处理结果的响应Bean
     */
    @PutMapping
    @AdminAuthorization
    @ApiOperation(value = "更新客户端信息", notes = "根据客户端id更新客户端的基本信息")
    @ApiImplicitParam(name = "oauthClientDetails", value = "客户端实体oauthClientDetails", required = true, dataType = "OauthClientDetails")
    @Log("修改客户端")
    public ResponseBean<Boolean> updateOauthClient(@RequestBody OauthClientDetails oauthClientDetails) {
        OauthClientDetails tempOauthClientDetails = oauthClientDetailsService.get(oauthClientDetails);
        // 有调整过明文则重新加密密钥
        if (tempOauthClientDetails != null && !tempOauthClientDetails.getClientSecretPlainText().equals(oauthClientDetails.getClientSecretPlainText())) {
            oauthClientDetails.setClientSecret(bCryptPasswordEncoder.encode(oauthClientDetails.getClientSecretPlainText()));
        }
        oauthClientDetails.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
        return new ResponseBean<>(oauthClientDetailsService.update(oauthClientDetails) > 0);
    }

    /**
     * 根据id删除客户端
     *
     * @param id 客户端ID
     * @return 包含请求处理结果的响应Bean
     */
    @DeleteMapping("/{id}")
    @AdminAuthorization
    @ApiOperation(value = "删除客户端", notes = "根据ID删除客户端")
    @ApiImplicitParam(name = "id", value = "客户端ID", required = true, paramType = "path")
    @Log("删除客户端")
    public ResponseBean<Boolean> deleteOauthClient(@PathVariable Long id) {
        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        oauthClientDetails.setId(id);
        oauthClientDetails.setNewRecord(false);
        oauthClientDetails.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
        return new ResponseBean<>(oauthClientDetailsService.delete(oauthClientDetails) > 0);
    }

    /**
     * 批量删除客户端
     *
     * @param ids 需要删除的客户端的ids
     * @return 包含请求处理结果的响应Bean
     */
    @PostMapping("deleteAll")
    @AdminAuthorization
    @ApiOperation(value = "批量删除客户端", notes = "根据客户端id批量删除客户端")
    @ApiImplicitParam(name = "oauthClientDetails", value = "客户端信息", dataType = "OauthClientDetails")
    @Log("批量删除客户端")
    public ResponseBean<Boolean> deleteAllOauthClient(@RequestBody Long[] ids) {
        boolean success = false;
        try {
            if (ArrayUtils.isNotEmpty(ids)) {
                success = oauthClientDetailsService.deleteAll(ids) > 0;
            }
        } catch (Exception e) {
            log.error("Delete client failed", e);
        }
        return new ResponseBean<>(success);
    }

}