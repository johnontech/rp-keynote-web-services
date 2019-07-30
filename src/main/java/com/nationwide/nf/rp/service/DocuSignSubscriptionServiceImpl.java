package com.nationwide.nf.rp.service;

import com.nationwide.nf.rp.bean.DocuSignConfiguration;
import com.nationwide.nf.rp.data.dao.dao.JdbcDocuSignSubscriberFeedDao;
import com.nationwide.nf.rp.entity.FeedEntity;
import com.nationwide.nf.rp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DocuSignSubscriptionServiceImpl  implements DocuSignSubscriptionService {

    @Autowired
    JdbcDocuSignSubscriberFeedDao jdbcDocuSignSubscriberFeedDao;

    @Autowired
    DateUtil dateUtil;

    public DocuSignConfiguration getDocuSignSubscription(String feedSeqId) {
        return getDocuSignSubscriptionForId(feedSeqId);
    }

    public DocuSignConfiguration getDocuSignSubscriptionForId(String feedSeqId) {
        List<FeedEntity> feedDetailsForSubscriber = jdbcDocuSignSubscriberFeedDao.getFeedDetailsForSubscriber(Integer.parseInt(feedSeqId));
        DocuSignConfiguration docuSignConfiguration = new DocuSignConfiguration();
        docuSignConfiguration.setSeqId(String.valueOf(feedDetailsForSubscriber.get(0).getSeqId()));
        docuSignConfiguration.setSubscriptionName(feedDetailsForSubscriber.get(0).getSubscriberName());
        docuSignConfiguration.setSubscriptionStatus(feedDetailsForSubscriber.get(0).getSubscrStatus());
        docuSignConfiguration.setSubscriptionBeginDate(feedDetailsForSubscriber.get(0).getSubscrBeginDate().toString());
        docuSignConfiguration.setSubscriptionEndDate((feedDetailsForSubscriber.get(0).getSubscrEndDate() == null) ? null :
                                                      feedDetailsForSubscriber.get(0).getSubscrEndDate().toString());
        docuSignConfiguration.setFileTransferMethod(feedDetailsForSubscriber.get(0).getFileXferMethod());
        docuSignConfiguration.setFileTransferId(feedDetailsForSubscriber.get(0).getFileXferId());
        docuSignConfiguration.setFileTransferDirectory(feedDetailsForSubscriber.get(0).getFileXferDir());
        return docuSignConfiguration;
    }

    public DocuSignConfiguration[] getAllDocuSignSubscriptions() {
        List<FeedEntity> allFeedDetailsForSubscribers = jdbcDocuSignSubscriberFeedDao.getAllFeedDetailsForSubscribers();

        int idx = 0;
        DocuSignConfiguration[] docuSignConfigurations = new DocuSignConfiguration[allFeedDetailsForSubscribers.size()];
        for (FeedEntity feedEntity : allFeedDetailsForSubscribers) {
            DocuSignConfiguration docuSignConfiguration = new DocuSignConfiguration();

            docuSignConfiguration.setSeqId(String.valueOf(feedEntity.getSeqId()));
            docuSignConfiguration.setSubscriptionName(feedEntity.getSubscriberName());
            docuSignConfiguration.setSubscriptionBeginDate(feedEntity.getSubscrBeginDate().toString());
            docuSignConfiguration.setSubscriptionEndDate(feedEntity.getSubscrEndDate() != null ? feedEntity.getSubscrEndDate().toString() : null);
            docuSignConfiguration.setSubscriptionStatus(feedEntity.getSubscrStatus());
            docuSignConfiguration.setFileTransferMethod(feedEntity.getFileXferMethod());
            docuSignConfiguration.setFileTransferId(feedEntity.getFileXferId());
            docuSignConfiguration.setFileTransferDirectory(feedEntity.getFileXferDir());

            docuSignConfigurations[idx] = docuSignConfiguration;
            ++idx;
        }
        return docuSignConfigurations;
    }

    public int updateDocuSignConfiguration(DocuSignConfiguration docuSignConfiguration) {
        docuSignConfiguration.setSubscriptionBeginDate(dateUtil.reformatDate(
        "yyyy-MM-dd", docuSignConfiguration.getSubscriptionBeginDate()));
        docuSignConfiguration.setSubscriptionEndDate(dateUtil.reformatDate(
        "yyyy-MM-dd", docuSignConfiguration.getSubscriptionEndDate()));
        return jdbcDocuSignSubscriberFeedDao.updateDocuSignConfiguration(docuSignConfiguration);
    }

    public DocuSignConfiguration createDocuSignConfiguration(DocuSignConfiguration docuSignConfiguration) {
        docuSignConfiguration.setSubscriptionBeginDate(dateUtil.reformatDate(
                "yyyy-MM-dd", docuSignConfiguration.getSubscriptionBeginDate()));
        docuSignConfiguration.setSubscriptionEndDate(dateUtil.reformatDate(
                "yyyy-MM-dd", docuSignConfiguration.getSubscriptionEndDate()));
        return jdbcDocuSignSubscriberFeedDao.create(docuSignConfiguration);
    }

    public DocuSignConfiguration deleteDocuSignSubscription(String feedSeqId) {
        return jdbcDocuSignSubscriberFeedDao.deleteDocuSignConfiguration(feedSeqId);
    }
}
