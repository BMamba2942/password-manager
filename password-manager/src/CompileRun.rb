#Compile .java files
for dir in Dir['passwordgenerator/*/'] do 
	system("javac -d ../build/ " + dir + "*.java")
end

#Run application
Dir.chdir('../build')
system("java passwordgenerator/view/MainView")