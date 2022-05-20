package ru.learnUp.market.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.learnUp.market.dao.user.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleView {

    private String role;

    public RoleView(Role role){
        this.role = role.getRole();
    }

}
