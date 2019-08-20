package com.nationwide.nf.rp.service;

import com.nationwide.nf.rp.bean.DocuSignConfiguration;
import com.nationwide.nf.rp.bean.DocuSignSubscriptionFile;
import com.nationwide.nf.rp.data.dao.dao.JdbcDocuSignSubscriberFeedDao;
import com.nationwide.nf.rp.data.dao.dao.JdbcDocuSignSubscriberFilesDao;
import com.nationwide.nf.rp.entity.FeedEntity;
import com.nationwide.nf.rp.entity.FileEntity;
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
    JdbcDocuSignSubscriberFilesDao jdbcDocuSignSubscriberFilesDao;

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
        docuSignConfiguration.setSubscriptionStatus(getStatus(feedDetailsForSubscriber.get(0).getSubscrStatus()));
        docuSignConfiguration.setSubscriptionBeginDate(dateUtil.getDateAsString(feedDetailsForSubscriber.get(0).getSubscrBeginDate()));
        docuSignConfiguration.setSubscriptionEndDate((feedDetailsForSubscriber.get(0).getSubscrEndDate() == null) ? null :
                dateUtil.getDateAsString(feedDetailsForSubscriber.get(0).getSubscrEndDate()));
        docuSignConfiguration.setFileTransferMethod(feedDetailsForSubscriber.get(0).getFileXferMethod());
        docuSignConfiguration.setFileTransferId(feedDetailsForSubscriber.get(0).getFileXferId());
        docuSignConfiguration.setFileTransferDirectory(feedDetailsForSubscriber.get(0).getFileXferDir());

        List<FileEntity> filesForSubscription = jdbcDocuSignSubscriberFilesDao.getFilesForSubscription(feedSeqId);
        if (filesForSubscription.size() > 0) {
            DocuSignSubscriptionFile[] docuSignSubscriptionFiles = new DocuSignSubscriptionFile[filesForSubscription.size()];
            DocuSignSubscriptionFile docuSignSubscriptionFile = null;
            int idx = 0;
            for (FileEntity fileEntity : filesForSubscription) {
                docuSignSubscriptionFile = new DocuSignSubscriptionFile();
                docuSignSubscriptionFile.setFileType(fileEntity.getFileType());
                docuSignSubscriptionFile.setFileNamePrefix(fileEntity.getFileNamePrefix());
                docuSignSubscriptionFile.setFileExtension(fileEntity.getFileExtension());
                docuSignSubscriptionFile.setCreateFileWhenEmpty(
                        fileEntity.getCreateFileWhenEmpty().equalsIgnoreCase("Y") ? "Yes" : "No");
                docuSignSubscriptionFile.setFileBeginDate(dateUtil.getDateAsString(fileEntity.getFileBeginDate()));
                docuSignSubscriptionFile.setFileEndDate(fileEntity.getFileEndDate() == null ? null :
                        dateUtil.getDateAsString(fileEntity.getFileEndDate()));
                docuSignSubscriptionFile.setInternalEmailNotifAdr(fileEntity.getInternalEmailNotifAddr());
                docuSignSubscriptionFile.setMftUserName(fileEntity.getMftUserName());
                docuSignSubscriptionFiles[idx++] = docuSignSubscriptionFile;
            }

            docuSignConfiguration.setDocuSignSubscriptionFiles(docuSignSubscriptionFiles);
        }
        return docuSignConfiguration;
    }

    private String getStatus(String statusCode) {
        String statusString;
        if (statusCode.equalsIgnoreCase("A")) {
            statusString = "Active";
        } else if (statusCode.equalsIgnoreCase("T")) {
            statusString = "Terminated";
        } else if (statusCode.equalsIgnoreCase("H")) {
            statusString = "Hold";
        } else {
            statusString = statusCode;
        }
        return statusString;
    }

    public DocuSignConfiguration[] getAllDocuSignSubscriptions() {
        List<FeedEntity> allFeedDetailsForSubscribers = jdbcDocuSignSubscriberFeedDao.getAllFeedDetailsForSubscribers();

        int idx = 0;
        DocuSignConfiguration[] docuSignConfigurations = new DocuSignConfiguration[allFeedDetailsForSubscribers.size()];
        for (FeedEntity feedEntity : allFeedDetailsForSubscribers) {
            DocuSignConfiguration docuSignConfiguration = new DocuSignConfiguration();

            docuSignConfiguration.setSeqId(String.valueOf(feedEntity.getSeqId()));
            docuSignConfiguration.setSubscriptionName(feedEntity.getSubscriberName());
            docuSignConfiguration.setSubscriptionBeginDate(dateUtil.getDateAsString(feedEntity.getSubscrBeginDate()));
            docuSignConfiguration.setSubscriptionEndDate(feedEntity.getSubscrEndDate() == null ? null :
                    dateUtil.getDateAsString(feedEntity.getSubscrEndDate()));
            docuSignConfiguration.setSubscriptionStatus(getStatus(feedEntity.getSubscrStatus()));
            docuSignConfiguration.setFileTransferMethod(feedEntity.getFileXferMethod());
            docuSignConfiguration.setFileTransferId(feedEntity.getFileXferId());
            docuSignConfiguration.setFileTransferDirectory(feedEntity.getFileXferDir());

            docuSignConfigurations[idx] = docuSignConfiguration;
            ++idx;
        }
        return docuSignConfigurations;
    }

    public int updateDocuSignConfiguration(DocuSignConfiguration docuSignConfiguration) {
//        docuSignConfiguration.setSubscriptionBeginDate(docuSignConfiguration.getSubscriptionBeginDate());
//        docuSignConfiguration.setSubscriptionBeginDate(docuSignConfiguration.getSubscriptionEndDate());

//        dateUtil.reformatDate("yyyy-MM-dd", docuSignConfiguration.getSubscriptionBeginDate()));
        //dateUtil.reformatDate("yyyy-MM-dd", docuSignConfiguration.getSubscriptionEndDate()));

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
