package com.dmdev.listener;

import com.dmdev.entity.Revision;
import org.hibernate.envers.RevisionListener;
public class MyRevisionListener implements RevisionListener {


    @Override
    public void newRevision(Object revisionEntity) {
        // SecurityContext.getUser().getId() ...
        ((Revision) revisionEntity).setUsername("user who do something");
    }
}
