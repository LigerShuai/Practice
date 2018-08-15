package com.liger.practice.rx;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liger
 * @date 2018/8/15 23:09
 */
public class Person {

    private String name;
    private List<Plan> planList = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

}
