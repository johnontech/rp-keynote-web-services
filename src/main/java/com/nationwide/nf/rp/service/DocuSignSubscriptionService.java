package com.nationwide.nf.rp.service;

import com.nationwide.nf.rp.bean.DocuSignConfiguration;

public interface DocuSignSubscriptionService {

    DocuSignConfiguration[] getAllDocuSignSubscriptions();

    DocuSignConfiguration getDocuSignSubscriptionForId(String feedSeqId);

    DocuSignConfiguration getDocuSignSubscription(String feedSeqId);

    int updateDocuSignConfiguration(DocuSignConfiguration docuSignConfiguration);

    DocuSignConfiguration createDocuSignConfiguration(DocuSignConfiguration docuSignConfiguration);

    void deleteDocuSignSubscription(String feedSeqId);
}
