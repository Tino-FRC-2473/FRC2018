import numpy as np
import socket
import sys
import csv
import time

BASE_TIME = 1511843312
HOST = '10.24.73.2'
PORT = 50007
STOP_KEY = 'S'
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print('trying to connect')
s.connect((HOST,PORT))
print('connected')

times = []
rpm = []
current = []
startTime = 0
firstTime = True

while True:
	try:
		data = s.recv(1024)
		dataList = data.split()
		print('dataList' + str(dataList))

		if firstTime:
			startTime = float(dataList[1])
			firstTime = False

		times.append(int(dataList[0]) - startTime)
		rpm.append(dataList[1])
		current.append(dataList[2])
	except KeyboardInterrupt:
		break

t = time.time() - BASE_TIME
print('saving data', t)
plt.savefig('rawRPM.png')
plt.savefig('rawCur.png')

txtFile = open('data/rawData.txt', 'w')

for i in range(len(times)):
    txtFile.write(str(times[i] + ' ' + str(rpm[i]) + ' ' + str(current[i]) + '\n'))
txtFile.close()
quit()
