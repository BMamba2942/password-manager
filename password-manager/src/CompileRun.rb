#Compile .java files
for dir in Dir['passwordgenerator/*/'] do 
	system("javac " + dir + "*.java")
end

#Run application
system("java passwordgenerator/view/MainView")