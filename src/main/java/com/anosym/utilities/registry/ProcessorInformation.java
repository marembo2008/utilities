/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.registry;

/**
 *
 * @author Administrator
 */
public interface ProcessorInformation {

    public static interface Processor {

        public int getProcessorSpeed();

        public String getComponentInformation();

        public String getConfigurationData();

        public long getFeatureSet();

        public String getIdentifier();

        public long getPlatformId();

        public String getPreviousUpdateSignature();

        public String getPreviousNameString();

        public String getUpdateSignature();

        public int getUpdateStaus();

        public String getVendorIdentifier();
    }

    public int getNumberOfProcessors();

    public Processor[] getProcessors();
}
