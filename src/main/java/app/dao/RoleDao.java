package app.dao;

import app.model.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> getAllRoles();
    Role getRoleId(long id);
}
