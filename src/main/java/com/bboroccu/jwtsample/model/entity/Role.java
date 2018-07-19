package com.bboroccu.jwtsample.model.entity;

/**
 * Enumerated {@link User} roles.
 *
 * @author vladimir.stankovic
 *
 * Aug 16, 2016
 */
public enum Role {
    ADMIN, ALL_USE, RECEIPT_USE, TAKEBACK_USE;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
