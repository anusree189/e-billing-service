package com.electricity.billingsystem.controller;

import com.electricity.billingsystem.service.SlabService;
import com.electricity.billingsystem.vo.ResponseVo;
import com.electricity.billingsystem.vo.SlabVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slab")
public class SlabController {

    @Autowired
    private SlabService slabService;

    @PostMapping("/add-slab")
    public ResponseVo addSlab(@RequestBody @Valid SlabVo slabVo) {
        return slabService.addSlab(slabVo);
    }

    @GetMapping("/get-all")
    public ResponseVo<Page<SlabVo>> getAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "20") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String orderBy) {
        return slabService.getAllSlabs(pageNumber, pageSize, sortBy, orderBy);
    }

}
