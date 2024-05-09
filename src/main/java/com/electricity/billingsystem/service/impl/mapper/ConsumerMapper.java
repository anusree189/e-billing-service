package com.electricity.billingsystem.service.impl.mapper;

import com.electricity.billingsystem.entity.ConsumerEntity;
import com.electricity.billingsystem.vo.ConsumerVo;

public class ConsumerMapper {
    public static ConsumerVo mapToConsumerVo(ConsumerEntity consumerEntity) {
        return ConsumerVo.builder()
                .id(consumerEntity.getId())
                .consumerNumber(consumerEntity.getConsumerNumber())
                .slabId(consumerEntity.getSlabId())
                .slabVo(SlabMapper.mapToSlabVo(consumerEntity.getSlabEntity()))
                .firstName(consumerEntity.getFirstName())
                .lastName(consumerEntity.getLastName())
                .mobileNumber(consumerEntity.getMobileNumber())
                .addressLine1(consumerEntity.getAddressLine1())
                .addressLine1(consumerEntity.getAddressLine2())
                .zip(consumerEntity.getZip())
                .district(consumerEntity.getDistrict())
                .state(consumerEntity.getState())
                .build();
    }

    public static ConsumerVo mapToConsumerEntity(ConsumerVo consumerVo) {
        return ConsumerVo.builder()
                .id(consumerVo.getId())
                .consumerNumber(consumerVo.getConsumerNumber())
                .slabId(consumerVo.getSlabId())
                .firstName(consumerVo.getFirstName())
                .lastName(consumerVo.getLastName())
                .mobileNumber(consumerVo.getMobileNumber())
                .addressLine1(consumerVo.getAddressLine1())
                .addressLine1(consumerVo.getAddressLine2())
                .zip(consumerVo.getZip())
                .district(consumerVo.getDistrict())
                .state(consumerVo.getState())
                .build();
    }

}
