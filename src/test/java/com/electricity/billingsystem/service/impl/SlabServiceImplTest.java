package com.electricity.billingsystem.service.impl;

import com.electricity.billingsystem.entity.SlabEntity;
import com.electricity.billingsystem.enums.RateStructure;
import com.electricity.billingsystem.repository.SlabRepository;
import com.electricity.billingsystem.vo.ResponseVo;
import com.electricity.billingsystem.vo.SlabVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

class SlabServiceImplTest {

    @Mock
    SlabRepository slabRepository;

    @InjectMocks
    SlabServiceImpl slabService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.
                initMocks
                        (this);
    }

    @Test
    void addSlab() {

        SlabVo slabVo = SlabVo.builder()
                .type(RateStructure.TELESCOPIC)
                .tariff(1.5)
                .startDate(1640995200L)
                .endDate(1640995400L)
                .unitFrom(0)
                .unitTo(0)
                .build();
        Mockito.when(slabRepository.findByUnitFromAndUnitTo(Mockito.any(), Mockito.any()))
                .thenReturn(new ArrayList<>());
        Mockito.when(slabRepository.save(Mockito.any(SlabEntity.class)))
                .thenAnswer(invocation -> {
                    SlabEntity slabEntity = invocation.getArgument(0);
                    return slabEntity;
                });
        ResponseVo responseVo = slabService.addSlab(slabVo);
        Assertions.assertEquals(responseVo.getStatus(), HttpStatus.CREATED.name());

    }


    @Test
    void getAllSlabs() {
        List<SlabEntity> slabEntities = new ArrayList<>();
        slabEntities.add(SlabEntity.builder()
                .id(1L)
                .startDate(1640995200L)
                .endDate(1640995200L)
                .unitFrom(0)
                .unitTo(40)
                .tariff(1.5)
                .type(RateStructure.valueOf(RateStructure.TELESCOPIC.name()))
                .build());

        Pageable pageable = PageRequest.of(0, 20);
        Page<SlabEntity> slabEntityPage = new PageImpl<>(slabEntities, pageable, slabEntities.size());
        Mockito.when(slabRepository.findAll((Pageable) Mockito.any()))
                .thenReturn(slabEntityPage);
        ResponseVo<Page<SlabVo>> response = slabService.getAllSlabs(0, 20, "id", "asc");
        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.name());

    }
}