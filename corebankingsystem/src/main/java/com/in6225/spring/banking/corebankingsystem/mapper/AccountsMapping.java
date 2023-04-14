package com.in6225.spring.banking.corebankingsystem.mapper;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in6225.spring.banking.corebankingsystem.dto.AccountsDTO;
import com.in6225.spring.banking.corebankingsystem.entities.Accounts;
import com.in6225.spring.banking.corebankingsystem.entities.Users;
import com.in6225.spring.banking.corebankingsystem.repos.UserRepository;

public class AccountsMapping extends BaseMappings<Accounts, AccountsDTO> {
	
	@Override
    public Accounts convertToEntity(AccountsDTO dto, Object... args) {
        Accounts entity = new Accounts();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
            
        }
        return entity;
    }

	@Override
    public AccountsDTO convertToDto(Accounts entity, Object... args) {
        AccountsDTO dto = new AccountsDTO();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }
}
