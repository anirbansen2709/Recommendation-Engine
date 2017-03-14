# Run Steps #

Open ```${project.home}/src/main/resources/application.properties``` file and change the project home path
```${project.home}``` refers to the base directory where this project is located

Check in ```${project.home}/src/main/resources/database.properties``` file if username / password matches 
   with system's mysql installation
   
Import ```${project.home}/setup_files/app_schema_0.1.sql``` into mysql using command,
   ```mysql -uroot -p < ${project.home}/setup_files/app_schema_0.1.sql```
This will create a new database named ```musicRecommendation```
   
Set up is ready. Now Run.