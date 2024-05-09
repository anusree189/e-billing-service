package com.electricity.billingsystem.service;

import com.electricity.billingsystem.vo.BillVo;
import com.electricity.billingsystem.vo.ResponseVo;

public interface BillService {
    ResponseVo<BillVo> generateBill(BillVo billVo);

    ResponseVo<BillVo> getBill(Long consumerId);

    ResponseVo<BillVo> getBillById(Long id);
}
