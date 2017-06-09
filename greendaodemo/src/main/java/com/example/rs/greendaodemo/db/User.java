package com.example.rs.greendaodemo.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by rongsheng1 on 2017/3/16.
 */
@Entity
public class User  {
    @Id(autoincrement = true)
    @Property(nameInDb = "service_id")
    private Long serviceId;
    @Property(nameInDb = "service_name")
    public String serviceName; //服务名称
    public String getServiceName() {
        return this.serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public Long getServiceId() {
        return this.serviceId;
    }
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
    @Generated(hash = 1832648611)
    public User(Long serviceId, String serviceName) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
    }
    @Generated(hash = 586692638)
    public User() {
    }
}
