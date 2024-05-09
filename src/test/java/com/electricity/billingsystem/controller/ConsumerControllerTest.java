package com.electricity.billingsystem.controller;

import com.electricity.billingsystem.service.ConsumerService;
import com.electricity.billingsystem.vo.BillVo;
import com.electricity.billingsystem.vo.ConsumerVo;
import com.electricity.billingsystem.vo.ResponseVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class ConsumerControllerTest {

    @InjectMocks
    ConsumerController consumerController;

    @Mock
    ConsumerService consumerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.
                initMocks
                        (this);
    }

    @Test
    void addConsumer() {
        ConsumerVo consumerVo = ConsumerVo.builder()
                .firstName("John")
                .lastName("Doe")
                .slabId(1L)
                .addressLine1("Test")
                .addressLine2("Test")
                .mobileNumber("+913477788")
                .district("Test")
                .zip("123456")
                .state("kerala")
                .build();
        Mockito.when(consumerService.addConsumer(Mockito.any()))
                .thenReturn(new
                        ResponseVo<>(HttpStatus.CREATED.name(), "Consumer added"));
        ResponseVo responseVo = consumerController.addConsumer(consumerVo);
        Assertions.assertEquals(responseVo.getStatus(), HttpStatus.CREATED.name());
    }

    @Test
    void getConsumer() {
        Long consumerNumber = 10000L;
        Mockito.when(consumerService.getConsumer(Mockito.any()))
                .thenReturn(new ResponseVo<>(
                        HttpStatus.OK.name(), "Consumer details",
                        ConsumerVo.builder()
                                .id(1L)
                                .consumerNumber(10000L)
                                .firstName("John")
                                .lastName("Doe")
                                .slabId(1L)
                                .addressLine1("Test")
                                .addressLine2("Test")
                                .mobileNumber("+913477788")
                                .district("Test")
                                .zip("123456")
                                .state("kerala")
                                .build()));
        ConsumerVo consumerVo = consumerService.getConsumer(consumerNumber).getData();
        Assertions.assertEquals(consumerNumber, consumerVo.getConsumerNumber());
    }

    @Test
    void getConsumerById() {
        Long id = 1L;
        Mockito.when(consumerService.getConsumerById(Mockito.any()))
                .thenReturn(new ResponseVo<>(
                        HttpStatus.OK.name(), "Consumer details",
                        ConsumerVo.builder()
                                .id(1L)
                                .firstName("John")
                                .lastName("Doe")
                                .slabId(1L)
                                .addressLine1("Test")
                                .addressLine2("Test")
                                .mobileNumber("+913477788")
                                .district("Test")
                                .zip("123456")
                                .state("kerala")
                                .build()));
        ConsumerVo consumerVo = consumerService.getConsumerById(id).getData();
        Assertions.assertEquals(id, consumerVo.getId());
    }
}