package util;

import configManager.DataLoad;
import configManager.ResourceData;

import java.util.HashMap;

public class APIFormParams {

    public static HashMap<String, String> defaultFormParams() {

        DataLoad dataLoad = DataLoad.getInstance();
        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("grant_type", "client_credentials");
        formParams.put("client_id", ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesEngageClientCredentialsClientId"));
        formParams.put("client_secret", ResourceData.getEnvironmentURL(ResourceData.getEnvironment() + "." + "servicesEngageClientCredentialsSecret"));
        formParams.put("scope", ResourceData.getScopesProperty(dataLoad.getParticipantTokenScope()));
        String trustedIdentifier = dataLoad.getSubScopeTrustedIdentifier();
        if (!(trustedIdentifier == null || trustedIdentifier.isEmpty() || trustedIdentifier.trim().isEmpty())) {
            formParams.put("grant_type", "trusted_identifier");
            formParams.put("trusted_identifier", trustedIdentifier);
            System.out.println("trusted_identifier = " + trustedIdentifier);

        }

        return formParams;
    }

}
