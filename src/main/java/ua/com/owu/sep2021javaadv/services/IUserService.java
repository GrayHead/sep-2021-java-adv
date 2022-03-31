package ua.com.owu.sep2021javaadv.services;

import ua.com.owu.sep2021javaadv.models.dto.UserDTO;

import java.util.List;

public interface IUserService {

    List<UserDTO> findAllUserDTO();
}
