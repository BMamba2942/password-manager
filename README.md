password-manager
================

Simple password manager

Have yet to add any application that will build, but this is how I do it outside of an IDE:

Password Manager now requires the Jasypt library (http://www.jasypt.org/download.html)

cd to password-manager/src/

complie with this: "javac passwordgenerator/\*/\*.java"

Run with this: "java passwordgenerator/view/MainView"

Optional:
If your system supports ruby, I have added a script under the src directory that should compile and run PasswordManager. The script assumes javac and java are accessible directly from the command prompt/terminal(set in path variable for Windows) and that there is a build directory in the same directory where src is located(password-manager/build, in other words). Untested for Linux/Mac systems, but should work there as well.

simply type: "ruby CompileRun.rb" and it should do the rest!
