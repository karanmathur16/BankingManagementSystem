package com.in6225.spring.banking.corebankingsystem.mapper;

import org.springframework.beans.BeanUtils;

import com.in6225.spring.banking.corebankingsystem.dto.TransactionsDTO;
import com.in6225.spring.banking.corebankingsystem.entities.Transactions;

public class TransactionsMapping extends BaseMappings<Transactions, TransactionsDTO> {
	
	@Override
    public Transactions convertToEntity(TransactionsDTO dto, Object... args) {
        Transactions entity = new Transactions();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
        }
        return entity;
    }
	
	@Override
    public TransactionsDTO convertToDto(Transactions entity, Object... args) {
        TransactionsDTO dto = new TransactionsDTO();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
            
        }
        return dto;
    }

}
