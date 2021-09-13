package com.springweb.dv_spring_web_mongo.evaluator;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

public class OwnerEvaluator implements PermissionEvaluator {

    //not implemented
    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
//        Alternative method for evaluating a permission where only the identifier of the target object is available, rather than the target instance itself.
//                Params:
//        authentication – represents the user in question. Should not be null.
//                targetId – the identifier for the object instance (usually a Long)
//        targetType – a String representing the target's type (usually a Java classname). Not null.
//        permission – a representation of the permission object as supplied by the expression system. Not null.
//                Returns:
//        true if the permission is granted, false otherwise
        return false;
    }

    public boolean isOwner() {
        return true;
    }
}
