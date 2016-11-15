# Unit and Integration testing in Java. Code coverage

## Software Testing Pyramid
![](https://d2mxuefqeaa7sj.cloudfront.net/s_A71EE19DE2EB5636DB417FA80CDCA5FFA357726AF9CADC33E9A66538C60EB360_1478105802088_iu.png)

## Unit Tests 
- as name suggests this tests are for the one unit (one class)
- should be really fast
- there should be hundreds of such tests
- good written tests are better than javadoc
- [TestNG](http://testng.org/doc/index.html), [JUnit](http://junit.org/junit4/), [Mockito](http://site.mockito.org), [AssertJ](https://joel-costigliola.github.io/assertj/)

**JUnit sample**

```java
    @Test
    public void emptyTest() {
     //1. prepare
     final List<String> list = new ArrayList<>();
     
     //2. evaluation
     final boolean result = list.isEmpty();
     
     //3. validation
     assertTrue(result);
    }
```
**Mockito sample**

```java
    private AuthorDao authorDao;
    private AuthorManagerImpl instance;
    
    @Before
    public void setUp() {
        instance = new AuthorManagerImpl();
        instance.setAuthorDao(mock(AuthorDao.class));
    }
```

**Assertj sample**

```java
    // basic assertions
    assertThat(frodo.getName()).isEqualTo("Frodo");
    assertThat(frodo).isNotEqualTo(sauron);
    
    // chaining string specific assertions
    assertThat(frodo.getName()).startsWith("Fro")
                               .endsWith("do")
                               .isEqualToIgnoringCase("frodo");
    
    // collection specific assertions (there are plenty more)
    // in the examples below fellowshipOfTheRing is a List<TolkienCharacter>
    assertThat(fellowshipOfTheRing).hasSize(9)
                                   .contains(frodo, sam)
                                   .doesNotContain(sauron);
```

## Integration Tests
- testing “integration” between multiple system components
- typical integration tests are executed against real database
- slower, usually runs on CI server
- [Selenium](http://www.seleniumframework.com/selenium-java/), [Arquillian](http://arquillian.org), [REST Assured](http://rest-assured.io)
## Code coverage
- shows how much of source code is coverage by unit/integration tests
- [Jacoco](https://wiki.openjdk.java.net/display/CodeTools/jcov), [Cobertura](http://cobertura.github.io/cobertura/), [SonarQube](http://www.sonarqube.org)

