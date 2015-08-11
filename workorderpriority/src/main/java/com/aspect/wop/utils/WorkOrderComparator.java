package com.aspect.wop.utils;

import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.aspect.wop.pojo.WorkOrder;

public class WorkOrderComparator implements Comparator<WorkOrder> {

	//@Override
	public int compare(WorkOrder wo1, WorkOrder wo2) {
        return new CompareToBuilder()
        .append(wo1.getWoclassInt(), wo2.getWoclassInt())
        .append(wo2.getRank(), wo1.getRank()).toComparison();
	}

}
