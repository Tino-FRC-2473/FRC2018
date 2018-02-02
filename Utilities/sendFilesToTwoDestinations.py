import os, sys
from shutil import copyfile
if sys.version_info[0] < 3:
    from Tkinter import *
    import tkMessageBox
    import tkFileDialog
else:
    from tkinter import *
    import messagebox as tkMessageBox
    import filedialog as tkFileDialog
#PRECONDITION: no unmatched files

#we need the end slashes for the directory names to make it work
def addEndSlash(fileName):
	if(fileName[len(fileName) - 1:] != '/'):
		fileName+="/"

	return fileName


def getSelectedDirectories():
	print "Select the directory for the images"
	inputDirectory =  tkFileDialog.askdirectory()
	print inputDirectory
	#print type(inputDirectory)

	#print "Select the directory for the labels"
	#print tkFileDialog.askdirectory()

	print "Select the validation target destination"
	valDestDir = tkFileDialog.askdirectory()
	print valDestDir

	print "Select the training target destination"
	trDestDir = tkFileDialog.askdirectory()
	print trDestDir

	return inputDirectory, valDestDir, trDestDir


def sendFilesToDestinations(files, inputDirectory, valDestDir, trDestDir, copy=False):
	index = 0
	for file in files:
		fileName = str(file)

		#add slashes to directory

		if(fileName.find(".jpg") != -1 or fileName.find(".txt") != -1):
			if(index % 5 == 0):
				if(copy):
					copyfile(inputDirectory+fileName, valDestDir+fileName)
				print "val: " + fileName
			else:
				if(copy):
					copyfile(inputDirectory+fileName, trDestDir+fileName)
				print "tr: " + fileName
			index+=1


#setup the variable names
inputDirectory, valDestDir, trDestDir = getSelectedDirectories()
inputDirectory = addEndSlash(inputDirectory)
valDestDir = addEndSlash(valDestDir)
trDestDir = addEndSlash(trDestDir)

labelsDirectory = inputDirectory+"LabelData/"

#inputDirectory = "/Users/work/Desktop/Tanupa DontCare 176-200/LabelData/"
#valDestDir = "/Users/work/Desktop/val"
#trDestDir = "/Users/work/Desktop/tr"

imageFiles = os.listdir(inputDirectory)


print imageFiles

imageFiles.sort()#appears to work
print imageFiles
#print directory

response = input('Do you want this to actually send the files? Type "Y" for yes: ')
copy = False
print response
if(response=='y'):
	copy = True

sendFilesToDestinations(imageFiles, inputDirectory, valDestDir, trDestDir, copy)

#textFiles = os.listdir(labelsDirectory)
#sendFilesToDestinations(textFiles, labelsDirectory, valDestDir, trDestDir, copy)

print str(response=='y')