package hcmus.brightdemy.security.service;

import hcmus.brightdemy.security.dto.LoginDTO;

public interface AuthService {
    public Object login (LoginDTO dto);
}
