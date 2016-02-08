# jcsvdao
Java CSV to Pojo
Hibernate Style Mappings for CSV ORM

Simple ReadOnly Dao for mapping csv text files to Java Objects.

Allows the text columns to be mapped to Pojo properties using a Mappings file similar to the old hibernate .hbm.xml files.

jcsvdao can handle simple properties as well as unidirectional or bidirectional OneToOne, OneToMany and ManyToOne relationships

Examples of different mapping scenarios can be seen in the unit tests of jcsvdao-examples module.

Sample User CSV file
```
Username, Email, Registration Date, Age, Premium User
Jimmy, jim@test.com, 04-05-2016, 15, Yes, M
Bob, bob@test.com, 15-01-2012, 32, No, M
Alice, alice@test.com, 22-09-2011, 24, No, F
Mike, mike@test.com, 11-03-2012, 18, Yes, M
Helen, helen@test.com, 02-12-2013, 22, Yes, F
Tom, tom@test.com, 08-11-2015, 45, No, M
```
Create A CsvDao

```java
        CSVDaoFactory factory = new CSVDaoFactory("/csv-config.xml");
        CSVDao dao = new CSVDao(factory);
```        
csv-config.xml
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<CSVConfig>
    <mappingFiles fileType="resource">
        <mappingFile>/example01/mapping/UserDetail.csv.xml</mappingFile>
    </mappingFiles>
</CSVConfig>
```
UserDetail.csv.xml
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<CSVMapping className="org.jcsvdao.examples.example01.model.UserDetail" csvFile="csv-examples/example01/users.txt" delimiter="," ignoreFirstLine="true">
    <matchAll/>
    <properties>
        <property index="0" property="username" primaryKey="true"/>
        <property index="1" property="email"/>
        <property index="2" property="registrationDate" converter="myDateConverter"/>
        <property index="3" property="age"/>
        <property index="4" property="premiumUser" converter="yesNoConverter"/>
        <property index="5" property="gender" converter="myGenderConverter"/>
    </properties>
    <converters>
        <dateConverter converterName="myDateConverter" format="dd-MM-yyyy"/>
        <booleanConverter converterName="yesNoConverter" positive="Yes" negative="No"/>
        <customConverter converterName="myGenderConverter" converterClass="org.jcsvdao.examples.example01.converter.GenderCustomerConverter"/>
    </converters>
</CSVMapping>
```

