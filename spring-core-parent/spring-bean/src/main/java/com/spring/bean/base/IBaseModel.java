package com.spring.bean.base;

import java.io.Serializable;
import java.util.Date;

public interface IBaseModel extends Serializable {

    /**
     * 所有的表统一规范主键，使用id
     * @return
     */
	Long getId();
    void setId(Long id);

    /**
     * 数据只做逻辑删除，不做物理删除。（赋值取值请使用DBConsts常量类）
     * 1：正常态
     * 0：删除态
     * @return
     */
    Byte getEnabled();
	void setEnabled(Byte enabled);

    /**
     * 用于记录创建者
     * @return
     */
	Long getCreateBy();
	void setCreateBy(Long createBy);

    /**
     * 时间戳，用于记录创建时间
     * @return
     */
	Date getCreateTime();
	void setCreateTime(Date createTime);

    /**
     * 用于记录更新者
     * @return
     */
	Long getUpdateBy();
	void setUpdateBy(Long updateBy);

    /**
     * 时间戳，用于记录更新时间
     * 时间戳，用于进行校验并发
     * @return
     */
	Date getUpdateTime() ;
	void setUpdateTime(Date updateTime);

}
