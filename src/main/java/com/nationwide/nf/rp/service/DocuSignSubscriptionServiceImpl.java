package com.nationwide.nf.rp.service;

import com.nationwide.nf.rp.bean.AllDocuSignConfigurations;
import com.nationwide.nf.rp.bean.DocuSignConfiguration;
import com.nationwide.nf.rp.bean.DocuSignSubscriptionFile;
import com.nationwide.nf.rp.data.dao.dao.JdbcDocuSignSubscriberFeedDao;
import com.nationwide.nf.rp.entity.FeedEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocuSignSubscriptionServiceImpl  implements DocuSignSubscriptionService {

    @Autowired
    JdbcDocuSignSubscriberFeedDao jdbcDocuSignSubscriberFeedDao;

    public DocuSignConfiguration getDocuSignSubscriptionForId(String feedSeqId) {
        List<FeedEntity> feedDetailsForSubscriber = jdbcDocuSignSubscriberFeedDao.getFeedDetailsForSubscriber(Integer.parseInt(feedSeqId));
        DocuSignConfiguration docuSignConfiguration = new DocuSignConfiguration();
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

    public DocuSignConfiguration getDocuSignSubscription(String feedSeqId) {

        return getDocuSignSubscriptionForId(feedSeqId);

//        DocuSignConfiguration docuSignConfiguration = new DocuSignConfiguration();
//
//        docuSignConfiguration.setSubscriptionName("SchoolsFirst");
//        docuSignConfiguration.setSubscriptionStatus("Active");
//        docuSignConfiguration.setSubscriptionBeginDate("01-01-2019");
//        docuSignConfiguration.setSubscriptionEndDate("12-31-2021");
//        docuSignConfiguration.setFileTransferMethod("EB2B");
//        docuSignConfiguration.setFileTransferId("/devl/rptest1/eb2b");
//        docuSignConfiguration.setFileTransferDirectory("/transport/in/PENSIONS/KEYNOTE_OUT");
//        docuSignConfiguration.setFileSubscriptionCases("SCHOOLSFIRST FEDERAL");
//
//        DocuSignSubscriptionFile[] docuSignSubscriptionFiles = new DocuSignSubscriptionFile[4];
//
//        DocuSignSubscriptionFile docuSignSubscriptionFile = new DocuSignSubscriptionFile();
//        docuSignSubscriptionFile.setFileName("Census");
//        docuSignSubscriptionFile.setBeginDate("Jan 1, 2017");
//        docuSignSubscriptionFile.setEndDate("Feb 1, 2017");
//        docuSignSubscriptionFile.setInternalEmail("census@gmail.com");
//        docuSignSubscriptionFiles[0] = docuSignSubscriptionFile;
//
//        docuSignSubscriptionFile = new DocuSignSubscriptionFile();
//        docuSignSubscriptionFile.setFileName("SRA");
//        docuSignSubscriptionFile.setBeginDate("Jan 2, 2017");
//        docuSignSubscriptionFile.setEndDate("Feb 2, 2017");
//        docuSignSubscriptionFile.setInternalEmail("sra@gmail.com");
//        docuSignSubscriptionFiles[1] = docuSignSubscriptionFile;
//
//        docuSignSubscriptionFile = new DocuSignSubscriptionFile();
//        docuSignSubscriptionFile.setFileName("Enrollment");
//        docuSignSubscriptionFile.setBeginDate("Jan 3, 2017");
//        docuSignSubscriptionFile.setEndDate("Feb 3, 2017");
//        docuSignSubscriptionFile.setInternalEmail("enrollment@gmail.com");
//        docuSignSubscriptionFiles[2] = docuSignSubscriptionFile;
//
//        docuSignSubscriptionFile = new DocuSignSubscriptionFile();
//        docuSignSubscriptionFile.setFileName("PDF");
//        docuSignSubscriptionFile.setBeginDate("Jan 4, 2017");
//        docuSignSubscriptionFile.setEndDate("Feb 4, 2017");
//        docuSignSubscriptionFile.setInternalEmail("pdf@gmail.com");
//        docuSignSubscriptionFiles[3] = docuSignSubscriptionFile;
//
//        docuSignConfiguration.setDocuSignSubscriptionFiles(docuSignSubscriptionFiles);
//        return docuSignConfiguration;
    }

    public AllDocuSignConfigurations getAllDocuSignSubscriptions() {
        List<FeedEntity> allFeedDetailsForSubscribers = jdbcDocuSignSubscriberFeedDao.getAllFeedDetailsForSubscribers();

        int idx = 0;
        DocuSignConfiguration[] docuSignConfigurations = new DocuSignConfiguration[allFeedDetailsForSubscribers.size()];
        for (FeedEntity feedEntity : allFeedDetailsForSubscribers) {
            DocuSignConfiguration docuSignConfiguration = new DocuSignConfiguration();

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
        AllDocuSignConfigurations allDocuSignConfigurations = new AllDocuSignConfigurations();
        allDocuSignConfigurations.setDocuSignConfigurations(docuSignConfigurations);
        return allDocuSignConfigurations;

//        DocuSignConfiguration[] docuSignConfigurations = new DocuSignConfiguration[2];
//        DocuSignConfiguration docuSignConfiguration = getDocuSignSubscription("1");
//
//        docuSignConfigurations[0] = docuSignConfiguration;
//        docuSignConfigurations[1] = docuSignConfiguration;
//
//        AllDocuSignConfigurations allDocuSignConfigurations = new AllDocuSignConfigurations();
//
//        allDocuSignConfigurations.setDocuSignConfigurations(docuSignConfigurations);
//        return allDocuSignConfigurations;
    }

    public int updateDocuSignConfiguration(DocuSignConfiguration docuSignConfiguration) {
        docuSignConfiguration.setSubscriptionName("Schools First - updated");
        return jdbcDocuSignSubscriberFeedDao.updateDocuSignConfiguration(docuSignConfiguration);
    }

    public DocuSignConfiguration createDocuSignConfiguration(DocuSignConfiguration docuSignConfiguration) {
        docuSignConfiguration.setSubscriptionName("Schools First - created");
        return new DocuSignConfiguration();
    }

    public DocuSignConfiguration deleteDocuSignSubscription(String feedSeqId) {
        DocuSignConfiguration docuSignConfiguration = new DocuSignConfiguration();
        docuSignConfiguration.setSubscriptionName("Schools First - deleted");
        return docuSignConfiguration;
    }
}
