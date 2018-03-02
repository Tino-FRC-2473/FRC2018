import os
import sys
from shutil import copyfile
if sys.version_info[0] < 3:
    from Tkinter import *
    import tkMessageBox
    import tkFileDialog
else:
    from tkinter import *
    import messagebox as tkMessageBox
    import filedialog as tkFileDialog

def replaceContent(content, findString, replaceString):
	newContent = content.replace(findString, replaceString)
	return newContent

def addEndSlash(fileName):
	if(fileName[len(fileName) - 1:] != '/'):
		fileName+="/"

	return fileName


print "select files"
filePaths = (tkFileDialog.askopenfilenames())
print filePaths

print "select destination directory"
destDir = tkFileDialog.askdirectory()
destDir = addEndSlash(destDir)
print destDir

#fileNameWithoutSlashes = fileName.replace("\\","");

for filePath in filePaths:
	print filePath
	fileName = os.path.relpath(filePath, os.path.dirname(filePath))
	file = open(filePath, "r");

	content = file.read()
	print content
	newContent = replaceContent(content, "dontcare", "powerbox")
	print newContent

	writeFilePath = destDir+fileName
	writtenFile = open(writeFilePath, "w")

	writtenFile.write(newContent)

	writtenFile.close()
	#print fileNameWithoutSlashes

	#file = open(fileName, "r")

	
	#print content.replace("dontcare", "powerbox")


	#print file

