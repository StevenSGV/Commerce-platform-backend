package com.stevensgv.auth_service.service;

import com.stevensgv.auth_service.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface IUserFeign {

    @GetMapping("/email/{email}")
    UserDTO getUserByEmail(@PathVariable String email);
}
