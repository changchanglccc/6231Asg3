
package clientMTL;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebServiceClient(name = "SchoolServerImplService", targetNamespace = "http://server/", wsdlLocation = "http://localhost:4010/clinic?wsdl")
public class SchoolServerImplService
        extends Service {

    private final static URL CLINICSERVERIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException CLINICSERVERIMPLSERVICE_EXCEPTION;
    private final static QName CLINICSERVERIMPLSERVICE_QNAME = new QName("http://server/", "SchoolServerImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:4010/clinic?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CLINICSERVERIMPLSERVICE_WSDL_LOCATION = url;
        CLINICSERVERIMPLSERVICE_EXCEPTION = e;
    }

    public SchoolServerImplService() {
        super(__getWsdlLocation(), CLINICSERVERIMPLSERVICE_QNAME);
    }

    public SchoolServerImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CLINICSERVERIMPLSERVICE_QNAME, features);
    }

    public SchoolServerImplService(URL wsdlLocation) {
        super(wsdlLocation, CLINICSERVERIMPLSERVICE_QNAME);
    }

    public SchoolServerImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CLINICSERVERIMPLSERVICE_QNAME, features);
    }

    public SchoolServerImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SchoolServerImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * @return returns SchoolServer
     */
    @WebEndpoint(name = "ClinicServerImplPort")
    public SchoolServer getClinicServerImplPort() {
        return super.getPort(new QName("http://server/", "ClinicServerImplPort"), SchoolServer.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns SchoolServer
     */
    @WebEndpoint(name = "ClinicServerImplPort")
    public SchoolServer getClinicServerImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server/", "ClinicServerImplPort"), SchoolServer.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CLINICSERVERIMPLSERVICE_EXCEPTION != null) {
            throw CLINICSERVERIMPLSERVICE_EXCEPTION;
        }
        return CLINICSERVERIMPLSERVICE_WSDL_LOCATION;
    }

}
