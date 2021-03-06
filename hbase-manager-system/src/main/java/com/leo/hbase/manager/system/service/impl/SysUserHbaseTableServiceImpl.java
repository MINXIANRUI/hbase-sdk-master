package com.leo.hbase.manager.system.service.impl;

import java.util.List;

import com.github.CCweixiao.exception.HBaseOperationsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leo.hbase.manager.system.mapper.SysUserHbaseTableMapper;
import com.leo.hbase.manager.system.domain.SysUserHbaseTable;
import com.leo.hbase.manager.system.service.ISysUserHbaseTableService;
import com.leo.hbase.manager.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户和HBase的关联Service业务层处理
 *
 * @author leojie
 * @date 2021-02-06
 */
@Service
public class SysUserHbaseTableServiceImpl implements ISysUserHbaseTableService {
    @Autowired
    private SysUserHbaseTableMapper sysUserHbaseTableMapper;

    /**
     * 查询用户和HBase的关联
     *
     * @param userId 用户和HBase的关联ID
     * @return 用户和HBase的关联
     */
    @Override
    public SysUserHbaseTable selectSysUserHbaseTableById(Long userId) {
        return sysUserHbaseTableMapper.selectSysUserHbaseTableById(userId);
    }

    /**
     * 查询用户和HBase的关联列表
     *
     * @param sysUserHbaseTable 用户和HBase的关联
     * @return 用户和HBase的关联
     */
    @Override
    public List<SysUserHbaseTable> selectSysUserHbaseTableList(SysUserHbaseTable sysUserHbaseTable) {
        return sysUserHbaseTableMapper.selectSysUserHbaseTableList(sysUserHbaseTable);
    }

    @Override
    public List<SysUserHbaseTable> selectSysUserHbaseTableListByUserAndClusterAlias(Long userId, String clusterAlias) {
        return sysUserHbaseTableMapper.selectSysUserHbaseTableListByUserAndClusterAlias(userId, clusterAlias);
    }

    /**
     * 新增用户和HBase的关联
     *
     * @param sysUserHbaseTable 用户和HBase的关联
     * @return 结果
     */
    @Override
    public int insertSysUserHbaseTable(SysUserHbaseTable sysUserHbaseTable) {
        return sysUserHbaseTableMapper.insertSysUserHbaseTable(sysUserHbaseTable);
    }

    /**
     * 修改用户和HBase的关联
     *
     * @param sysUserHbaseTable 用户和HBase的关联
     * @return 结果
     */
    @Override
    public int updateSysUserHbaseTable(SysUserHbaseTable sysUserHbaseTable) {
        return sysUserHbaseTableMapper.updateSysUserHbaseTable(sysUserHbaseTable);
    }

    /**
     * 删除用户和HBase的关联对象
     *
     * @param userIds 用户ID
     * @return 结果
     */
    @Override
    public int deleteSysUserHbaseTableByUserIds(String userIds) {
        return sysUserHbaseTableMapper.deleteSysUserHbaseTableByUserIds(Convert.toStrArray(userIds));
    }

    /**
     * 删除用户和HBase的关联信息
     *
     * @param userId 用户和HBase的关联ID
     * @return 结果
     */
    @Override
    public int deleteSysUserHbaseTableById(Long userId) {
        return sysUserHbaseTableMapper.deleteSysUserHbaseTableById(userId);
    }

    @Override
    public int deleteSysUserHbaseTable(SysUserHbaseTable sysUserHbaseTable) {
        return sysUserHbaseTableMapper.deleteSysUserHbaseTable(sysUserHbaseTable);
    }

    @Override
    public int deleteSysUserHbaseTableByTableId(SysUserHbaseTable sysUserHbaseTable) {
        return sysUserHbaseTableMapper.deleteSysUserHbaseTableByTableId(sysUserHbaseTable);
    }

    @Override
    @Transactional(rollbackFor = {HBaseOperationsException.class})
    public int authUserTable(Long userId, String clusterAlias, List<SysUserHbaseTable> sysUserHbaseTableList) {
        SysUserHbaseTable sysUserHbaseTable = new SysUserHbaseTable();
        sysUserHbaseTable.setUserId(userId);
        sysUserHbaseTable.setClusterAlias(clusterAlias);

        if (sysUserHbaseTableList == null || sysUserHbaseTableList.isEmpty()) {
            sysUserHbaseTableMapper.deleteSysUserHbaseTable(sysUserHbaseTable);
            return 1;
        }
        sysUserHbaseTableMapper.deleteSysUserHbaseTable(sysUserHbaseTable);
        return sysUserHbaseTableMapper.batchUserTable(sysUserHbaseTableList);
    }
}
