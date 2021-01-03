package app.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import app.model.Role;
import app.service.RoleService;
import java.util.Set;


@Component
public class StringArrayToRole implements Converter<String[], Set<Role>> {

    @Autowired
    private RoleService roleService;

    public StringArrayToRole() {
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Set<Role> convert(String[] inputArr) {

        for (String str: inputArr) {
            System.out.println(str);
        }
        return roleService.getRolesByName(inputArr);
    }
}

