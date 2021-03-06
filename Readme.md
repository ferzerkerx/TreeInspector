# TreeInspector

[![Build Status](https://travis-ci.org/ferzerkerx/TreeInspector.svg?branch=master)](https://travis-ci.org/ferzerkerx/TreeInspector)
[![Quality Gate](https://sonarcloud.io/api/badges/gate?key=com.ferzerkerx%3ATreeInspector)](https://sonarcloud.io/dashboard/index/com.ferzerkerx%3ATreeInspector)

Displays an adjacency list as a tree and adds some controls to it

![alt text](https://raw.githubusercontent.com/ferzerkerx/TreeInspector/master/img/sample.png "sample img")


## File specs
- File needs to be located under TreeInspector/src/main/resources/com/ferzerkerx/tree_inspector/service/tree_data.txt
- Format:
    - the first line indicates the # of adjanencies to find
    - the rest of the lines indicate an adjacency
    - Example:
    
```python
3     # 3 adjacencies
4 50  # 4 --> 50
4 145 # 4 --> 145
5 53  # 5 --> 53
```


## Build:
mvn clean jfx:jar

## Run:
java -jar target/jfx/app/TreeInspector-1.0-jfx.jar
