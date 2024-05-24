import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.handler.*;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.sessions.ActionResult;
import burp.api.montoya.http.sessions.SessionHandlingAction;
import burp.api.montoya.http.sessions.SessionHandlingActionData;

import static burp.api.montoya.http.handler.RequestToBeSentAction.continueWith;
import static burp.api.montoya.http.handler.ResponseReceivedAction.continueWith;
import static burp.api.montoya.http.sessions.ActionResult.actionResult;

@SuppressWarnings("unused")
public class Extension implements BurpExtension
{

    public static final String NAME = "Prevent language changes";

    @Override
    public void initialize(MontoyaApi montoyaApi)
    {
        montoyaApi.extension().setName(NAME);

        montoyaApi.http().registerSessionHandlingAction(new SessionHandlingAction()
        {
            @Override
            public String name()
            {
                return NAME;
            }

            @Override
            public ActionResult performAction(SessionHandlingActionData sessionHandlingActionData)
            {
                HttpRequest newRequest = sessionHandlingActionData.request().withBody("{\"lang_code\":\"en-us\"}");

                return actionResult(newRequest);
            }
        });

        /*
        montoyaApi.http().registerHttpHandler(new HttpHandler()
        {
            @Override
            public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent httpRequestToBeSent)
            {
                if (httpRequestToBeSent.method().equals("PUT") && httpRequestToBeSent.pathWithoutQuery().equals("/api/internal/account/settings"))
                {
                    return continueWith(httpRequestToBeSent.withBody("{\"lang_code\":\"en-us\"}"));
                }

                return continueWith(httpRequestToBeSent);
            }

            @Override
            public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived httpResponseReceived)
            {
                return continueWith(httpResponseReceived);
            }
        });
        */
    }
}
