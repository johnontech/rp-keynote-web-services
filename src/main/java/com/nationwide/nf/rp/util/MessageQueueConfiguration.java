package com.nationwide.nf.rp.util;

import org.springframework.stereotype.Component;

/**
 * This domain class encapsulates message queue configuration details.
 *
 * Modification Log:
 *
 * Date        Modifier             Description
 * ----------  -------------------  --------------------------------------------
 * 5/25/2017   J. Jorgensen         Initial version.
 */
@Component
public class MessageQueueConfiguration {

    private String messageQueueInitialContext;
    private String messageQueueProviderUrl;
    private String messageQueueFactoryName;
    private String messageQueueName;
    private String temporaryMessageFilePath;
    private String archiveMessageRootFilePath;
    private String temporaryBadMessageFilePath;

    public String getMessageQueueInitialContext() {
        return messageQueueInitialContext;
    }

    public void setMessageQueueInitialContext(String messageQueueInitialContext) {
        this.messageQueueInitialContext = messageQueueInitialContext;
    }

    public String getMessageQueueProviderUrl() {
        return messageQueueProviderUrl;
    }

    public void setMessageQueueProviderUrl(String messageQueueProviderUrl) {
        this.messageQueueProviderUrl = messageQueueProviderUrl;
    }

    public String getMessageQueueFactoryName() {
        return messageQueueFactoryName;
    }

    public void setMessageQueueFactoryName(String messageQueueFactoryName) {
        this.messageQueueFactoryName = messageQueueFactoryName;
    }

    public String getMessageQueueName() {
        return messageQueueName;
    }

    public void setMessageQueueName(String messageQueueName) {
        this.messageQueueName = messageQueueName;
    }

    public String getArchiveMessageRootFilePath() {
        return archiveMessageRootFilePath;
    }

    public void setArchiveMessageRootFilePath(String archiveMessageRootFilePath) {
        this.archiveMessageRootFilePath = archiveMessageRootFilePath;
    }

    public String getTemporaryMessageFilePath() {
        return temporaryMessageFilePath;
    }

    public void setTemporaryMessageFilePath(String temporaryMessageFilePath) {
        this.temporaryMessageFilePath = temporaryMessageFilePath;
    }

    public void setTemporaryBadMessageFilePath(String temporaryBadMessageFilePath) {
        this.temporaryBadMessageFilePath = temporaryBadMessageFilePath;
    }

    public String getTemporaryBadMessageFilePath() {
        return temporaryBadMessageFilePath;
    }

    @Override
    public String toString() {
        return "MessageQueueConfiguration{" +
                "messageQueueInitialContext='" + messageQueueInitialContext + '\'' +
                ", messageQueueProviderUrl='" + messageQueueProviderUrl + '\'' +
                ", messageQueueFactoryName='" + messageQueueFactoryName + '\'' +
                ", messageQueueName='" + messageQueueName + '\'' +
                ", temporaryMessageFilePath='" + temporaryMessageFilePath + '\'' +
                ", archiveMessageRootFilePath='" + archiveMessageRootFilePath + '\'' +
                ", temporaryBadMessageFilePath='" + temporaryBadMessageFilePath + '\'' +
                '}';
    }
}
