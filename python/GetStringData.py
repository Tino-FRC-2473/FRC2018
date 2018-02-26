import numpy as np
import socket
import sys
import csv
import datetime

HOST = '10.24.73.56'
PORT = 50505
STOP_KEY = 'S'
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
firstTime = True
print('trying to connect')
s.connect((HOST,PORT))
print('connected')

stringData = []

while True:
	try:
		data = str(s.recv(1024))
		print('data: ' + data)

		if firstTime:
			firstMS = long(data[data.index('[') + 1 : data.index(']')])
			firstTime = False

		stringData.append(data)
	except KeyboardInterrupt:
		break

txtFile = open('data/rawStringData' + str(datetime.datetime.fromtimestamp(firstMS) / 1000) + '.txt', 'w')
txtFile.seek(0)
txtFile.truncate()

for i in range(len(stringData)):
    txtFile.write(stringData[i]+'\n')
txtFile.close()
quit()
