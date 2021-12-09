

# Maximum Height By Stacking Blocks Application

This is a Maximum Height By Stacking Blocks application which provides a set of features
covering the specification of the requirements described [here](tech-programming-test-block-stack-1-0.pdf).

The application calculates and logs to terminal/console the maximum height of the stacked blocks. 

The solution modelled blocks as [Cuboids](https://en.wikipedia.org/wiki/Cuboid) 
while the problem was framed as a variant of [Longest increasing subsequence](https://en.wikipedia.org/wiki/Longest_increasing_subsequence)

## Prerequisites

- JDK 11+ or higher
- Gradle runtime

## Building the source
Run the following command to build the source. This will create a single jar executable which can be found in 
`build/libs/max-height-by-stacking-blocks-1.0-SNAPSHOT.jar`

```bash
./gradle clean build
```

## Running the test suite

Running this command will run all tests

```bash
./gradle test
```

Executing this command will yield the following console output

![alt text][unitTestOutcome]


## Running the app from terminal

Execute the below command to build and execute the app from terminal.


```bash
java -jar build/libs/max-height-by-stacking-blocks-1.0-SNAPSHOT.jar <path_to_input_file>
```

Complete example: 

```bash
java -jar build/libs/max-height-by-stacking-blocks-1.0-SNAPSHOT.jar /Users/josh/workspace/projects/max-height-by-stacking-blocks/src/test/resources/TestCase4.txt /Users/josh/workspace/projects/max-height-by-stacking-blocks/src/test/resources/TestCase4.txt
```

Executing this command will log the following to terminal
```log
102
```

## Code coverage

### Jacoco code coverage
While the goal of the test harness is to cover as much edge and corner cases, that naturally led to a wider coverage of over 90%.
Code coverage was both executed as part of gradle build cycle using [JaCoCo](https://github.com/jacoco/jacoco) gradle plugin.

Execute this command to run code coverage
```bash
./gradlew clean build jacocoTestReport
```
![alt text][codeCoverageJacoco]

## Code formatting
Source code was formatted using [google-java-format](https://github.com/google/google-java-format)

[codeCoverageJacoco]: screenshots/codecov.png "codeCoverageJacoco"
[unitTestOutcome]: screenshots/testoutcome.png "unitTestOutcome"
