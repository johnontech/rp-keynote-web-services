package com.nationwide.nf.rp.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorgej2 on 9/17/2017.
 */
public class FailedDocSignMqJobResult {
    private List<FailedEnrollment> failedEnrollments = new ArrayList<FailedEnrollment>();

    public void setFailedEnrollments(List<FailedEnrollment> failedEnrollments) {
        this.failedEnrollments = failedEnrollments;
    }

    public List<FailedEnrollment> getFailedEnrollments() {
        return failedEnrollments;
    }

    public void add(FailedEnrollment failedEnrollment) {
        failedEnrollments.add(failedEnrollment);
    }

    public boolean hasFailedEnrollments() {
        return failedEnrollments.size() > 0;
    }

    public int getFailedEnrollmentCount() {
        return failedEnrollments.size();
    }
}
