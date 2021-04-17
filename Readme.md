Using the Command Line Distribution API Application
  
Command-line Instructions

# Prerequisites:
Install the Java11 or later.
You may need to set your JAVA_HOME.
Download the jar file.

# Compile and run
java -jar distribution-api-0.0.1-SNAPSHOT.jar

When the application is running, a command line prompt will show "What is the amount Krakatoa Ventures plan to distribute?".
Enter a number and hit enter.
The JSON formatted report for distribution report by unit class and by owner will be printed in console.

The below is a sample report:

****************************************
Unit class Distribution Report (JSON):
{A=142.86, B=785.71, C=71.43}
****************************************

****************************************
Owner Distribution Report (JSON):
{Alex=392.86, David=142.86, Becky=464.29}
****************************************

Git repository.

  git clone https://github.com/GoogleCloudPlatform/java-docs-samples.git