#Client - Server architecture. Multitiered applications and Java EE 


## Client-Server architecture
- distributed software model
- server provides services
- client request the services
- usually hosted on separate hardware
- generally client can become server and vice versa


## Server
- shares resources and awaits client request
- most of logic resides on server
- usually handles more than one client


## Client
- requests services from server
- usually initiates communication
- need to communicate on protocol server understands
- client do not concern about the processing on server


## Client-Server communication
- usually request/response messaging pattern (not a rule)
- client sends request for resource/service, server returns response
- client and server communicate on well known protocol (e.g HTTP)


## Multitiered Applications
- application divided into multiple layers (tiers)
- the logic is divided by function
- sustainability and extendability
- performance in scale
- tier defined security

![multitiered architecture](https://d2mxuefqeaa7sj.cloudfront.net/s_9A039EF06EDBFB02AC13E7ABD1A60F7D10D987AFF751B0BA06778CB5B078029D_1472991620457_file.png)

## Java EE - What's this about?
- Standardization of enterprise/web components
  > "Java EE is a platform which defines 'standard specifications/APIs' which are then implemented by vendors and used for development of enterprise (distributed, 'multi-tired', robust) 'applications'.
  > These applications are composed of modules or 'components' which use Java EE 'containers' as their run-time infrastructure."
## Java EE - Facts
- Latest version EE 7 (Jul 2013)
- JSR 342
- Glassfish 4 - reference implementation
- Java EE 8 (JSR 366) in progress (hopefully H1 2017)
## Tiers
- **EJB Container**
- **WEB Container**
- Application Client container
- Java Applets
![java ee tiers](http://abhirockzz.files.wordpress.com/2014/01/containers.png)

## Container services
- vendor implementation of specification
  - JPA - Hibernate
  - CDI - Weld
  - JAX-RS - Jersey
  - container is then interface between deployed application and application server
## Typical EE Application
- Multi-Tier
- Client - Browser,SPA
- Server - EJB,Servlet, JSP
- Persistence - Database, Cloud Storage

## EE Web Profile
- subset of Java EE specification sufficient for typical web applications
- APIs:
  - Java Servlet API 3.1
  - EJB Lite 3.2
  - CDI 1.1
  - JSF 2.2
  - JTA 1.2
  - JPA 2.1
  - Web Socket 1.0
  - Bean Validation 1.1
  - JAX-RS 2.0
  - JSON-P 1.0

## EE Full Profile
- Complete set of API that matches the Platform Edition specification
- Java EE 7 compliant MUST HAVE
- APIs:
  - JTA
  - JMS
  - EJB
  - Managed Beans
  - JDBC
  - JPA
  - JNDI
  - JAX-RS
  - JAX-WS
  - JavaMail
