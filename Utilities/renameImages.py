import os, sys
from shutil import copyfile

inputDirectory = "/Users/work/Desktop/Resized and Renamed CV2018Don'tCarePicturesOne/DONTCARE35-000470EmilyMATCHED/LabelData/"
destDirectory = "/Users/work/Desktop/Renamed/LabelData/"

images = os.listdir(inputDirectory)
#print directory

for image in images:
	fileName = str(image)
	if(fileName.find('000') != -1):
		print fileName
		#if(fileName.find(' ')!= -1 and fileName.index(' ')==0):
		#	print fileName

		fileNameWithoutSpaces=fileName.lstrip()
		print fileNameWithoutSpaces
		copyfile(inputDirectory + fileName, destDirectory + "1" + fileNameWithoutSpaces);