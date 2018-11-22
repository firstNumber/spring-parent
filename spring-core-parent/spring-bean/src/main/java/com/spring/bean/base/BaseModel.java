package com.spring.bean.base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author liyongzhen
 * @create 2018-11-22 10:42
 **/
@MappedSuperclass
public abstract class BaseModel implements IBaseModel{

	/**
     * 主键
     */
    @Id
    @Column(name = "id")
    public Long id;

    /**
     * 是否删除：0：有效，1：删除
     */
	private Byte enabled;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
	private Date createTime;

    /**
     * 创建者
     */
    @Column(name = "create_by")
	private Long createBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
	private Date updateTime;

    /**
     * 更新者
     */
    @Column(name = "update_by")
	private Long updateBy;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Byte getEnabled() {
		return enabled;
	}

	public void setEnabled(Byte enabled) {
		this.enabled = enabled;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
}
