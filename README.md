#maxic towers project
##Environment Setup
###First
* Create a [Github](https://github.com) account
* Email me your username so I can add you as a collaborator to the project.

###Eclipse
* Download and install [Eclipse IDE for Java EE Developers](https://eclipse.org/downloads/packages/release/Luna/SR1A)
* In Eclipse, go to Help > Eclipse Marketplace
* Search 'Maven Integration for Eclipse' and install this package
* Search 'Spring IDE' and install this package

###Tomcat
* Install [Tomcat 7.0](http://tomcat.apache.org/download-70.cgi), Service Installer is easiest
* Start Tomcat, go to localhost:8080, if a Tomcat page appears it has installed correctly.
* Stop Tomcat
* In Eclipse go to Servers > New Server > Add the Tomcat 7.0 you just installed as a Server in Eclipse

###MySQL
* Download and install [MySQL Server](http://dev.mysql.com/downloads/mysql/) and [MySQL Workbench](http://dev.mysql.com/downloads/workbench/)
* Load up MySQL Workbench and create a new connection to the local server you installed with the username 'root' and password '' (blank)
* Go to users and privileges and create a user called 'admin' with password 'letmein', grant this user the Administrative Role of 'DBA' (database administrator), hit Apply.

###Git
* Download and install [Git](http://git-scm.com/)
* Open a Git bash terminal
* Navigate to the folder where you want to store the files for the workspace
* Run `git init` to initialise a local repository at the location where you are
* Run `git config --global user.name "<Your Name>"` to set your name
* Run `git config --global user.email "your_email@youremail.com"` your email here must be the one you signed up to Github with
* Run `git remote add maxi https://github.com/macleodmac/maxic.git` This adds the maxi repository I created to your remote sources
* Run `git pull maxi master (download the latest files)`
* Go into Eclipse and Import Project > Existing Maven Project > Navigate to the folder where you've pulled the Github repository to and import it.

##Using Git
###Updating your files
* `git fetch maxi master` sync the latest files but do not merge
* `git merge` merge the synced files
* `git pull maxi master` download and merge the latest files

###Pushing changes to the repository
* `git add -A` add all changed files to a commit
* `git commit -m "message"` commit these changes to your local repository, with a message. The message should be a short summary of the changes made.
* `git push -u maxi master` push these changes to the maxic repository on github (you will then be prompted for your username and password).

##Project Structure

###com.maxic.towers.web.config
* dao-context.xml
  * Sets annotation configuration, scans dao package for @Components
  * Defines the datasource used in the dao package
* service-context.xml
  * Sets annotation configuration, scans service package for @Components
  
###com.maxic.towers.web.controllers
* HomeController.java
  * Deals with requests for the home page, returns the relevant jsp
* TowerController.java
  * Connects to towerService (autowired)
  * Deals with request mappings related to towers

###com.maxic.towers.web.dao
* Tower.java
  * The tower bean, defines the properties of the bean
  * Defines constructors of bean
  * Defines getters and setters
* TowerDao.java
  * Connects to jdbc (autowired)
  * Runs queries on database

###com.maxic.towers.web.service
* TowerService.java
  * Instansiates towerDao (autowired)
  * Interfaces between controller and data access object

###com.maxic.towers.web.processing
* Parser.java
  * Splits the dove.txt file
  
###WEB-INF
* jsp folder
  * Contains JSPs
* towers-servlet.xml
  * Component scans the controller package
  * Defines the MVC as annotation driven
  * Defines the Spring view resolver
  * Defines the location of views (jsp folder, with extension .jsp)
* Web.xml
  * Defines default home files
  * Defines Dispatcher Servlet, set to load on startup, mapped to /
  * Defines database connection linking to context.xml
  * Defines locations to scan for context.xml files
  * Defines contextLoaderListener to do this



