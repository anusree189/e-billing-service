package com.electricity.billingsystem.service.impl;

import com.electricity.billingsystem.entity.ConsumerEntity;
import com.electricity.billingsystem.entity.SlabEntity;
import com.electricity.billingsystem.enums.RateStructure;
import com.electricity.billingsystem.repository.ConsumerRepository;
import com.electricity.billingsystem.repository.SlabRepository;
import com.electricity.billingsystem.vo.ConsumerVo;
import com.electricity.billingsystem.vo.ResponseVo;
import com.electricity.billingsystem.vo.SlabVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Optional;

class ConsumerServiceImplTest {

    @Mock
    ConsumerRepository consumerRepository;

    @Mock
    SlabRepository slabRepository;

    @InjectMocks
    ConsumerServiceImpl consumerService;

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
        Mockito.when(slabRepository.findById(Mockito.any()))
                .thenReturn(Optional.ofNullable(SlabEntity.builder()
                        .id(1L)
                        .tariff(1.5)
                        .build()));

        Mockito.when(consumerRepository.save(Mockito.any()))
                .thenReturn(new ResponseVo<>(HttpStatus.CREATED.name(), "Consumer added"));
        ResponseVo responseVo = consumerService.addConsumer(consumerVo);
        Assertions.assertEquals(responseVo.getStatus(), HttpStatus.CREATED.name());
    }

    @Test
    void getConsumer() {
        Long consumerNumber = 10000L;
        ConsumerEntity consumerEntity = ConsumerEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .slabId(1L)
                .slabEntity(SlabEntity.builder()
                        .id(1L)
                        .tariff(1.0)
                        .startDate(12345678L)
                        .endDate(92345671L)
                        .type(RateStructure.TELESCOPIC)
                        .unitTo(40)
                        .unitFrom(0)
                        .build())
                .addressLine1("Test")
                .addressLine2("Test")
                .mobileNumber("+913477788")
                .district("Test")
                .zip("123456")
                .state("kerala")
                .build();
        Mockito.when(consumerRepository.findByConsumerNumber(Mockito.any()))
                .thenReturn(consumerEntity);
        ConsumerVo consumerVo = consumerService.getConsumer(consumerNumber).getData();
        Assertions.assertEquals(consumerVo.getConsumerNumber(), consumerNumber);

    }

    @Test
    void getConsumerById() {
        Long id = 1L;
        ConsumerEntity consumerEntity = ConsumerEntity.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .slabId(1L)
                .slabEntity(SlabEntity.builder()
                        .id(1L)
                        .tariff(1.0)
                        .startDate(12345678L)
                        .endDate(92345671L)
                        .type(RateStructure.TELESCOPIC)
                        .unitTo(40)
                        .unitFrom(0)
                        .build())
                .addressLine1("Test")
                .addressLine2("Test")
                .mobileNumber("+913477788")
                .district("Test")
                .zip("123456")
                .state("kerala")
                .build();
        Mockito.when(consumerRepository.findById(Mockito.any()))
                .thenReturn(Optional.ofNullable(consumerEntity));
        ConsumerVo consumerVo = consumerService.getConsumerById(id).getData();
        Assertions.assertEquals(consumerVo.getId(), id);
    }
}