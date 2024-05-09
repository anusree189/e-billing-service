package com.electricity.billingsystem.controller;

import com.electricity.billingsystem.enums.RateStructure;
import com.electricity.billingsystem.service.SlabService;
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
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SlabControllerTest {

    @InjectMocks
    SlabController slabController;

    @Mock
    SlabService slabService;

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
        Mockito.when(slabService.addSlab(Mockito.any()))
                .thenReturn(new
                        ResponseVo<>(HttpStatus.CREATED.name(), "Slab added"));

        ResponseVo responseVo = slabController.addSlab(slabVo);
        Assertions.assertEquals(responseVo.getStatus(), HttpStatus.CREATED.name());

    }


    @Test
    public void getAll() {

        List<SlabVo> slabVoList = new ArrayList<>();
        slabVoList.add(SlabVo.builder()
                .startDate(1640995200L)
                .endDate(1640995200L)
                .unitFrom(0)
                .unitTo(40)
                .tariff(1.5)
                .type(RateStructure.valueOf(RateStructure.TELESCOPIC.name()))
                .build());

        Pageable pageable = PageRequest.of(0, 20);
        Page<SlabVo> slabVoPage = new PageImpl<>(slabVoList, pageable, slabVoList.size());

        Mockito.when(slabService.getAllSlabs(
                0, 20, "id", "asc"))
                .thenReturn(
                        new ResponseVo<>(HttpStatus.OK.name(), "List of slabs", slabVoPage)
                );
        ResponseVo<Page<SlabVo>> response = slabController.getAll(0, 20, "id", "asc");

        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.name());
    }
}
