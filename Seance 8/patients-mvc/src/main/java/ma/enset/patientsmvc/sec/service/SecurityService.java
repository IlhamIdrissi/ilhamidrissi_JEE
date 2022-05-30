package ma.enset.patientsmvc.sec.service;

import ma.enset.patientsmvc.sec.entities.AppRole;
import ma.enset.patientsmvc.sec.entities.AppUser;

public interface SecurityService {
    AppUser saveUser(String username, String password, String rePassword);
    AppRole saveRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
}
