# OnboardingLabs

# Objective:  

  - To post a tweet using Twitter account @bsman
  - Retrieve latest home timeline from Twitter account @bsman


## How To Use (for Mac):
  - create a directory in Finder or in Terminal where the program will be located
  - open Terminal
  - in Terminal, enter the following:
    ```
    cd {location of directory you created above}

    git clone https://github.com/briansheen/OnboardingLabs.git

    cd OnboardingLabs/
    ```

### Run Using Maven

    mvn install
    cd target/

#### GetTimeline

    java -cp OnboardingLabs-1.0-SNAPSHOT-jar-with-dependencies.jar main.java.com.company.GetTimeline

#### Tweet

    java -cp OnboardingLabs-1.0-SNAPSHOT-jar-with-dependencies.jar main.java.com.company.Tweet '{your tweet}'
