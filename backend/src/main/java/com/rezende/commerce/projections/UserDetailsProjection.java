package com.rezende.commerce.projections;

public interface UserDetailsProjection {

    Long getRoleId();
    String getPassword();
    String getUsername();
    String getAuthority();
}
