# Magic Fitting Room Dashboard V1.0

## Goals and features
Magic Fitting Room Dashboard is an app allowing the addition and
deletion of image files of Git repository. The app was built to 
facilitate a web application maintenance, by providing an GUI tool
to edit a Git repo.

## Configuration
The application needs a settings file "Settings.json". The settings
file must be stored in the same folder as the application.

## Usage
Start the application by double-clicking the 
"Magic-Fitting-Room-Dashboard.jar" file.

Depending on your machine configuration, you may need to start 
the application with following bash command 
````bash
java -jar Magic-Fitting-Room-Dashboard.jar
````

At the start, you are prompted a login page. The username is
always admin. The password must have been given to you.

Once logged-in, you are presented with four buttons. 

### Select a clothes category
This drop down list allows to choose a category to or from which the files
will be added or deleted.

### Add one or many pictures
This will open a file explorer on your local machine, select one or
many picture files. Click on "Add pictures to the application" to
add them to the Git repo (Web application) or "Cancel" to cancel.

### Delete one or many pictures
This will open a file explorer on your local machine. The files you
see are the ones from the local copy of the Git repository. Select
the picture files you want to delete (careful, these files will not
end in the recycle bin), and click on "Remove Pictures from the 
application" to delete them.

### Settings
This will open a settings panel. In this settings panel you can 
quickly change the Git repository address (HTTPS only) and update
your password.

Press "Save" to save your changes or "Cancel" to cancel.

Careful, saving with an empty password field will save your
password as empty or "".

### Logout
This will log you out of the application.

### Settings file usage

The settings files should contain the following pairs of key-value:
- "gitHubRepository": "https://yourgithost/yourgit"
- "emailAddress": "name@domain"
- "hash": "yourArgon2Hash"
- "localRepository": "locationOfLocalRepo" (Most likely left as it is).
- "token": "tokenProvidedByGitHost"
- 
## Technical features
- Argon2 password hashing
- Git repository manipulation

## Required environment
- Java Runtime Environment (JRE 11+) ([Oracle](https://www.java.com/en/download/manual.jsp))
- Network access to the Git host
- RECOMMENDED: Windows 10 or Windows 11

## Known issues and troubleshooting
- My application is slow to update the Web application: This may be due to the fact that you started the app for the first time.
- My application doesn't start: The app has not been tested for non-Windows machines.

## Authors
Authors: N. Gaillard, K. David, B. Taboga

## Language
Java 11

## Build dependencies
- de.mkammerer argon2-jvm 2.11
- com.googlecode.json-simple json-simple 1.1.1
- org.eclipse.jgit org.eclipse.jgit 6.3.0.202209071007-r

## Licence
Developed in academic context


