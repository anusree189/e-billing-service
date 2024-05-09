package com.electricity.billingsystem.service.impl;

import com.electricity.billingsystem.entity.BillEntity;
import com.electricity.billingsystem.entity.ConsumerEntity;
import com.electricity.billingsystem.entity.SlabEntity;
import com.electricity.billingsystem.exception.BadRequestException;
import com.electricity.billingsystem.exception.NotFoundException;
import com.electricity.billingsystem.repository.BillRepository;
import com.electricity.billingsystem.repository.ConsumerRepository;
import com.electricity.billingsystem.repository.SlabRepository;
import com.electricity.billingsystem.service.BillService;
import com.electricity.billingsystem.service.impl.mapper.BillMapper;
import com.electricity.billingsystem.vo.BillVo;
import com.electricity.billingsystem.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import static com.electricity.billingsystem.util.CommonUtil.generateUniqueNumber;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final ConsumerRepository consumerRepository;
    private final BillRepository billRepository;

    private static final double basicCharge  = 50.00;
    private final SlabRepository slabRepository;

    @Override
    public ResponseVo<BillVo> generateBill(BillVo billVo) {
        validateBillVo(billVo);
        Double previousConsumedUnits = billRepository.findLastBillByConsumerId(billVo.getConsumerId())
                .map(BillEntity::getConsumedUnits)
                .orElse(0.0);
        ConsumerEntity consumerEntity = consumerRepository.findById(billVo.getConsumerId()).orElseThrow();
        SlabEntity slabEntity = consumerEntity.getSlabEntity();
        Double consumedUnits = billVo.getConsumedUnits() - previousConsumedUnits;
        Double dueAmount = (consumedUnits * slabEntity.getTariff()) + basicCharge;

        billVo.setBillNumber((long) generateUniqueNumber());
        billVo.setDueAmount(dueAmount);
        billVo.setBasicCharge(basicCharge);
        billVo.setPaid((short) 0);
        billVo.setDueDate(LocalDate.now().plusDays(10));

        BillEntity newBill = BillMapper.mapToBillEntity(billVo);
        newBill = billRepository.save(newBill);
        newBill.setConsumerEntity(consumerEntity);
        return new ResponseVo<>(HttpStatus.CREATED.name(), "Bill generated", BillMapper.mapToBillVo(newBill));
    }

    private void validateBillVo(BillVo billVo) {
        ConsumerEntity consumerEntity = consumerRepository.findById(billVo.getConsumerId()).get();
        if(Objects.isNull(billVo.getConsumerId())) {
            throw new NotFoundException(HttpStatus.NOT_FOUND.name(), "Consumer id not found");
        }
        boolean billEntity = billRepository.existsByConsumerIdAndDueDate(billVo.getConsumerId(), LocalDate.now().plusDays(10));
        if(billEntity) {
            throw new BadRequestException("Bill already generated");
        }
    }

    @Override
    public ResponseVo<BillVo> getBill(Long consumerId) {
        Optional<BillEntity> billEntity = billRepository.findLastBillByConsumerId(consumerId);
        if(!billEntity.isPresent()){
            throw new NotFoundException(HttpStatus.BAD_REQUEST.name(), "Consumer not found");
        }
        return new ResponseVo<>(HttpStatus.OK.name(), "Bill details", BillMapper.mapToBillVo(billEntity.get()));
    }

    @Override
    public ResponseVo<BillVo> getBillById(Long id) {
        Optional<BillEntity> billEntity = billRepository.findById(id);
        if(!billEntity.isPresent()){
            throw new NotFoundException(HttpStatus.BAD_REQUEST.name(), "Consumer not found");
        }

        return new ResponseVo<>(HttpStatus.CREATED.name(), "Bill details", BillMapper.mapToBillVo(billEntity.get()));
    }
}
