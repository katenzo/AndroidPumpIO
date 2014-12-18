package model;

import model.registerClient.RegisterApplication;
import model.registerClient.RegisterClient;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import service.PumpIORestAPI;
import service.PumpIORestAdapter;

/**
 * Created by katenzo on 12/16/14.
 */
public class OAuth {

    public static OAuthConsumer getOAuthConsumerClient() {

        PumpIORestAPI pumpIORestAPI = PumpIORestAdapter.getApiInterface();


        RegisterApplication register = new RegisterApplication();
        register.setType(Constanta.REGISTER_CLIENT_ASSOCIATE);
        register.setApplicationType(Constanta.REGISTER_APPLICATION_TYPE_NATIVE);
        register.setApplicationName(Constanta.REGISTER_APPLICATION_NAME);

        RegisterClient registerClient =  pumpIORestAPI.getRegisterClient(register);

        OAuthConsumer oAuthConsumer = new DefaultOAuthConsumer(
                registerClient.getClientId(), registerClient.getClientSecret());

        return oAuthConsumer;

    }



}
