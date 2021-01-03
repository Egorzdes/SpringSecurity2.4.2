package app.service;

import java.util.Set;

import app.model.Role;

public interface RoleService {


    Set<Role> getAllRoles();

    Set<Role> getRolesByName(String[] Array);

    Role getRoleId(long id);
}


