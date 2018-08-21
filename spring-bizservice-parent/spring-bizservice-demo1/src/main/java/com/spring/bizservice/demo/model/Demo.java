package com.spring.bizservice.demo.model;

import javax.persistence.*;

public class Demo {
    @Id
    @Column(name = "demoId")
    private Integer demoid;

    @Column(name = "demoName")
    private String demoname;

    @Column(name = "demoAge")
    private Integer demoage;

    /**
     * @return demoId
     */
    public Integer getDemoid() {
        return demoid;
    }

    /**
     * @param demoid
     */
    public void setDemoid(Integer demoid) {
        this.demoid = demoid;
    }

    /**
     * @return demoName
     */
    public String getDemoname() {
        return demoname;
    }

    /**
     * @param demoname
     */
    public void setDemoname(String demoname) {
        this.demoname = demoname;
    }

    /**
     * @return demoAge
     */
    public Integer getDemoage() {
        return demoage;
    }

    /**
     * @param demoage
     */
    public void setDemoage(Integer demoage) {
        this.demoage = demoage;
    }
}