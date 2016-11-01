# Web services REST, SOAP, RPC, Websockets

## REST
- protocol defined over HTTP protocol (can be defined on other protocols)
- HTTP methods used for diferrent action on resource
  - POST - **C**reate resource
  - GET - **R**ead resource
  - PUT - **U**pdate resource
  - DELETE - **D**elete resource
- resource defined by url
- each url represents the full state of resource -> communication is stateless

## REST - Java EE API
- JAX-RS
- defined by JSR 339


## REST - resources
- resources defined by @Path annotation
- methods identified by @GET,@POST,@PUT,@DELETE annotations
- to define base context user @ApplicationPath annotation
- @Consumes defines accepted content-type
- @Produces defines produced content-type 

```
    @GET
    @Produces("application/json")
    @Path("{id}") //Path params
    public PostDto getOne(@PathParam("id") Integer id) {
        return postManager.getOne(id);
    }
```

## REST - params
- resource can access few types of params
- @CookieParam,@HeaderParam,@PathParam,@QueryParam,@FormParam

```
    http://example.com/<path_param_value>?<query_param_name>=<query_param_value>
```

## Entity Providers
- provides mapping from on-the-wire representation to Java and vice versa
- @Provider annotation
- MessageBodyReader,MessageBodyWriter must be implemented

```
@Provider
@Produces("application/json")
public class PostDtoWriter implements MessageBodyWriter<PostDto> {

    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return aClass == PostDto.class;
    }

    @Override
    public long getSize(PostDto postDto, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

    @Override
    public void writeTo(PostDto o, Class<?> type,
                        Type t,
                        Annotation[] as,
                        MediaType mt,
                        MultivaluedMap<String, Object> mm,
                        OutputStream out)
            throws IOException, WebApplicationException {
        JsonUtil.writeJson(out, o);
    }
}
```

# SOAP(Simple Object Access Protocol) Web Service
- XML based protocol
- JAX-WS 
- defined by JSR 224

# SOAP - service description
- WSDL describes the service
- Data mapping between Java and XML 
is defined through the Java API for XML Binding (JAXB)


# SOAP - implementation perspective
- code-first/contract-first approach
- class/object annotated as @WebService provides the WS
- Service Endpoint Interface
- all public methods as web service operations
- @WebMethod to override defautl for public methods
- @WebParam,@WebResult,@WebFault
- fire and forget defined by @Oneway


# SOAP - parameters
- endpointInterface - Fully qualified class name of the service endpoint interface defining the service’s abstract web service contract
- name - Name of the web service (wsdl:portType)
- portName - Port name of the web service (wsdl:port)
- serviceName - Service name of the web service (wsdl:service)
- targetNamespace - Namespace for the web service (targetNamespace)
- wsdlLocation - Location of a predefined WSDL describing the service



# WebSocket API
- WebSocket is defined as JSR 356
- full duplex protocol over TCP connection
- protocol upgrade after handshake
- messages composed of one or more frames
- application / control frames


  