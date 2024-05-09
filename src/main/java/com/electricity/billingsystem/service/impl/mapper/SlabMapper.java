package com.electricity.billingsystem.service.impl.mapper;

import com.electricity.billingsystem.entity.SlabEntity;
import com.electricity.billingsystem.vo.SlabVo;

public class SlabMapper {
    public static SlabVo mapToSlabVo(SlabEntity slabEntity) {
        return SlabVo.builder()
                .id(slabEntity.getId())
                .type(slabEntity.getType())
                .startDate(slabEntity.getStartDate())
                .endDate(slabEntity.getEndDate())
                .unitFrom(slabEntity.getUnitFrom())
                .unitTo(slabEntity.getUnitTo())
                .tariff(slabEntity.getTariff())
                .build();
    }

    public static SlabEntity mapToSlabEntity(SlabVo slabVo) {
        return SlabEntity.builder()
                .type(slabVo.getType())
                .startDate(slabVo.getStartDate())
                .endDate(slabVo.getEndDate())
                .unitFrom(slabVo.getUnitFrom())
                .unitTo(slabVo.getUnitTo())
                .tariff(slabVo.getTariff())
                .build();
    }
}
