package com.springweb.dv_spring_web_mongo.evaluator;

import com.springweb.dv_spring_web_mongo.model.Project;
import com.springweb.dv_spring_web_mongo.model.User;
import com.springweb.dv_spring_web_mongo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;


public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private ProjectService projectService;

    //not implemented
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        throw new ApplicationContextException("Method unavailable");
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        String perm = (String) permission;
        if (perm.equals("projectOwner")) {
            User user = (User) authentication.getPrincipal();
            String projectId = (String) targetId;
            Project project = projectService.getById(projectId);
            if (project.getOwnerId().equals(user.getId())) {
                return true;
            }
        }
        return false;
    }
}
