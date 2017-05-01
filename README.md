# Liquibase JUnit Test Extension
[![Build Status](https://travis-ci.org/hcspidergrasp/liquibase-junit-test.svg?branch=master)](https://travis-ci.org/hcspidergrasp/liquibase-junit-test)

This extension allows to the developers to use Liquibase migrations in JUnit tests.
It gives the ability to reset database schema and fill it with test data with Liquibase migration tools.
So that each test or group of tests has predictable database start state.

### List of core features

* Test method scoped migration
* Test class scoped migration

## Getting started

Right now Liquibase JUnit Test Extension is available only in source code.

### Installation

First you need to build this library.

```bash
./gradlew build
```

Then just put compiled library in your JUnit tests classpath.

### How to use

Liquibase JUnit Test Extension provides JUnit test runner ```LiquibaseTestRunner``` for running migrations within tests.

```java
@RunWith(LiquibaseTestRunner.class)
public class YourTestClass {
    // Your tests.
}
```

Right now it supports ```@LiquibaseTest``` annotation. It can be used
for methods or classes.

When you use ```@LiquibaseTest``` for methods it means that each annotated method
will have clean database environment which will be dropped right after
test method will be completed.

```java
@RunWith(LiquibaseTestRunner.class)
public class YourTestClass {
    
    @Test
    @LiquibaseTest
    public void yourTestMethod() {
        // Do your test here.
    }
    
}
```

When you use ```@LiquibaseTest``` for classes it means that each
annotated class will have clean database environment. It will be dropped right
after all test methods will be completed.

```java
@RunWith(LiquibaseTestRunner.class)
@LiquibaseTest
public class YourTestClass {
    
    @Test
    public void testMethod1() {
        // Do your test here.
    }
    
    @Test
    public void testMethod2() {
        // Do your test here.
    }
    
}
```

## License

This product is under Apache 2.0 license.