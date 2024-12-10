# Security-Tutorial

## If you face any lombok issue in Intellij Idea the follow these steps:

### Step 1 - add below plugin in your POM.xml file
 ```sh
  <plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<annotationProcessorPaths>
						<path>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
							<version>${lombok.version}</version>
						</path>
					</annotationProcessorPaths>
				</configuration>
			</plugin>
  ```

### Step 2 - Run below mvn commands in Intellij
```sh
mvn clean install
```

## Now the issue should be resolved
