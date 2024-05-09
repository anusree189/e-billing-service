package com.electricity.billingsystem.service.impl;

import com.electricity.billingsystem.entity.ConsumerEntity;
import com.electricity.billingsystem.entity.SlabEntity;
import com.electricity.billingsystem.exception.BadRequestException;
import com.electricity.billingsystem.exception.NotFoundException;
import com.electricity.billingsystem.repository.ConsumerRepository;
import com.electricity.billingsystem.repository.SlabRepository;
import com.electricity.billingsystem.service.ConsumerService;
import com.electricity.billingsystem.service.impl.mapper.ConsumerMapper;
import com.electricity.billingsystem.vo.ConsumerVo;
import com.electricity.billingsystem.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static com.electricity.billingsystem.util.CommonUtil.convertToCamelCase;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository consumerRepository;
    private final SlabRepository slabRepository;

    @Override
    public ResponseVo addConsumer(ConsumerVo consumerVo) {
        validateConsumerVo(consumerVo);
        ConsumerEntity consumerEntity = ConsumerEntity.builder()
                .slabId(consumerVo.getSlabId())
                .consumerNumber(consumerVo.getConsumerNumber())
                .firstName(convertToCamelCase(consumerVo.getFirstName()))
                .lastName(convertToCamelCase(consumerVo.getLastName()))
                .mobileNumber(consumerVo.getMobileNumber().trim())
                .email(consumerVo.getEmail().trim())
                .addressLine1(consumerVo.getAddressLine1().trim())
                .addressLine2(consumerVo.getAddressLine2().trim())
                .zip(consumerVo.getZip().trim())
                .district(consumerVo.getDistrict().trim())
                .state(consumerVo.getState().trim())
                .build();
        consumerRepository.save(consumerEntity);
        return new ResponseVo<>(HttpStatus.CREATED.name(), "Consumer added");
    }

    private void validateConsumerVo(ConsumerVo consumerVo) {
        Long consumerNumber = generateRandomId();
        if (consumerRepository.existsByConsumerNumber(consumerNumber)) {
            consumerNumber = generateRandomId();
        }
        consumerVo.setConsumerNumber(consumerNumber);
        if (!slabRepository.existsById(consumerVo.getSlabId())) {
            throw new NotFoundException(HttpStatus.BAD_REQUEST.name(), "Slab id not found");
        }
        Optional<SlabEntity> slabEntity = slabRepository.findById(consumerVo.getSlabId());
        if(!slabEntity.isPresent() || (slabEntity.isPresent() && (slabEntity.get().getEndDate() <= Instant.now().toEpochMilli()
                || slabEntity.get().getStartDate() >= Instant.now().toEpochMilli() )) ) {
            throw new BadRequestException("Invalid slab");
        }
    }

    @Override
    public ResponseVo<ConsumerVo> getConsumer(Long consumerNumber) {
        ConsumerEntity consumerEntity = consumerRepository.findByConsumerNumber(consumerNumber);
        if (Objects.isNull(consumerEntity)) {
            throw new NotFoundException(HttpStatus.NOT_FOUND.name(), "Consumer not found");
        } else {
            return new ResponseVo<>(HttpStatus.OK.name(), "Consumer details", ConsumerMapper.mapToConsumerVo(consumerEntity));
        }
    }

    @Override
    public ResponseVo<ConsumerVo> getConsumerById(Long id) {
        Optional<ConsumerEntity> consumerEntity = consumerRepository.findById(id);
        if(!consumerEntity.isPresent()) {
              throw new NotFoundException(HttpStatus.NOT_FOUND.name(), "Consumer not found");
        }
        return new ResponseVo<>(HttpStatus.OK.name(), "Consumer details", ConsumerMapper.mapToConsumerVo(consumerEntity.get()));

    }

    public long generateRandomId() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextLong(10_000_000_000L, 100_000_000_000L);
    }
}
