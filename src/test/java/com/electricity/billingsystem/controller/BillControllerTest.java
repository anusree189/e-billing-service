package com.electricity.billingsystem.controller;

import com.electricity.billingsystem.service.BillService;
import com.electricity.billingsystem.vo.BillVo;
import com.electricity.billingsystem.vo.ResponseVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

class BillControllerTest {

    @InjectMocks
    BillController billController;

    @Mock
    BillService billService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.
                initMocks
                        (this);
    }

    @Test
    void generateBill() {
        BillVo billVo = BillVo.builder()
                .consumerId(1L)
                .consumedUnits(10.0)
                .build();
        Mockito.when(billService.generateBill(Mockito.any())).thenReturn(new
                ResponseVo<>(HttpStatus.CREATED.name(), "Bill generated", BillVo.builder()
                .id(1L)
                .billNumber(1000000L)
                .consumerId(1L)
                .paid((short) 0)
                .consumedUnits(10.0)
                .dueAmount(50.0)
                .dueDate(LocalDate.now())
                .basicCharge(50.0)
                .build()));
        ResponseVo<BillVo> billVoResponseVo = billController.generateBill(billVo);
        Assertions.assertEquals(billVo.getConsumerId(), billVoResponseVo.getData().getConsumerId());

    }

    @Test
    void getBill() {
        Long consumerId = 10000L;
        Mockito.when(billService.getBill(Mockito.any()))
                .thenReturn(new ResponseVo<>(
                        HttpStatus.OK.name(), "Bill details", BillVo.builder()
                        .consumerId(10000L)
                        .paid((short) 0)
                        .consumedUnits(10.0)
                        .dueAmount(50.0)
                        .dueDate(LocalDate.now())
                        .basicCharge(50.0)
                        .build()));
        ResponseVo<BillVo> billVoResponseVo = billController.getBill(consumerId);
        Assertions.assertEquals(billVoResponseVo.getData().getConsumerId(), consumerId);
    }

    @Test
    void getBillById() {
        Long billId = 10000L;
        Mockito.when(billService.getBill(Mockito.any()))
                .thenReturn(new ResponseVo<>(
                        HttpStatus.OK.name(), "Bill details", BillVo.builder()
                        .id(10000L)
                        .consumerId(10000L)
                        .paid((short) 0)
                        .consumedUnits(10.0)
                        .dueAmount(50.0)
                        .dueDate(LocalDate.now())
                        .basicCharge(50.0)
                        .build()));
        ResponseVo<BillVo> billVoResponseVo = billController.getBillById(billId);
        Assertions.assertEquals(billVoResponseVo.getData().getId(), billId);
    }
}