package com.yyz.service.impl;

import com.yyz.domain.Items;
import com.yyz.service.ItemService;
import com.yyz.dao.ItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;

    public List<Items> getItemList() {
        List<Items> list = itemsMapper.selectAll();

        return list;
    }

}