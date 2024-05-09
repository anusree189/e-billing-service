package com.electricity.billingsystem.service.impl;

import com.electricity.billingsystem.entity.SlabEntity;
import com.electricity.billingsystem.enums.RateStructure;
import com.electricity.billingsystem.exception.BadRequestException;
import com.electricity.billingsystem.repository.SlabRepository;
import com.electricity.billingsystem.service.SlabService;
import com.electricity.billingsystem.service.impl.mapper.SlabMapper;
import com.electricity.billingsystem.vo.ResponseVo;
import com.electricity.billingsystem.vo.SlabVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SlabServiceImpl implements SlabService {

    private final SlabRepository slabRepository;

    @Override
    public ResponseVo addSlab(SlabVo slabVo) {
        validateSlab(slabVo);
        if (slabVo.getUnitTo() <= 250) {
            slabVo.setType(RateStructure.valueOf(RateStructure.TELESCOPIC.name()));
        } else {
            slabVo.setType(RateStructure.valueOf(RateStructure.NON_TELESCOPIC.name()));
        }
        slabRepository.save(SlabMapper.mapToSlabEntity(slabVo));
        return new ResponseVo<>(HttpStatus.CREATED.name(), "Slab added");
    }

    @Override
    public ResponseVo<Page<SlabVo>> getAllSlabs(int pageNumber, int pageSize, String sortBy, String orderBy) {
        Sort.Direction direction = Sort.Direction.fromString(orderBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));
        Page<SlabEntity> slabEntities = slabRepository.findAll(pageable);
        List<SlabVo> slabVos = slabEntities.stream().map(SlabMapper::mapToSlabVo).toList();
        Page<SlabVo> slabVoPage = new PageImpl<>(slabVos, pageable, Objects.requireNonNull(slabEntities).getTotalElements());
        return new ResponseVo<>(HttpStatus.OK.name(), "List of slabs", slabVoPage);
    }

    private void validateSlab(SlabVo slabVo) {

        if (!isValidEpoch(slabVo.getStartDate())) {
            throw new BadRequestException("Invalid start date");
        }

        if (!isValidEpoch(slabVo.getEndDate())) {
            throw new BadRequestException("Invalid end date");
        }

        if(slabVo.getTariff() <= 0) {
            throw new BadRequestException("Add tariff");
        }

        if(Objects.equals(slabVo.getUnitFrom(), slabVo.getUnitTo()) || slabVo.getUnitFrom() < 0 ||
        slabVo.getUnitFrom() >= slabVo.getUnitTo() || slabVo.getUnitTo() <= 0) {
            throw new BadRequestException("Add valid unit range");
        }

        List<SlabEntity> slabEntityList = slabRepository.findByUnitFromAndUnitTo(
                slabVo.getUnitFrom(), slabVo.getUnitTo());
        boolean isBadRequest =
                slabEntityList.stream().anyMatch(slabEntity ->
                        slabVo.getStartDate() >= slabEntity.getStartDate() &&
                                slabVo.getEndDate() <= slabEntity.getEndDate());
        if (isBadRequest) {
            throw new BadRequestException("Slab already exists");
        }
    }

    public static boolean isValidEpoch(long timestamp) {
        try {
            Instant.ofEpochMilli(timestamp);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
}
