package es624.service.impl;

import es624.dao.source1.ItemsMapper;
import es624.domain.Items;
import es624.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<Items> getItemList() {
        List<Items> list = itemsMapper.selectAll();

        return list;
    }

}