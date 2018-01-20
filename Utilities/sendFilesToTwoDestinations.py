import os, sys
from shutil import copyfile
#PRECONDITION: no unmatched files

#we need the end slashes for the directory names to make it work
def addEndSlash(fileName):
	if(fileName[len(fileName) - 1:] != '/'):
		fileName+="/"

	return fileName


#remember to add the slashes in!!!!!
inputDirectory = "/Users/work/Desktop/Tanupa DontCare 176-200/LabelData/"
valDestDir = "/Users/work/Desktop/val"
trDestDir = "/Users/work/Desktop/tr"

files = os.listdir(inputDirectory)
print files

files.sort()
print files
#print directory
inputDirectory = addEndSlash(inputDirectory)
valDestDir = addEndSlash(valDestDir)
trDestDir = addEndSlash(trDestDir)

index = 0
for file in files:
	fileName = str(file)

	#add slashes to directory

	if(fileName.find(".jpg") != -1 or fileName.find(".txt") != -1):
		if(index % 5 == 0):
			#copyfile(inputDirectory+fileName, valDestDir+fileName)
			print "val: " + fileName
		else:
			#copyfile(inputDirectory+fileName, trDestDir+fileName)
			print "tr: " + fileName
		index+=1
	#copyfile(inputDirectory + fileName, destDirectory + fileNameWithoutSpaces);

