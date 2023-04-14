package com.in6225.spring.banking.corebankingsystem.mapper;

import org.springframework.beans.BeanUtils;

import com.in6225.spring.banking.corebankingsystem.dto.UsersDTO;
import com.in6225.spring.banking.corebankingsystem.entities.Users;

public class UserMapping extends BaseMappings<Users, UsersDTO> {
	
	@Override
    public Users convertToEntity(UsersDTO dto, Object... args) {
        Users entity = new Users();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
        }
        return entity;
    }
	
	@Override
    public UsersDTO convertToDto(Users entity, Object... args) {
        UsersDTO dto = new UsersDTO();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
            
        }
        return dto;
    }

}
