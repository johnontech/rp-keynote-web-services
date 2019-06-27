package com.nationwide.nf.rp.service;

import com.nationwide.nf.rp.bean.DocuSignConfiguration;
import com.nationwide.nf.rp.bean.DocuSignSubscriptionFile;
import org.springframework.stereotype.Service;

@Service
public class DocuSignSubscriptionServiceImpl  implements DocuSignSubscriptionService {

    public DocuSignConfiguration getDocuSignSubscription(String feedSeqId) {

        DocuSignConfiguration docuSignConfiguration = new DocuSignConfiguration();

        docuSignConfiguration.setSubscriptionName("SchoolsFirst");
        docuSignConfiguration.setSubscriptionStatus("Active");
        docuSignConfiguration.setSubscriptionBeginDate("01-01-2019");
        docuSignConfiguration.setSubscriptionEndDate("12-31-2021");
        docuSignConfiguration.setFileTransferMethod("EB2B");
        docuSignConfiguration.setFileTransferId("/devl/rptest1/eb2b");
        docuSignConfiguration.setFileTransferDirectory("/transport/in/PENSIONS/KEYNOTE_OUT");
        docuSignConfiguration.setFileSubscriptionCases("SCHOOLSFIRST FEDERAL");

        DocuSignSubscriptionFile[] docuSignSubscriptionFiles = new DocuSignSubscriptionFile[4];

        DocuSignSubscriptionFile docuSignSubscriptionFile = new DocuSignSubscriptionFile();
        docuSignSubscriptionFile.setFileName("Census");
        docuSignSubscriptionFile.setBeginDate("Jan 1, 2017");
        docuSignSubscriptionFile.setEndDate("Feb 1, 2017");
        docuSignSubscriptionFile.setInternalEmail("census@gmail.com");
        docuSignSubscriptionFiles[0] = docuSignSubscriptionFile;

        docuSignSubscriptionFile = new DocuSignSubscriptionFile();
        docuSignSubscriptionFile.setFileName("SRA");
        docuSignSubscriptionFile.setBeginDate("Jan 2, 2017");
        docuSignSubscriptionFile.setEndDate("Feb 2, 2017");
        docuSignSubscriptionFile.setInternalEmail("sra@gmail.com");
        docuSignSubscriptionFiles[1] = docuSignSubscriptionFile;

        docuSignSubscriptionFile = new DocuSignSubscriptionFile();
        docuSignSubscriptionFile.setFileName("Enrollment");
        docuSignSubscriptionFile.setBeginDate("Jan 3, 2017");
        docuSignSubscriptionFile.setEndDate("Feb 3, 2017");
        docuSignSubscriptionFile.setInternalEmail("enrollment@gmail.com");
        docuSignSubscriptionFiles[2] = docuSignSubscriptionFile;

        docuSignSubscriptionFile = new DocuSignSubscriptionFile();
        docuSignSubscriptionFile.setFileName("PDF");
        docuSignSubscriptionFile.setBeginDate("Jan 4, 2017");
        docuSignSubscriptionFile.setEndDate("Feb 4, 2017");
        docuSignSubscriptionFile.setInternalEmail("pdf@gmail.com");
        docuSignSubscriptionFiles[3] = docuSignSubscriptionFile;

        docuSignConfiguration.setDocuSignSubscriptionFiles(docuSignSubscriptionFiles);

        return docuSignConfiguration;
    }
}
