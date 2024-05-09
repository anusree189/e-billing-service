package com.electricity.billingsystem.controller;

import com.electricity.billingsystem.service.BillService;
import com.electricity.billingsystem.vo.BillVo;
import com.electricity.billingsystem.vo.ResponseVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/generate-bill")
    public ResponseVo<BillVo> generateBill(@RequestBody @Valid BillVo billVo) {
        return billService.generateBill(billVo);
    }

    @GetMapping("/get-by-consumerId")
    public ResponseVo<BillVo> getBill(@RequestParam(required = true) Long consumerId) {
        return billService.getBill(consumerId);
    }

    @GetMapping("/get-by-id")
    public ResponseVo<BillVo> getBillById(@RequestParam(required = true) Long id) {
        return billService.getBillById(id);
    }
}
