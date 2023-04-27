package org.geekbang.projects.cs.servicebus.filter.impl;

import org.geekbang.projects.cs.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.servicebus.filter.AbstractCustomerStaffFilter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


//过滤包含特殊字符的过滤器
public class CustomerStaffSpecialTestFilter extends AbstractCustomerStaffFilter {

    private static final List<String> args= Arrays.asList("哈哈","aa");

    public CustomerStaff execute(CustomerStaff customerStaff) {
        for (String arg : args) {
            if (customerStaff.getStaffName().contains(arg)) {
                return null;
            }
        }


        if (getNext() != null) {
            return getNext().execute(customerStaff);
        }

        return customerStaff;
    }
}
