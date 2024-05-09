package com.electricity.billingsystem.service.impl.mapper;

import com.electricity.billingsystem.entity.BillEntity;
import com.electricity.billingsystem.vo.BillVo;

import java.time.LocalDate;

public class BillMapper {
    public static BillVo mapToBillVo(BillEntity billEntity) {


        return BillVo.builder()
                .id(billEntity.getId())
                .billNumber(billEntity.getBillNumber())
                .consumerVo(ConsumerMapper.mapToConsumerVo(billEntity.getConsumerEntity()))
                .consumedUnits(billEntity.getConsumedUnits())
                .dueDate(billEntity.getDueDate())
                .dueAmount(billEntity.getDueAmount())
                .paid(billEntity.getPaid())
                .build();
    }

    public static BillEntity mapToBillEntity(BillVo billVo) {
        return BillEntity.builder()
                .id(billVo.getId())
                .billNumber(billVo.getBillNumber())
                .consumerId(billVo.getConsumerId())
                .consumedUnits(billVo.getConsumedUnits())
                .dueDate(billVo.getDueDate())
                .dueAmount(billVo.getDueAmount())
                .paid(billVo.getPaid())
                .build();
    }
}
