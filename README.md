The following project is an internal project for 7Layers.
This program was written using Java 8 and is using external libraries, 
this was not set up with Maven and the dependencies will have to be found
manually, as such the following code cannot be compiled without the following dependencies.

javax.mail
poi-3.16
poi-examples-3.16
poi-.ooxml-3.16
poi-ooxml-schemas-3.16
poi-scratchpad-3.16
curveapi-1.04
xmlbeans-2.6.0
commons-codec-1.10
commons-collections4-4.1
commons-logging-1.2
junit-4.12
log4j-1.2.17

Some dependencies are not used in the project, but these are all these dependencies that
were added to the build path when starting the project.

The email class has had it's username and password removed for the sake of privacy; 
please enter your own account into the file.

The program is under the assumption the document follows the format license name at column 0 and active date on either
column 2 or 4. The dates are expected to be in MM-dd-yyyy format.


==============Compiling and running======================

In the working directory

mkdir bin
javac -d bin src/*.java


To run the program

java Controller 'Document Name Here'


=============Initial Changes=============================

Originally had a function to call a method that would perform the file IO, parse through
the excel sheet document, calculate which licenses were soon to be expired, and send an
email with the list of licenses, but decided to scrap it for now until a better solution
is found.

============Issues========================================

#There is an issue when parsing the the CMW2 lic sheet where there exists empty cells
and trying to read through those will initially do nothing, but later on in the function,
it will hit a null pointer exception due to those empty cells. Attempting to avoid the
exception by checking if the cells are null does not seem to work.

#Need to find a better way to implement a timer instead of just waiting. Trying to research
how to use the OS scheduler to run the program every 24 hours or at a specific time.

#Program needs more testing and the FileReader class needs to be more optimized due to the
lack of time to properly implement 1 function that can handle multiple sheets of similiar format.

#Need to obtain the updated license excel sheet to properly test the program as a whole.

#Currently need to find a way to send email using company email rather than a dummy gmail account.
