package com.electricity.billingsystem.service;

import com.electricity.billingsystem.vo.ConsumerVo;
import com.electricity.billingsystem.vo.ResponseVo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

public interface ConsumerService {
    ResponseVo addConsumer(ConsumerVo consumerVo);
    ResponseVo<ConsumerVo> getConsumer(Long consumerNumber);

    ResponseVo<ConsumerVo> getConsumerById(Long id);
}
