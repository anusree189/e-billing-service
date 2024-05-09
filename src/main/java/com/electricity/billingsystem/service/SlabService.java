package com.electricity.billingsystem.service;

import com.electricity.billingsystem.vo.ResponseVo;
import com.electricity.billingsystem.vo.SlabVo;
import org.springframework.data.domain.Page;

public interface SlabService {
    ResponseVo addSlab(SlabVo slabVo);

    ResponseVo<Page<SlabVo>> getAllSlabs(int pageNumber, int pageSize, String sortBy, String orderBy);
}
