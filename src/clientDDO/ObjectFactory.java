
package clientDDO;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the clientDDO package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EditRecordResponse_QNAME = new QName("http://server/", "editRecordResponse");
    private final static QName _CreateNRecord_QNAME = new QName("http://server/", "createNRecord");
    private final static QName _TransferRecordResponse_QNAME = new QName("http://server/", "transferRecordResponse");
    private final static QName _TransferRecord_QNAME = new QName("http://server/", "transferRecord");
    private final static QName _CreateDRecord_QNAME = new QName("http://server/", "createDRecord");
    private final static QName _CreateDRecordResponse_QNAME = new QName("http://server/", "createDRecordResponse");
    private final static QName _CreateNRecordResponse_QNAME = new QName("http://server/", "createNRecordResponse");
    private final static QName _EditRecord_QNAME = new QName("http://server/", "editRecord");
    private final static QName _GetRecordCountsResponse_QNAME = new QName("http://server/", "getRecordCountsResponse");
    private final static QName _GetRecordCounts_QNAME = new QName("http://server/", "getRecordCounts");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: clientDDO
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetRecordCounts }
     */
    public GetRecordCounts createGetRecordCounts() {
        return new GetRecordCounts();
    }

    /**
     * Create an instance of {@link CreateTRecordResponse }
     */
    public CreateTRecordResponse createCreateDRecordResponse() {
        return new CreateTRecordResponse();
    }

    /**
     * Create an instance of {@link CreateSRecordResponse }
     */
    public CreateSRecordResponse createCreateNRecordResponse() {
        return new CreateSRecordResponse();
    }

    /**
     * Create an instance of {@link EditRecord }
     */
    public EditRecord createEditRecord() {
        return new EditRecord();
    }

    /**
     * Create an instance of {@link GetRecordCountsResponse }
     */
    public GetRecordCountsResponse createGetRecordCountsResponse() {
        return new GetRecordCountsResponse();
    }

    /**
     * Create an instance of {@link CreateTRecord }
     */
    public CreateTRecord createCreateDRecord() {
        return new CreateTRecord();
    }

    /**
     * Create an instance of {@link TransferRecord }
     */
    public TransferRecord createTransferRecord() {
        return new TransferRecord();
    }

    /**
     * Create an instance of {@link CreateSRecord }
     */
    public CreateSRecord createCreateNRecord() {
        return new CreateSRecord();
    }

    /**
     * Create an instance of {@link TransferRecordResponse }
     */
    public TransferRecordResponse createTransferRecordResponse() {
        return new TransferRecordResponse();
    }

    /**
     * Create an instance of {@link EditRecordResponse }
     */
    public EditRecordResponse createEditRecordResponse() {
        return new EditRecordResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditRecordResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server/", name = "editRecordResponse")
    public JAXBElement<EditRecordResponse> createEditRecordResponse(EditRecordResponse value) {
        return new JAXBElement<EditRecordResponse>(_EditRecordResponse_QNAME, EditRecordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSRecord }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server/", name = "createNRecord")
    public JAXBElement<CreateSRecord> createCreateNRecord(CreateSRecord value) {
        return new JAXBElement<CreateSRecord>(_CreateNRecord_QNAME, CreateSRecord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransferRecordResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server/", name = "transferRecordResponse")
    public JAXBElement<TransferRecordResponse> createTransferRecordResponse(TransferRecordResponse value) {
        return new JAXBElement<TransferRecordResponse>(_TransferRecordResponse_QNAME, TransferRecordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransferRecord }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server/", name = "transferRecord")
    public JAXBElement<TransferRecord> createTransferRecord(TransferRecord value) {
        return new JAXBElement<TransferRecord>(_TransferRecord_QNAME, TransferRecord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTRecord }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server/", name = "createDRecord")
    public JAXBElement<CreateTRecord> createCreateDRecord(CreateTRecord value) {
        return new JAXBElement<CreateTRecord>(_CreateDRecord_QNAME, CreateTRecord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTRecordResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server/", name = "createDRecordResponse")
    public JAXBElement<CreateTRecordResponse> createCreateDRecordResponse(CreateTRecordResponse value) {
        return new JAXBElement<CreateTRecordResponse>(_CreateDRecordResponse_QNAME, CreateTRecordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSRecordResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server/", name = "createNRecordResponse")
    public JAXBElement<CreateSRecordResponse> createCreateNRecordResponse(CreateSRecordResponse value) {
        return new JAXBElement<CreateSRecordResponse>(_CreateNRecordResponse_QNAME, CreateSRecordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditRecord }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server/", name = "editRecord")
    public JAXBElement<EditRecord> createEditRecord(EditRecord value) {
        return new JAXBElement<EditRecord>(_EditRecord_QNAME, EditRecord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRecordCountsResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server/", name = "getRecordCountsResponse")
    public JAXBElement<GetRecordCountsResponse> createGetRecordCountsResponse(GetRecordCountsResponse value) {
        return new JAXBElement<GetRecordCountsResponse>(_GetRecordCountsResponse_QNAME, GetRecordCountsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRecordCounts }{@code >}}
     */
    @XmlElementDecl(namespace = "http://server/", name = "getRecordCounts")
    public JAXBElement<GetRecordCounts> createGetRecordCounts(GetRecordCounts value) {
        return new JAXBElement<GetRecordCounts>(_GetRecordCounts_QNAME, GetRecordCounts.class, null, value);
    }

}
