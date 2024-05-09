package com.electricity.billingsystem.service.impl;

import com.electricity.billingsystem.entity.BillEntity;
import com.electricity.billingsystem.entity.ConsumerEntity;
import com.electricity.billingsystem.entity.SlabEntity;
import com.electricity.billingsystem.repository.BillRepository;
import com.electricity.billingsystem.repository.ConsumerRepository;
import com.electricity.billingsystem.vo.BillVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

class BillServiceImplTest {

    @InjectMocks
    BillServiceImpl billService;

    @Mock
    ConsumerRepository consumerRepository;

    @Mock
    BillRepository billRepository;

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
        Mockito.when(billRepository.findLastBillByConsumerId(Mockito.any()))
                .thenReturn(Optional.ofNullable(BillEntity.builder()
                        .billNumber(1000000L)
                        .consumerId(1L)
                        .paid((short) 0)
                        .consumedUnits(10.0)
                        .dueAmount(50.0)
                        .dueDate(LocalDate.now())
                        .build()));
        Mockito.when(consumerRepository.findById(Mockito.any()))
                .thenReturn(Optional.ofNullable(ConsumerEntity.builder()
                        .id(1L)
                        .email("test@gmail.com")
                        .mobileNumber("+919995500111")
                        .firstName("John")
                        .lastName("Doe")
                        .addressLine1("Line1")
                        .addressLine2("Line2")
                        .district("Kottayam")
                        .zip("682030")
                        .state("Kerala")
                        .slabId(1L)
                        .consumerNumber(10000L)
                        .slabEntity(SlabEntity.builder()
                                .id(1L)
                                .tariff(1.5)
                                .build())
                        .build()));
        Mockito.when(billRepository.save(Mockito.any()))
                .thenReturn(BillEntity.builder()
                        .billNumber(1000000L)
                        .consumerId(1L)
                        .paid((short) 0)
                        .consumedUnits(10.0)
                        .dueAmount(50.0)
                        .dueDate(LocalDate.now())
                        .build());
        BillVo billVo1 = billService.generateBill(billVo).getData();
        Assertions.assertEquals(billVo1.getConsumedUnits(), billVo.getConsumedUnits());
    }

    @Test
    void getBill() {
        Long consumerId = 1L;
        Mockito.when(billRepository.findLastBillByConsumerId(Mockito.any()))
                .thenReturn(Optional.ofNullable(BillEntity.builder()
                        .billNumber(1000000L)
                        .consumerId(1L)
                        .paid((short) 0)
                        .consumedUnits(10.0)
                        .dueAmount(50.0)
                        .dueDate(LocalDate.now())
                        .build()));
        BillVo billVo = billService.getBill(consumerId).getData();
        Assertions.assertEquals(billVo.getConsumerId(), consumerId);
    }

    @Test
    void getBillById() {
        Long id = 1L;
        Mockito.when(billRepository.findById(Mockito.any()))
                .thenReturn(
                        Optional.ofNullable(BillEntity.builder()
                                .billNumber(1000000L)
                                .consumerId(1L)
                                .paid((short) 0)
                                .consumedUnits(10.0)
                                .dueAmount(50.0)
                                .dueDate(LocalDate.now())
                                .build()));
        BillVo billVo = billService.getBillById(id).getData();
        Assertions.assertEquals(billVo.getId(), id);
    }
}