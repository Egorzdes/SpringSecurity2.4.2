package app.dao;

import org.springframework.stereotype.Repository;
import app.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Set<Role> getAllRoles() {
        TypedQuery<Role> tQuery = (TypedQuery<Role>) entityManager.createQuery("SELECT r FROM Role r");
        Set<Role> internalRoleSet = new HashSet<>(tQuery.getResultList());
        return internalRoleSet;
    }

    @Override
    public Role getRoleId(long id) {
        return entityManager.find(Role.class, id);
    }
}

