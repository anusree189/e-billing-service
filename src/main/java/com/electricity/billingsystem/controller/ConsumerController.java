package com.electricity.billingsystem.controller;

import com.electricity.billingsystem.service.ConsumerService;
import com.electricity.billingsystem.vo.ConsumerVo;
import com.electricity.billingsystem.vo.ResponseVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @PostMapping("/add-consumer")
    public ResponseVo addConsumer(@RequestBody @Valid ConsumerVo consumerVo) {
        return consumerService.addConsumer(consumerVo);
    }

    @GetMapping("")
    public ResponseVo<ConsumerVo> getConsumer(@RequestParam Long consumerNumber) {
        return consumerService.getConsumer(consumerNumber);
    }

    @GetMapping("/get-by-id")
    public ResponseVo<ConsumerVo> getConsumerById(@RequestParam Long id) {
        return consumerService.getConsumerById(id);
    }
}
