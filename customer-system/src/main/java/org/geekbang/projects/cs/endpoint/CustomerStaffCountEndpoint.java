package org.geekbang.projects.cs.endpoint;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.geekbang.projects.cs.metrics.CustomCounter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyz
 * @version 1.0.0
 * @ClassName CustomerStaffCountEndpoint.java
 * @Description TODO
 * @createTime 2023年01月10日 09:27:00
 */
@Configuration
@Endpoint(id = "customerStaffCount",enableByDefault = true)
public class CustomerStaffCountEndpoint implements InitializingBean {


    private CustomCounter customCounter;

    @Override
    public void afterPropertiesSet() throws Exception {
        SimpleMeterRegistry registry = new SimpleMeterRegistry();
        customCounter = new CustomCounter("customerStaffCount", "phone", registry);
    }

    @WriteOperation
    public void addCustomerCount(@Selector String arg0){
        customCounter.increment(arg0);
    }

    @ReadOperation
    public Map<String, Object> getCustomerCount(@Selector String arg0){
        double count = customCounter.getCount(arg0);
        Map<String,Object> map=new HashMap<>();
        map.put("count",count);
        return map;
    }

}
