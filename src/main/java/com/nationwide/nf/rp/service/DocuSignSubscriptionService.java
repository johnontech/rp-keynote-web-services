package com.nationwide.nf.rp.service;

import com.nationwide.nf.rp.bean.AllDocuSignConfigurations;
import com.nationwide.nf.rp.bean.DocuSignConfiguration;

public interface DocuSignSubscriptionService {

    AllDocuSignConfigurations getAllDocuSignSubscriptions();

    DocuSignConfiguration getDocuSignSubscription(String feedSeqId);

    DocuSignConfiguration updateDocuSignConfiguration(DocuSignConfiguration docuSignConfiguration);

    DocuSignConfiguration createDocuSignConfiguration(DocuSignConfiguration docuSignConfiguration);

    DocuSignConfiguration deleteDocuSignSubscription(String feedSeqId);
}
