package app.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import app.model.Role;
import app.service.RoleService;
import java.util.Set;


@Component
public class StringToRole implements Converter<String, Set<Role>> {

    @Autowired
    private RoleService roleService;

    public StringToRole() {
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Set<Role> convert(String inputString) {

        String[] Array = {inputString};
        return roleService.getRolesByName(Array);
    }
}
